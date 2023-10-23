package com.example.salman_ayaz_stressmeter.ui.stressselector

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.salman_ayaz_stressmeter.R
import com.example.salman_ayaz_stressmeter.databinding.FragmentStressSelectorBinding
import com.example.salman_ayaz_stressmeter.ui.ImageConfirmationActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class StressSelectorFragment : Fragment() {
    private lateinit var rootView: View
    private var _binding: FragmentStressSelectorBinding? = null
    private val stressSelectorViewModel by lazy { ViewModelProvider(this)[StressSelectorViewModel::class.java] }
    private var isVibrating: Boolean = false
    private var mediaPlayer: MediaPlayer? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStressSelectorBinding.inflate(inflater, container, false)
        val root: View = binding.root
        rootView = root

        setupImageGrid()

        root.findViewById<Button>(R.id.more_images).setOnClickListener() {
            changeImagePage()
        }

        stressSelectorViewModel.imagePage.observe(viewLifecycleOwner) {
            setupImageGrid()
        }

        soundAndVibrateObservers()

        return root
    }

    /**
     * Sets up the 4x4 grid of images based on the current imagePage
     */
    private fun setupImageGrid() {
        val gridLayout: GridLayout = rootView.findViewById(R.id.gridLayout)
        gridLayout.removeAllViews()
        // create and configure 4x4 grid
        val numRows = 4
        val numCols = 4

        for (i in 0 until numRows) {
            for (j in 0 until numCols) {
                val imageView = ImageView(rootView.context)
                val imageResource = stressSelectorViewModel.images[stressSelectorViewModel.imagePage.value!!][i][j][0]
                imageView.setImageResource(imageResource)
                imageView.layoutParams = GridLayout.LayoutParams().apply {
                    width = 0
                    height = 0
                    rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                    columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                }
                imageView.id = stressSelectorViewModel.images[stressSelectorViewModel.imagePage.value!!][i][j][0]
                imageView.setOnClickListener {
                    onImageClick(stressSelectorViewModel.images[stressSelectorViewModel.imagePage.value!!][i][j])
                }

                gridLayout.addView(imageView)
            }
        }
    }

    /**
     * opens another activity for the image that was clicked
     * @param imageId 
     * the id of the image that was clicked
     */
    private fun onImageClick(imageVals: IntArray) {
        val intent = Intent(requireContext(), ImageConfirmationActivity::class.java)
        intent.putExtra(ImageConfirmationActivity.IMAGE_ID_KEY, imageVals[0])
        intent.putExtra(ImageConfirmationActivity.IMAGE_STRESS_KEY, imageVals[1])

        stressSelectorViewModel.vibrate.value = false
        stressSelectorViewModel.playSound.value = false
        startActivity(intent)
    }

    /**
     * Changes the imagePage to the next page
     * 0 -> 1 -> 2 -> 0
     */
    private fun changeImagePage() {
        // (val + 1) % 3
        stressSelectorViewModel.imagePage.value =
            (stressSelectorViewModel.imagePage.value!! + 1).rem(3)
    }

    /**
     * Vibrates the phone
     */
    private fun startVibration() {
        isVibrating = true
        CoroutineScope(Dispatchers.IO).launch {
            val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            while (isVibrating) {
                if (Build.VERSION.SDK_INT >= 26) {
                    vibrator.vibrate(
                        VibrationEffect.createWaveform(
                            longArrayOf(200, 200),
                            0
                        )
                    )
                } else {
                    vibrator.vibrate(
                        longArrayOf(200, 200),
                        0
                    )
                }
                delay(800)
            }
        }
    }

    /**
     * stops the vibration
     */
    private fun stopVibration() {
        isVibrating = false
        val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.cancel()
    }

    /**
     * starts playing a alarm sound
     */
    private fun startSound() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, R.raw.clock_sound_effect)
        }

        if (!mediaPlayer?.isPlaying!!) {
            mediaPlayer?.isLooping = true
            mediaPlayer?.start()
        }
    }

    private fun stopSound() {
        if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
            mediaPlayer!!.stop();
            mediaPlayer!!.release();
            mediaPlayer = null;
        }
    }

    /**
     * Observes the vibrate and playSound variables in the stressSelectorViewModel
     * and starts/stops the vibration and sound accordingly
     * This is done so that the vibration and sound can be stopped when the user
     * navigates away from the fragment but not when the application is closed
     * and stops the vibration and sound from playing again when the user navigates
     * back to the fragment
     */
    private fun soundAndVibrateObservers() {
        stressSelectorViewModel.vibrate.observe(viewLifecycleOwner) {
            if (it == true) {
                startVibration()
            } else {
                stopVibration()
            }
        }
        stressSelectorViewModel.playSound.observe(viewLifecycleOwner) {
            if (it == true) {
                startSound()
            } else {
                stopSound()
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        stressSelectorViewModel.vibrate.value = false
        stressSelectorViewModel.playSound.value = false
        stopSound()
        stopVibration()
        _binding = null
    }
}