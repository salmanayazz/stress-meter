package com.example.stressmeter.ui.stressrecord

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.stressmeter.R
import com.example.stressmeter.databinding.FragmentStressRecordBinding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet


class StressRecordFragment : Fragment() {

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

        val chart = root.findViewById<LineChart>(R.id.chart1)


        val values = ArrayList<Entry>()
        values.add(Entry(2F, 3F))
        values.add(Entry(4F, 10F))
        val set1 = LineDataSet(values, "DataSet 1")
        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(set1)
        val lineData = LineData(dataSets)
        chart.data = lineData
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}