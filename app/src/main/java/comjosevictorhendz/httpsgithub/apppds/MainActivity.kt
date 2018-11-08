@file:Suppress(   "DEPRECATION")

package comjosevictorhendz.httpsgithub.apppds

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.content.Intent
import android.graphics.Bitmap
import android.widget.Button
import android.widget.Toast



class MainActivity : AppCompatActivity() {
    var url = "https://api-back-pds.herokuapp.com"
    internal lateinit var image_label_detection: Button




    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image_label_detection = findViewById(R.id.image_label_detection)

        image_label_detection.setOnClickListener {
//            dispatchTakePictureIntent()
            test1()
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
        val bitmap: Bitmap

        val treatPictures = TreatPictures()
//
        bitmap = treatPictures.getBitmap(requestCode, resultCode, data)

        val base64 = treatPictures.encodeBase64(bitmap)


        Toast.makeText(applicationContext, "Test: $base64", Toast.LENGTH_LONG).show()

    }

    fun test1() {
        try{
            val httpRequest = HttpRequest(url)
            val tost = httpRequest.test(this)
            Toast.makeText(applicationContext, "o response é: "+ tost, Toast.LENGTH_LONG).show()
        }catch (error: Exception){
            Toast.makeText(applicationContext, "error" + error, Toast.LENGTH_LONG).show()

        }


    }

}


