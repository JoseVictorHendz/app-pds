package comjosevictorhendz.httpsgithub.apppds

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity__description.*


class Activity_Description : AppCompatActivity() {

    private var description: String = ""
    internal lateinit var listen_description: Button
    internal lateinit var return_menu: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__description)

        listen_description = findViewById(R.id.listen_description)
        return_menu = findViewById(R.id.return_menu)


        listen_description.setOnClickListener {
            Toast.makeText(applicationContext, "--speak-- " + description, Toast.LENGTH_LONG).show()
        }

        return_menu.setOnClickListener {
            alternActivity()
        }

//        //Pega a intent de outra activity
        val it = intent
//
//        //Recuperei a string da outra activity
        val array = it.getStringArrayExtra("array")
        for (i in array) {
            if(i != "voidReturn") {
                description += i + ", "
                createTextViewDynamically(i)
            }
        }


    }

    fun createTextViewDynamically(description: String) {
        // creating TextView programmatically
        val tv_dynamic = TextView(this)
        tv_dynamic.textSize = 20f
        tv_dynamic.text = description

        // add TextView to LinearLayout
        description_layout.addView(tv_dynamic)
    }

    fun alternActivity() {
        val intent = Intent(this, MainActivity::class.java)
        // start your next activity
        startActivity(intent)
    }
}
