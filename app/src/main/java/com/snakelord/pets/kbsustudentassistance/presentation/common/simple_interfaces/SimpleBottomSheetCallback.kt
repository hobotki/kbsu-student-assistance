package com.snakelord.pets.kbsustudentassistance.presentation.common.simple_interfaces

import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior

abstract class SimpleBottomSheetCallback : BottomSheetBehavior.BottomSheetCallback() {
    override fun onStateChanged(bottomSheet: View, newState: Int) {
        //Реализовать по мере необходимости
    }

    override fun onSlide(bottomSheet: View, slideOffset: Float) {
        //Реализовать по мере необходимости
    }
}