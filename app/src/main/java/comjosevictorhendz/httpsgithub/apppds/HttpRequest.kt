package comjosevictorhendz.httpsgithub.apppds

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class HttpRequest {

    private var url: String
    private var jsonBody: JSONObject

    constructor(url: String, jsonBody: JSONObject) {
        this.url = url
        this.jsonBody = jsonBody
    }

    fun requestApi(context: Context): String {
        val queue = Volley.newRequestQueue(context)

        val jsonObjectRequest = post(context)

        queue.add(jsonObjectRequest)

        return ""
    }

    fun post(context: Context): JsonObjectRequest {
        return JsonObjectRequest(Request.Method.POST, url, jsonBody,
                Response.Listener { response ->
                    Toast.makeText(context, "the response is: "+ response, Toast.LENGTH_LONG).show()
                },
                Response.ErrorListener { error ->
                    // TODO: Handle error
                }
        )
    }
}
