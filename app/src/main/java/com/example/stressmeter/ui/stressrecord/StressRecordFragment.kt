package com.example.stressmeter.ui.stressrecord

import android.Manifest
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.stressmeter.R
import com.example.stressmeter.databinding.FragmentStressRecordBinding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter

class StressRecordFragment : Fragment() {
    private lateinit var rootView: View
    private val stressRecordViewModel by lazy { ViewModelProvider(this)[StressRecordViewModel::class.java] }
    private var _binding: FragmentStressRecordBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val stressRecordViewModel =
            ViewModelProvider(this)[StressRecordViewModel::class.java]

        _binding = FragmentStressRecordBinding.inflate(inflater, container, false)
        val root: View = binding.root
        rootView = root

        stressRecordViewModel.loadRecords(root.context)
        ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)

        populateChart()
        populateTable()
        return root
    }

    /**
     * Populates the chart with the stress records
     */
    private fun populateChart() {
        val chart = rootView.findViewById<LineChart>(R.id.stress_chart)
        val stressRecords = stressRecordViewModel.stressRecords

        stressRecords.observe(viewLifecycleOwner) { arr ->
            val values = ArrayList<Entry>()

            arr.forEachIndexed { index, e ->
                values.add(Entry(index.toFloat(), e.stressLevel.toFloat()))
            }

            val dataSet = LineDataSet(values, "Stress Level")
            val lineData = LineData(dataSet)
            chart.data = lineData

            // customize chart appearance
            chart.description.isEnabled = false
            chart.setDrawGridBackground(false)

            // customize the X-axis
            chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
            chart.xAxis.setDrawGridLines(false)

            // customize the Y-axis
            chart.axisLeft.setDrawGridLines(false)
            chart.axisRight.isEnabled = false

            chart.invalidate()
        }
    }


    /**
     * Populates the table with the stress records
     */
    private fun populateTable() {
        val tableLayout = rootView.findViewById<TableLayout>(R.id.stress_table)

        stressRecordViewModel.stressRecords.observe(viewLifecycleOwner) { arr ->
            arr.forEach {
                // create TextViews and add them to the row
                val tableRow = TableRow(context)
                tableRow.gravity = Gravity.CENTER
                tableLayout.layoutParams = TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT
                )

                val timeTextView = TextView(context)
                timeTextView.text = it.time
                timeTextView.layoutParams = TableRow.LayoutParams(
                    0,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
                )
                timeTextView.gravity = Gravity.CENTER
                timeTextView.textSize = 14f
                timeTextView.setTypeface(null, Typeface.BOLD)
                timeTextView.setPadding(8, 8, 8, 8)

                val stressLevelTextView = TextView(context)
                stressLevelTextView.text = it.stressLevel.toString()
                stressLevelTextView.layoutParams = TableRow.LayoutParams(
                    0,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
                )
                stressLevelTextView.gravity = Gravity.CENTER
                stressLevelTextView.textSize = 14f
                stressLevelTextView.setTypeface(null, Typeface.BOLD)
                stressLevelTextView.setPadding(8, 8, 8, 8)

                tableRow.addView(timeTextView)
                tableRow.addView(stressLevelTextView)

                // set right under the header
                tableLayout.addView(tableRow, 1)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}