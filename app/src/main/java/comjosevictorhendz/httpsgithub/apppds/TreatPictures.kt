package comjosevictorhendz.httpsgithub.apppds

import android.content.Intent
import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream


class TreatPictures {

    fun getBitmapAndEncodeForBase64(data: Intent?): String {
        val imageBitmap = data!!.extras.get("data") as Bitmap
        return encodeBase64(imageBitmap)
    }

    fun encodeBase64(bm: Bitmap): String {
        val ByteArray = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, ByteArray)
        val b = ByteArray.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

}