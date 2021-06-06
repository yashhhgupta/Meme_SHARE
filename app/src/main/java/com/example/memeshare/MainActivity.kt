package com.example.memeshare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val memeButton = findViewById<Button>(R.id.memeButton)
        val GIFButton = findViewById<Button>(R.id.GIFButton)

        memeButton.setOnClickListener{
            Toast.makeText(this, "Random memes loading...", Toast.LENGTH_LONG).show()
            val abc = Intent(this, MemeActivity::class.java)
            startActivity(abc)
        }
        GIFButton.setOnClickListener{
            Toast.makeText(this, "Random GIFs loading...", Toast.LENGTH_LONG).show()
            val abc = Intent(this, GifActivity::class.java)
            startActivity(abc)
        }

    }
}