package com.snakelord.pets.kbsustudentassistance.common.theme

import android.content.Context
import android.content.SharedPreferences
import com.google.common.truth.Truth
import com.snakelord.pets.kbsustudentassistance.R
import com.snakelord.pets.kbsustudentassistance.presentation.common.theme.ThemeChanger
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class ThemeChangerTest {

    private val context: Context = mockk()
    private val settingsSharedPreferences: SharedPreferences = mockk()
    private val themeChanger = ThemeChanger(context, settingsSharedPreferences)

    @Before
    fun setUp() {
        every { context.getString(any()) } returns MODE_PREFERENCE_NAME
    }

    @Test
    fun getSummaryByThemeSystemTest() {
        //Arrange
        every { settingsSharedPreferences.getString(any(), any()) } returns SYSTEM_DEFAULT_MODE
        val expectedResult = SYSTEM_DEFAULT_SUMMARY

        //Act
        val actualResult = themeChanger.getSummaryByTheme()

        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun getSummaryByThemeLightTest() {
        //Arrange
        every { settingsSharedPreferences.getString(any(), any()) } returns LIGHT_MODE
        val expectedResult = LIGHT_MODE_SUMMARY

        //Act
        val actualResult = themeChanger.getSummaryByTheme()

        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun getSummaryByThemeDarkTest() {
        //Arrange
        every { settingsSharedPreferences.getString(any(), any()) } returns DARK_MODE
        val expectedResult = DARK_MODE_SUMMARY

        //Act
        val actualResult = themeChanger.getSummaryByTheme()

        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }


    @Test
    fun isDarkThemeTestDark() {
        //Arrange
        every { settingsSharedPreferences.getString(any(), any()) } returns DARK_MODE
        val expectedResult = true

        //Act
        val actualResult = themeChanger.isAppInDarkTheme()

        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun isDarkThemeTestLight() {
        //Arrange
        every { settingsSharedPreferences.getString(any(), any()) } returns LIGHT_MODE
        val expectedResult = false

        //Act
        val actualResult = themeChanger.isAppInDarkTheme()

        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    companion object {
        private const val MODE_PREFERENCE_NAME = "mode"

        private const val SYSTEM_DEFAULT_MODE = "system"
        private const val LIGHT_MODE = "light"
        private const val DARK_MODE = "dark"

        private const val SYSTEM_DEFAULT_SUMMARY = R.string.system_default_summary
        private const val LIGHT_MODE_SUMMARY = R.string.light_summary
        private const val DARK_MODE_SUMMARY = R.string.dark_summary
    }
}