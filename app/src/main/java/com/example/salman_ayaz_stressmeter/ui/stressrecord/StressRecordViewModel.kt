package com.example.salman_ayaz_stressmeter.ui.stressrecord

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException
import java.util.Calendar

data class StressRecord(val stressLevel: Int, val time: String)

class StressRecordViewModel : ViewModel() {
    private val fileName = "stress_timestamp.csv"
    val stressRecords: MutableLiveData<ArrayList<StressRecord>> = MutableLiveData(ArrayList())

    /**
     * Adds a new record to the file
     * @param context
     * The context to save the file to
     * @param stressLevel
     * The stress value to save (1 - 10)
     */
    fun addRecord(context: Context, stressLevel: Int) {
        try {
            val currentTime = Calendar.getInstance().time
            val newRecord = StressRecord(stressLevel, currentTime.toString())

            loadRecords(context)
            stressRecords.value?.add(newRecord)

            // re-save all records to file
            val file = File(context.getExternalFilesDir(null), fileName)
            val fw = FileWriter(file)
            val bw = BufferedWriter(fw)

            stressRecords.value?.forEach { record ->
                bw.write("${record.stressLevel},${record.time}\n")
            }

            bw.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**
     * Loads the records from the file and updates the LiveData
     * @param context 
     * The context to load the file from
     */
    fun loadRecords(context: Context) {
        try {
            val file = File(context.getExternalFilesDir(null), fileName)
            if (file.exists()) {
                val stressRecordList = mutableListOf<StressRecord>()

                val br = BufferedReader(FileReader(file))
                br.useLines { lines ->
                    lines.forEach { line ->
                        val split = line.split(",")
                        if (split.size == 2) {
                            stressRecordList.add(
                                StressRecord(
                                    split[0].toInt(),
                                    split[1]
                                )
                            )
                        }
                    }
                }

                // Update LiveData with the loaded records
                stressRecords.value = ArrayList(stressRecordList)
                println("done: ${stressRecords.value}")
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}