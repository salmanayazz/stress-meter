package com.example.stressmeter.ui.home

import android.os.Bundle
import android.provider.DocumentsContract.Root
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridLayout
import android.widget.GridView
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.stressmeter.R
import com.example.stressmeter.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    var images = arrayOf(
        arrayOf(
            intArrayOf(R.drawable.psm_alarm_clock, R.drawable.psm_alarm_clock2, R.drawable.psm_bar, R.drawable.psm_anxious),
            intArrayOf(R.drawable.psm_baby_sleeping, R.drawable.psm_bar, R.drawable.psm_barbed_wire2, R.drawable.psm_beach3),
            intArrayOf(R.drawable.psm_bird3, R.drawable.psm_blue_drop, R.drawable.psm_cat, R.drawable.psm_clutter),
            intArrayOf(R.drawable.psm_clutter3, R.drawable.psm_dog_sleeping, R.drawable.psm_exam4, R.drawable.psm_gambling4)
        ), arrayOf(
            intArrayOf(R.drawable.psm_headache, R.drawable.psm_headache2, R.drawable.psm_hiking3, R.drawable.psm_kettle),
            intArrayOf(R.drawable.psm_lake3, R.drawable.psm_lawn_chairs3, R.drawable.psm_lonely, R.drawable.psm_lonely2),
            intArrayOf(R.drawable.psm_mountains11, R.drawable.psm_neutral_child, R.drawable.psm_neutral_person2, R.drawable.psm_peaceful_person),
            intArrayOf(R.drawable.psm_puppy, R.drawable.psm_puppy3, R.drawable.psm_reading_in_bed2, R.drawable.psm_running3)
        ), arrayOf(
            intArrayOf(R.drawable.psm_running4, R.drawable.psm_sticky_notes2, R.drawable.psm_stressed_cat, R.drawable.psm_stressed_person),
            intArrayOf(R.drawable.psm_stressed_person3, R.drawable.psm_stressed_person4, R.drawable.psm_stressed_person6, R.drawable.psm_stressed_person7),
            intArrayOf(R.drawable.psm_stressed_person8, R.drawable.psm_stressed_person12, R.drawable.psm_talking_on_phone2, R.drawable.psm_to_do_list),
            intArrayOf(R.drawable.psm_to_do_list3, R.drawable.psm_wine3, R.drawable.psm_work4, R.drawable.psm_yoga4)
        )
    )


    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //setupGrid(root, 0)

        return root
    }

    fun setupGrid(root: View, imagePage: Int) {
        val gridLayout: GridLayout = root.findViewById(R.id.gridLayout)

        // Create and configure 4x4 grid
        val numRows = 4
        val numCols = 4

        for (i in 0 until numRows) {
            for (j in 0 until numCols) {
                val imageView = ImageView(root.context)
                val imageResource = images[imagePage][i][j]
                imageView.setImageResource(imageResource)
                imageView.layoutParams = GridLayout.LayoutParams().apply {
                    width = 0
                    height = GridLayout.LayoutParams.WRAP_CONTENT
                }
                imageView.id = View.generateViewId()
                imageView.setOnClickListener {
                    onImageClick(imageView.id)
                }

                gridLayout.addView(imageView)
            }
        }
    }

    private fun onImageClick(viewId: Int) {
        when (viewId) {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}