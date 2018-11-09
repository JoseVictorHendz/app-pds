@file:Suppress(   "DEPRECATION")

package comjosevictorhendz.httpsgithub.apppds

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.content.Intent
import android.graphics.Bitmap
import android.widget.Button
import android.widget.Toast
import org.json.JSONObject



class MainActivity : AppCompatActivity() {
    var url = "https://api-back-pds.herokuapp.com"
    internal lateinit var image_label_detection: Button




    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image_label_detection = findViewById(R.id.image_label_detection)

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
        val bitmap: Bitmap

        val treatPictures = TreatPictures()
//
        bitmap = treatPictures.getBitmap(requestCode, resultCode, data)

        val base64 = treatPictures.encodeBase64(bitmap)

        val jsonBody = jsonConstruction(base64)
        httpRequest(jsonBody)
        Toast.makeText(applicationContext, "Test: $base64", Toast.LENGTH_LONG).show()

    }

    fun jsonConstruction(value: String): JSONObject {
        var jsonBody = JSONObject("{\"value\":\""+ value + "\"}");

        return jsonBody
    }

    fun httpRequest(jsonBody: JSONObject) {
        try{
            val httpRequest = HttpRequest(url, jsonBody)
            httpRequest.requestApi(this)
        }catch (error: Exception){
            Toast.makeText(applicationContext, "error" + error, Toast.LENGTH_LONG).show()

        }


    }

}


