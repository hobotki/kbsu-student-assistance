package com.snakelord.pets.kbsustudentassistance.presentation.common.state

import com.snakelord.pets.kbsustudentassistance.domain.model.OperationError


sealed class UIStates {
    object Loading: UIStates()
    object Successful: UIStates()
    data class Error(val error: OperationError): UIStates()
}