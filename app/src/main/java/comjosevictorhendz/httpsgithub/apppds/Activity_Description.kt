package comjosevictorhendz.httpsgithub.apppds

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity__description.*


class Activity_Description : AppCompatActivity() {

    private var description: String = ""
    internal lateinit var listen_description: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__description)

        listen_description = findViewById(R.id.image_label_detection)

        listen_description.setOnClickListener {
            Toast.makeText(applicationContext, "--speak--", Toast.LENGTH_LONG).show()
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
}
