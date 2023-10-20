package com.example.stressmeter.ui.stressrecord

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException


class StressRecordViewModel : ViewModel() {
    private val fileName = "stress_timestamp.csv"
    val stressRecordData: MutableLiveData<ArrayList<Int>> = MutableLiveData(ArrayList())

    /**
     * Add a new record to the stressRecordData var and save it to the file
     * @param context 
     * the context of the activity
     * @param value 
     * the value to be added to the stressRecordData var
     */
    fun addRecord(context: Context, value: Int) {
        val bw: BufferedWriter? = null
        try {
            stressRecordData.value?.add(value)
            val file = File(context.getExternalFilesDir(null), fileName)
            if (!file.exists()) {
                file.createNewFile()
            } else { // load its contents if it exists
                loadRecords(context)
            }

            val fw = FileWriter(file.absoluteFile)
            val bw = BufferedWriter(fw)
            // turn the int array onto a string separated by ","
            bw.write(stressRecordData.value?.joinToString(",") ?: "")
            bw.close()
            println("done ${stressRecordData.value}")
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                bw?.close()
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
        }
    }

    fun loadRecords(context: Context) {
        var br: BufferedReader? = null
        try {
            val file = File(context.getExternalFilesDir(null), fileName)
            if (!file.exists()) { return }

            // save file values to stressRecordsData var
            br = BufferedReader(FileReader(file))
            br.readLine().also { str ->
                var strValues = str.split(",")
                strValues.forEach { num ->
                    stressRecordData.value?.add(num.toInt())
                }
            }
            println("done loading: ${stressRecordData.value}")
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                br?.close()
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
        }
    }
}