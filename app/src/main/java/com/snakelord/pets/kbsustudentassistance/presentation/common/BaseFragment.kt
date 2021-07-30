package com.snakelord.pets.kbsustudentassistance.presentation.common

import androidx.fragment.app.Fragment
import com.snakelord.pets.kbsustudentassistance.presentation.common.callback.OnConnectionCallback

abstract class BaseFragment : Fragment(), OnConnectionCallback {

    protected var isConnected = false

    override fun onConnectionAvailable() {
        isConnected = true
    }

    override fun onConnectionLost() {
        isConnected = false
    }

}