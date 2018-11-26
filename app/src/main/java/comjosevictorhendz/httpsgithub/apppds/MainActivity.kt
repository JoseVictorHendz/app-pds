@file:Suppress("DEPRECATION")

package comjosevictorhendz.httpsgithub.apppds

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.content.Intent
import android.widget.Button

class MainActivity : AppCompatActivity() {
    var url = ""
    internal lateinit var image_label_detection: Button
    internal lateinit var image_propertis_detection: Button
    internal lateinit var image_document_text_detection: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image_label_detection = findViewById(R.id.image_label_detection)
        image_propertis_detection = findViewById(R.id.image_propertis_detection)
        image_document_text_detection = findViewById(R.id.image_document_text_detection)


        image_label_detection.setOnClickListener {
            url = "https://api-back-pds.herokuapp.com/image-detection/image-label-detection/pt"
            takePicture()
        }

        image_propertis_detection.setOnClickListener {
            url = "https://api-back-pds.herokuapp.com/image-detection/image-properties-detection"
            takePicture()
        }

        image_document_text_detection.setOnClickListener {
            url = "https://api-back-pds.herokuapp.com/image-detection/image-document-text-detection"
            takePicture()
        }
    }

    val REQUEST_IMAGE_CAPTURE = 1

    private fun takePicture() {
//        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
//            takePictureIntent.resolveActivity(packageManager)?.also {
//                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
//            }
//        }
        val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        callCameraIntent.putExtra("android.intent.extra.quxickCapture",false);
        startActivityForResult(callCameraIntent, REQUEST_IMAGE_CAPTURE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val treatPictures = TreatPictures()

        val base64 = treatPictures.getBitmapAndEncodeForBase64(data)

        alternActivity(base64)
    }



    fun alternActivity(base64: String) {
        val intent = Intent(this, LoadingReturnApi::class.java)
        // To pass any data to next activity
        intent.putExtra("base64", base64)
        intent.putExtra("url", url)

        // start your next activity
        startActivity(intent)
    }
}


