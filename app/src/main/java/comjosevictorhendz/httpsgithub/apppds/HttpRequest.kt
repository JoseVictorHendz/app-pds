package comjosevictorhendz.httpsgithub.apppds

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class HttpRequest {

    fun test(context: Context): String {
        var responseAPI = "nada"

        val queue = Volley.newRequestQueue(context)
        val url = "https://api-back-pds.herokuapp.com"

// Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response -> Toast.makeText(context, "o response é: "+ response, Toast.LENGTH_LONG).show()

                responseAPI = response
            },
            Response.ErrorListener {})

        queue.add(stringRequest)
        return responseAPI
    }

}
