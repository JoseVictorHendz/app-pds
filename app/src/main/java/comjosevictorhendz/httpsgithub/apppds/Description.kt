package comjosevictorhendz.httpsgithub.apppds

import android.content.Intent
import android.media.AudioManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity__description.*
import java.util.*


class Description : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var description: String = ""
    private var tts: TextToSpeech? = null
    internal lateinit var listen_description: Button
    internal lateinit var return_menu: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__description)
        tts = TextToSpeech(this,this)

        listen_description = findViewById(R.id.listen_description)
        return_menu = findViewById(R.id.return_menu)


        listen_description.setOnClickListener {
            Toast.makeText(applicationContext, "--speak-- " + description, Toast.LENGTH_LONG).show()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                tts!!.speak(description, TextToSpeech.QUEUE_FLUSH,null,null)
            } else {
                val hash = HashMap<String,String>()
                hash.put(TextToSpeech.Engine.KEY_PARAM_STREAM,
                        AudioManager.STREAM_NOTIFICATION.toString())
                tts!!.speak(description, TextToSpeech.QUEUE_FLUSH,hash)
            }
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

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale("PT", "BR"))
            if(result != TextToSpeech.LANG_MISSING_DATA ||
                    result != TextToSpeech.LANG_NOT_SUPPORTED) {
                listen_description!!.isEnabled=true
            }
        }
    }

    public override fun onDestroy() {
        if(tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
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
