package comjosevictorhendz.httpsgithub.apppds

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class LoadingReturnApi : AppCompatActivity() {
    var url = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_return_api)

        val it = intent
        val base64 = it.getStringExtra("base64")
        this.url = it.getStringExtra("url")
        val jsonObject: JSONObject = jsonConstruction(base64)

        httpRequest(jsonObject)
    }

    fun jsonConstruction(base64: String): JSONObject {
        var jsonBody = JSONObject("{\"value\":\"" + base64 + "\"}");

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
                    //                    Toast.makeText(applicationContext, "the response is: " + response, Toast.LENGTH_LONG).show()
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
        val intent = Intent(this, Description::class.java)
        // To pass any data to next activity
        intent.putExtra("array", array)
        // start your next activity
        startActivity(intent)
    }
}
