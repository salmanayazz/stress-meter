package com.example.stressmeter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.stressmeter.R
import com.example.stressmeter.ui.stressrecord.StressRecordViewModel

class ImageConfirmationActivity : AppCompatActivity() {
    companion object {
        const val IMAGE_ID_KEY = "image_id"
        const val IMAGE_STRESS_KEY = "image_stress"
    }

    private val stressRecordViewModel by lazy { ViewModelProvider(this)[StressRecordViewModel::class.java] }
    private val imageId by lazy { intent.getIntExtra(IMAGE_ID_KEY, -1) }
    private val imageStressLvl by lazy { intent.getIntExtra(IMAGE_STRESS_KEY, -1) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_confirmation)

        // update values on ui
        if (imageId != -1 && imageStressLvl != -1) {
            findViewById<ImageView>(R.id.confirm_image_view).setImageResource(
                imageId
            )
            findViewById<TextView>(R.id.stress_level).text = "Stress Level: $imageStressLvl"
        }
        
        setupListeners()
    }

    /*
    * Sets up the listeners for the cancel and submit buttons
    */
    private fun setupListeners() {
        findViewById<Button>(R.id.cancel_image).setOnClickListener() {
            this.finishAffinity();
        }
        findViewById<Button>(R.id.submit_image).setOnClickListener() {
            stressRecordViewModel.addRecord(this, imageStressLvl)
            this.finishAffinity();
        }
    }
}