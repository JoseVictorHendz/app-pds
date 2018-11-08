package comjosevictorhendz.httpsgithub.apppds

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class HttpRequest {

    private var url: String

    constructor(url: String) {
        this.url = url
    }

    fun test(context: Context): String {
        val queue = Volley.newRequestQueue(context)
        var jsonBody = JSONObject("{\"value\":\"workingtest\"}");

        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, this.url, jsonBody,
                Response.Listener { response ->
                    Toast.makeText(context, "o response é: "+ response, Toast.LENGTH_LONG).show()
                },
                Response.ErrorListener { error ->
                    // TODO: Handle error
                }
        )

// Access the RequestQueue through your singleton class.
        queue.add(jsonObjectRequest)
        return ""
    }
}
