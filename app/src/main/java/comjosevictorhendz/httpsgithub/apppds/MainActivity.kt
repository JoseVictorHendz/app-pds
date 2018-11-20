@file:Suppress("DEPRECATION")

package comjosevictorhendz.httpsgithub.apppds

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.widget.Button
import android.widget.Toast
import org.json.JSONObject
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

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
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val treatPictures = TreatPictures()

        val base64 = treatPictures.getBitmapAndEncodeForBase64(requestCode, resultCode, data)

        val jsonBody = jsonConstruction(base64)
        httpRequest(jsonBody)
    }

    fun jsonConstruction(value: String): JSONObject {
        var jsonBody = JSONObject("{\"value\":\"" + value + "\"}");

        return jsonBody
    }

    // api---------------------------------------------------------------------
    fun httpRequest(jsonBody: JSONObject) {
        try {

            requestApi(jsonBody)

        } catch (error: Exception) {
            Toast.makeText(applicationContext, "error" + error, Toast.LENGTH_LONG).show()

        }

    }

    fun requestApi(jsonBody: JSONObject) {
        val queue = Volley.newRequestQueue(this)

        val jsonObjectRequest = post(jsonBody)

        queue.add(jsonObjectRequest)
    }

    fun post(jsonBody: JSONObject): JsonObjectRequest {
        return JsonObjectRequest(Request.Method.POST, url, jsonBody,
                Response.Listener { response ->
                    Toast.makeText(applicationContext, "the response is: " + response, Toast.LENGTH_LONG).show()
                    jsonForArray(response)

                },
                Response.ErrorListener { error ->
                    // TODO: Handle error
                }
        )
    }

    fun jsonForArray(jsonObject: JSONObject) {

        var array: Array<String> = Array(10) {""}

        for (i in 0..9) {
            array[i] = jsonObject.get("value"+i).toString()
        }

        alternActivity(array)
    }

    fun alternActivity(array: Array<String>) {
        val intent = Intent(this, Activity_Description::class.java)
        // To pass any data to next activity
        intent.putExtra("array", array)
        // start your next activity
        startActivity(intent)
    }


}


