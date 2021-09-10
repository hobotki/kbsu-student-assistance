package com.snakelord.pets.kbsustudentassistance.presentation.pass

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.snakelord.pets.kbsustudentassistance.domain.interactor.pass.PassInteractor
import com.snakelord.pets.kbsustudentassistance.domain.model.pass.Student
import com.snakelord.pets.kbsustudentassistance.presentation.common.schedulers.SchedulersProvider
import com.snakelord.pets.kbsustudentassistance.presentation.common.state.UIStates
import com.snakelord.pets.kbsustudentassistance.presentation.common.viewmodel.BaseViewModel

/**
 * ViewModel для экрана пропуска
 *
 * @property passInteractor интерактор для получения данных пользователя и генерации QR-кода
 * @property schedulersProvider проводник планировщиков для асинхронной работы
 *
 * @author Murad Luguev on 11-09-2021
 */
class PassViewModel(
    private val passInteractor: PassInteractor,
    private val schedulersProvider: SchedulersProvider
) : BaseViewModel() {

    private val studentMutableLiveData = MutableLiveData<Student>()
    val student: LiveData<Student>
        get() = studentMutableLiveData

    private val qrCodeBitmapMutableLiveData = MutableLiveData<Bitmap>()
    val qrcodeBitmap: LiveData<Bitmap>
        get() = qrCodeBitmapMutableLiveData

    /**
     * Функция, для получения QR-кода
     *
     * @param imageViewSize размер ImageView, в который будет помещён QR-код
     */
    fun getQrCode(imageViewSize: Int) {
        if (qrCodeBitmapMutableLiveData.value == null) {
            updateUIState(UIStates.Loading)
            val getDataDisposable = passInteractor.getStudentData()
                .observeOn(schedulersProvider.main())
                .subscribeOn(schedulersProvider.io())
                .subscribe { student ->
                    studentMutableLiveData.value = student
                    generateQrCode(student, imageViewSize)
                }
            compositeDisposable.add(getDataDisposable)
        }
    }

    private fun generateQrCode(student: Student, size: Int) {
        val qrCodeDisposable = passInteractor.getQrCode(student, size)
            .observeOn(schedulersProvider.main())
            .subscribeOn(schedulersProvider.io())
            .subscribe {
                bitmap -> qrCodeBitmapMutableLiveData.value = bitmap
                updateUIState(UIStates.Successful)
            }
        compositeDisposable.add(qrCodeDisposable)
    }
}