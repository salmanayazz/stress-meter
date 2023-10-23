package com.example.stressmeter.ui.stressselector

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stressmeter.R

class StressSelectorViewModel : ViewModel() {
    var imagePage = MutableLiveData<Int>(0) // to toggle between 3 pages of images

    // images[imagePage][row][col][image=0/stressLevel=1]
    val images = arrayOf(
        arrayOf(
            arrayOf(
                intArrayOf(R.drawable.psm_alarm_clock, 5),
                intArrayOf(R.drawable.psm_alarm_clock2, 7),
                intArrayOf(R.drawable.psm_bar, 4),
                intArrayOf(R.drawable.psm_anxious, 8)
            ),
            arrayOf(
                intArrayOf(R.drawable.psm_baby_sleeping, 1),
                intArrayOf(R.drawable.psm_bar, 3),
                intArrayOf(R.drawable.psm_barbed_wire2, 7),
                intArrayOf(R.drawable.psm_beach3, 3)
            ),
            arrayOf(
                intArrayOf(R.drawable.psm_bird3, 3),
                intArrayOf(R.drawable.psm_blue_drop, 2),
                intArrayOf(R.drawable.psm_cat, 1),
                intArrayOf(R.drawable.psm_clutter, 5)
            ),
            arrayOf(
                intArrayOf(R.drawable.psm_clutter3, 4),
                intArrayOf(R.drawable.psm_dog_sleeping, 1),
                intArrayOf(R.drawable.psm_exam4, 8),
                intArrayOf(R.drawable.psm_gambling4, 9)
            ),
        ),
        arrayOf(
            arrayOf(
                intArrayOf(R.drawable.psm_headache, 7),
                intArrayOf(R.drawable.psm_headache2, 8),
                intArrayOf(R.drawable.psm_hiking3, 4),
                intArrayOf(R.drawable.psm_kettle, 3)
            ),
            arrayOf(
                intArrayOf(R.drawable.psm_lake3, 2),
                intArrayOf(R.drawable.psm_lawn_chairs3, 3),
                intArrayOf(R.drawable.psm_lonely, 6),
                intArrayOf(R.drawable.psm_lonely2, 7)
            ),
            arrayOf(
                intArrayOf(R.drawable.psm_mountains11, 3),
                intArrayOf(R.drawable.psm_neutral_child, 2),
                intArrayOf(R.drawable.psm_neutral_person2, 3),
                intArrayOf(R.drawable.psm_peaceful_person, 2)
            ),
            arrayOf(
                intArrayOf(R.drawable.psm_puppy, 4),
                intArrayOf(R.drawable.psm_puppy3, 3),
                intArrayOf(R.drawable.psm_reading_in_bed2, 2),
                intArrayOf(R.drawable.psm_running3, 7)
            ),
        ),
        arrayOf(
            arrayOf(
                intArrayOf(R.drawable.psm_running4, 8),
                intArrayOf(R.drawable.psm_sticky_notes2, 5),
                intArrayOf(R.drawable.psm_stressed_cat, 9),
                intArrayOf(R.drawable.psm_stressed_person, 10)
            ),
            arrayOf(
                intArrayOf(R.drawable.psm_stressed_person3, 9),
                intArrayOf(R.drawable.psm_stressed_person4, 10),
                intArrayOf(R.drawable.psm_stressed_person6, 8),
                intArrayOf(R.drawable.psm_stressed_person7, 7)
            ),
            arrayOf(
                intArrayOf(R.drawable.psm_stressed_person8, 10),
                intArrayOf(R.drawable.psm_stressed_person12, 9),
                intArrayOf(R.drawable.psm_talking_on_phone2, 7),
                intArrayOf(R.drawable.psm_to_do_list, 6)
            ),
            arrayOf(
                intArrayOf(R.drawable.psm_to_do_list3, 5),
                intArrayOf(R.drawable.psm_wine3, 8),
                intArrayOf(R.drawable.psm_work4, 6),
                intArrayOf(R.drawable.psm_yoga4, 3)
            )
        )
    )

    var vibrate = MutableLiveData<Boolean>(true);
    var playSound = MutableLiveData<Boolean>(true);
}