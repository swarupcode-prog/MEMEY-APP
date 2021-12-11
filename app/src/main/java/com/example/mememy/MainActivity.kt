package com.example.mememy


import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


class MainActivity : AppCompatActivity() {

    var currentImageUrl:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val share = findViewById<Button>(R.id.shareButton)
        share.setOnClickListener {

            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain" // helps in showing the apps which can share a text
            intent.putExtra(Intent.EXTRA_TEXT,"Hey,Checkout This Cool meme I got from Reddit $currentImageUrl")
            val chooser = Intent.createChooser(intent,"Share This meme using ...")
            startActivity(chooser)
        }

        val next = findViewById<Button>(R.id.nextButton)
        next.setOnClickListener {
            loadMeme() //loads Meme On Clicking Next Button
        }

        loadMeme()
    }


    private fun loadMeme()
    {

        val inputProgressBar = findViewById<ProgressBar>(R.id.progressBar)
        inputProgressBar.visibility= View.VISIBLE

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.herokuapp.com/gimme"

         // Request a json response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                currentImageUrl = response.getString("url")

                val inputImage: ImageView = findViewById(R.id.memeImageview)

                Glide.with(this).load(currentImageUrl).placeholder(android.R.drawable.progress_indeterminate_horizontal).error(android.R.drawable.stat_notify_error).listener(object: RequestListener<Drawable>
                {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        inputProgressBar.visibility=View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        inputProgressBar.visibility=View.GONE
                        return false
                    }
                }
                ).into(inputImage);

                Log.d("JsonResponnse", "To Check What happens")
            },
            { error ->
                Log.d("TAG", "loadMeme: ${error.localizedMessage}")
                Toast.makeText(this, "Try Connecting to internet", Toast.LENGTH_SHORT).show()
            }
        )

       // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }


}


