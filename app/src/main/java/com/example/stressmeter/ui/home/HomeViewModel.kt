package com.example.stressmeter.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stressmeter.R

class HomeViewModel : ViewModel() {
    var imagePage = MutableLiveData<Int>(0) // to toggle between 3 pages of images
    val images = arrayOf(
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
    var selectedImageId = MutableLiveData<Int>(images[0][0][0])
}