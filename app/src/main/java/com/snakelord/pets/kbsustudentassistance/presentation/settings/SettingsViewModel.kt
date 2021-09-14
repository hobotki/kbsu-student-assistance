package com.snakelord.pets.kbsustudentassistance.presentation.settings

import com.snakelord.pets.kbsustudentassistance.domain.interactor.settings.SettingsInteractor
import com.snakelord.pets.kbsustudentassistance.presentation.common.schedulers.SchedulersProvider
import com.snakelord.pets.kbsustudentassistance.presentation.common.state.UIStates
import com.snakelord.pets.kbsustudentassistance.presentation.common.theme.ThemeChanger
import com.snakelord.pets.kbsustudentassistance.presentation.common.viewmodel.BaseViewModel

/**
 * ViewModel для экрана настроек
 *
 * @property settingsInteractor интерактор для изменения настроек приложения
 * @property schedulersProvider проводник планировщиков для асинхронной работы
 *
 * @author Murad Luguev on 12-09-2021
 */
class SettingsViewModel(
    private val settingsInteractor: SettingsInteractor,
    private val schedulersProvider: SchedulersProvider,
    private val themeChanger: ThemeChanger
) : BaseViewModel() {

    /**
     * Функция для выхода из приложения
     */
    fun logout() {
        val logoutDisposable = settingsInteractor.logout()
            .observeOn(schedulersProvider.main())
            .subscribeOn(schedulersProvider.io())
            .subscribe {
                updateUIState(UIStates.Successful)
            }
        compositeDisposable.add(logoutDisposable)
    }

    /**
     * Функция для получения идентификатор строкового ресурса, описывающего текущую тему
     *
     * @return идентификатор строкового ресурса, описывающего текущую тему типа [Int]
     */
    fun getSummaryByTheme(): Int {
        return themeChanger.getSummaryByTheme()
    }

    /**
     * Функция для обновления темы приложения
     */
    fun updateTheme() {
        themeChanger.updateTheme()
    }
}