package app.nickname.myoji.bughouse

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import coil.api.load
import kotlinx.android.synthetic.main.activity_image_view.*
import java.io.FileDescriptor
import java.io.IOException

class ImageViewActivity : AppCompatActivity() {

    var stringUri: String = ""

    private val RESULT_PICK_IMAGEFILE = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_view)

        val url = "https://life-is-tech.com/materials/images/summer2019_desktop_3.jpg"
        val imageView = findViewById<ImageView>(R.id.imageView)
        imageView.load(url)

        getImageButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "image/*"
            startActivityForResult(intent, RESULT_PICK_IMAGEFILE)
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        if (requestCode == RESULT_PICK_IMAGEFILE && resultCode == Activity.RESULT_OK) {
            var uri: Uri? = null
            if (resultData != null) {
                uri = resultData.data
                if(uri != null){
                    try {
                        stringUri = uri.toString()
                        val bmp = getBitmapFromUri(uri)
                        imageView.setImageBitmap(bmp)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }

        }
    }

    @Throws(IOException::class)
    private fun getBitmapFromUri(uri: Uri): Bitmap {
        val parcelFileDescriptor =
            contentResolver.openFileDescriptor(uri, "r")
        val fileDescriptor: FileDescriptor = parcelFileDescriptor!!.fileDescriptor
        val image = BitmapFactory.decodeFileDescriptor(fileDescriptor)
        parcelFileDescriptor.close()

        return image

    }
}
