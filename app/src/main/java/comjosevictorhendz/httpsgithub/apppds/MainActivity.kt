@file:Suppress(   "DEPRECATION")

package comjosevictorhendz.httpsgithub.apppds

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import 	android.provider.MediaStore
import android.content.Intent
import android.widget.Button
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    internal lateinit var image_label_detection: Button


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image_label_detection = findViewById(R.id.image_label_detection)




        val REQUEST_IMAGE_CAPTURE = 1

        fun dispatchTakePictureIntent() {
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                takePictureIntent.resolveActivity(packageManager)?.also {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }

        image_label_detection.setOnClickListener {
            dispatchTakePictureIntent()
        }


    }


}




