package com.example.stressmeter.ui.stressselector

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.stressmeter.R
import com.example.stressmeter.databinding.FragmentStressSelectorBinding
import com.example.stressmeter.ui.ImageConfirmationActivity

class StressSelectorFragment : Fragment() {
    private lateinit var rootView: View
    private var _binding: FragmentStressSelectorBinding? = null
    private val stressSelectorViewModel by lazy { ViewModelProvider(this)[StressSelectorViewModel::class.java] }

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
                val imageResource = stressSelectorViewModel.images[stressSelectorViewModel.imagePage.value!!][i][j]
                imageView.setImageResource(imageResource)
                imageView.layoutParams = GridLayout.LayoutParams().apply {
                    width = 0
                    height = 0
                    rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                    columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                }
                imageView.id = stressSelectorViewModel.images[stressSelectorViewModel.imagePage.value!!][i][j]
                imageView.setOnClickListener {
                    onImageClick(imageView.id)
                }

                gridLayout.addView(imageView)
            }
        }
    }

    /**
     * opens another activity for the image that was clicked
     * @param imageId the id of the image that was clicked
     */
    private fun onImageClick(imageId: Int) {
        val intent = Intent(requireContext(), ImageConfirmationActivity::class.java)
        intent.putExtra(ImageConfirmationActivity.IMAGE_ID_KEY, imageId)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}