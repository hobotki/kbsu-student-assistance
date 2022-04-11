package com.snakelord.pets.kbsustudentassistance.pass.presentation

import android.graphics.Bitmap
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.snakelord.pets.kbsustudentassistance.domain.interactor.pass.PassInteractor
import com.snakelord.pets.kbsustudentassistance.domain.model.pass.Student
import com.snakelord.pets.kbsustudentassistance.presentation.common.schedulers.SchedulersProviderTest
import com.snakelord.pets.kbsustudentassistance.presentation.pass.PassViewModel
import com.snakelord.pets.kbsustudentassistance.presentation.pass.utils.BitmapUtil
import io.mockk.*
import io.reactivex.rxjava3.core.Single
import org.junit.Rule
import org.junit.Test

class PassViewModelTest {

    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    private val passInteractor: PassInteractor = mockk(relaxed = true)
    private val schedulersProvider = SchedulersProviderTest()
    private val studentObserver: Observer<Student> = mockk()
    private val bitmap: Bitmap = mockk()
    private val passViewModel = PassViewModel(passInteractor, schedulersProvider)

    @Test
    fun testQrCodeGeneration() {
        //Arrange
        every { passInteractor.getStudentData() } returns Single.just(EXPECTED_STUDENT)
        every { passInteractor.convertStudentToJson(any()) } returns EXPECTED_RESULT
        every { studentObserver.onChanged(any()) } just Runs
        passViewModel.student.observeForever(studentObserver)

        mockkObject(BitmapUtil)
        every { BitmapUtil.generateQrCodeBitmap(any(), any()) } returns bitmap

        val expectedResult = EXPECTED_STUDENT

        //Act
        passViewModel.getQrCode(RANDOM_SIZE)

        //Assert
        verify {
            studentObserver.onChanged(expectedResult)
        }
    }

    companion object {
        private const val RANDOM_SIZE = 757

        private val EXPECTED_STUDENT = Student(
            fullName = "Иванов Иван Иванович",
            id = 3,
            specialityCode = "09.03.01-3"
        )

        private const val EXPECTED_RESULT =
            """{"id":3,"fullName":"Иванов Иван Иванович","specialityCode":"09.03.01-3"}"""
    }
}
