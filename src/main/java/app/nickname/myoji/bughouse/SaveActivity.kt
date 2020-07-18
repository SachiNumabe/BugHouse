package app.nickname.myoji.bughouse

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_save.*

class SaveActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save)

        val data :SharedPreferences = getSharedPreferences("Datastore", Context.MODE_PRIVATE)
        val editor = data.edit()
        saveButton.setOnClickListener {
            val text = input.text.toString()
            editor.putString("SAVE", text)
            editor.apply()
        }


    }




}
