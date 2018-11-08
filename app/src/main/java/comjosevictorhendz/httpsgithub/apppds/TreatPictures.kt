package comjosevictorhendz.httpsgithub.apppds

import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import java.io.ByteArrayOutputStream


class TreatPictures {

    fun getBitmap(requestCode: Int, resultCode: Int, data: Intent?): Bitmap {
        val imageBitmap = data!!.extras.get("data") as Bitmap
        return imageBitmap
    }

    fun encodeBase64(bm: Bitmap): String {
        val ByteArray = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, ByteArray)
        val b = ByteArray.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

}