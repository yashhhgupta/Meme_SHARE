package com.example.memeshare

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class GifActivity : AppCompatActivity() {
    var presentImageUrl: String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gif)
        loadgif()
    }
    private fun loadgif() {
        val progressBar: ProgressBar =findViewById(R.id.progressBar)
        progressBar.visibility= View.VISIBLE
        val gif: ImageView =findViewById(R.id.gif)
        val queue = Volley.newRequestQueue(this)

        val url = "https://api.giphy.com/v1/gifs/random?api_key=66BTs9VPMjCBgIMjr4ZJo00qmEiiDitk&tag=&rating=g"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val temp= response.getJSONObject("data").getJSONObject("images").getJSONObject("original")

                presentImageUrl = temp.getString("url")

                Glide.with(this).load(presentImageUrl).listener(object: RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility= View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility= View.GONE
                        return false

                    }
                }).into(gif)
            }
        ) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
        }
        queue.add(jsonObjectRequest)
    }

    fun sharegif(view: View) {
        val intent= Intent(Intent.ACTION_SEND)
        intent.type="text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,"Hey, Checkout this GIF $presentImageUrl")
        val chooser = Intent.createChooser(intent,"Share this GIF using")
        startActivity(chooser)
    }
    fun nextgif(view: View) {
        loadgif()
    }
}