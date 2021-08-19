package com.snakelord.pets.kbsustudentassistance.presentation.common.extensions

import androidx.fragment.app.Fragment
import com.snakelord.pets.kbsustudentassistance.presentation.main.NavigationCallback

val Fragment.navigationCallback: NavigationCallback
    get() = activity as NavigationCallback