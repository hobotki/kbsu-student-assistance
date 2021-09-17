package com.snakelord.pets.kbsustudentassistance.presentation.common.extensions

import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

fun BottomSheetDialogFragment.expand() {
    val viewGroup: ViewGroup = dialog!!.findViewById(
        com.google.android.material.R.id.design_bottom_sheet
    )
    val bottomSheetBehavior = BottomSheetBehavior.from(viewGroup)
    bottomSheetBehavior.expand()
}