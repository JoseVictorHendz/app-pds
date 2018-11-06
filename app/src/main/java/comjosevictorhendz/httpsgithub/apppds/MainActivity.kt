@file:Suppress(   "DEPRECATION")

package comjosevictorhendz.httpsgithub.apppds

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import 	android.provider.MediaStore
import android.content.Intent
import android.graphics.Bitmap
import android.widget.Button
import android.widget.ImageView
import android.util.Base64
import java.io.ByteArrayOutputStream
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    internal lateinit var image_label_detection: Button
    internal lateinit var mImageView: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image_label_detection = findViewById(R.id.image_label_detection)
        mImageView = findViewById(R.id.mImageView)


        image_label_detection.setOnClickListener {
            dispatchTakePictureIntent()
        }


    }

    val REQUEST_IMAGE_CAPTURE = 1

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data!!.extras.get("data") as Bitmap
            mImageView.setImageBitmap(imageBitmap)


            try {
                val encodedImage = encodeImage(imageBitmap)
                Toast.makeText(applicationContext, "Bombando: " + encodedImage, Toast.LENGTH_LONG).show()
            }catch (exeption: Exception) {
                Toast.makeText(applicationContext, "error: " + exeption, Toast.LENGTH_LONG).show()

            }




        }
    }

    private fun encodeImage(bm: Bitmap): String {
        val ByteArray = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, ByteArray)
        val b = ByteArray.toByteArray()

        return Base64.encodeToString(b, Base64.DEFAULT)
    }

}
