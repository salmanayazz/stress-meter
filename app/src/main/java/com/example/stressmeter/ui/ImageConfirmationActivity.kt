package com.example.stressmeter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.stressmeter.R

class ImageConfirmationActivity : AppCompatActivity() {
    companion object {
        const val IMAGE_ID_KEY = "image_id"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_confirmation)

        val imageId = intent.getIntExtra(IMAGE_ID_KEY, -1)

        if (imageId != -1) {
            findViewById<ImageView>(R.id.confirm_image_view).setImageResource(
                imageId
            )
        }

        findViewById<Button>(R.id.cancel_image).setOnClickListener() {
            finish()
        }

        findViewById<Button>(R.id.submit_image).setOnClickListener() {
            finish()
        }
    }
}