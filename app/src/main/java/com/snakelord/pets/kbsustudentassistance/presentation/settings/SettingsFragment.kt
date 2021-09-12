package com.snakelord.pets.kbsustudentassistance.presentation.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.snakelord.pets.kbsustudentassistance.R
import com.snakelord.pets.kbsustudentassistance.presentation.application.KbsuStudentAssistanceApp
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.navigationCallback
import com.snakelord.pets.kbsustudentassistance.presentation.common.state.UIStates
import com.snakelord.pets.kbsustudentassistance.presentation.settings.extensions.sharedPreferences

/**
 * Экран настроек
 *
 * @author Murad Luguev on 12-09-2021
 */
class SettingsFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    private val settingsViewModel: SettingsViewModel by navGraphViewModels(R.id.nav_graph) {
        KbsuStudentAssistanceApp.applicationComponent.viewModelFactory()
    }

    private var dialog: AlertDialog? = null
    private var modeListPreference: ListPreference? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.fragment_settings, rootKey)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settingsViewModel.uiStates.observe(viewLifecycleOwner, ::updateUIState)
        restoreModeSummary()
    }

    private fun restoreModeSummary() {
        modeListPreference = findPreference(getString(R.string.mode))

        modeListPreference?.summary = getString(
            settingsViewModel.getSummaryByTheme()
        )
    }

    override fun onResume() {
        super.onResume()
        sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    private fun updateUIState(state: UIStates) {
        if (state == UIStates.Successful) {
            findNavController()
                .navigate(R.id.action_settingsFragment_to_loginFragment)
            navigationCallback.hideBottomNavigationView()
        }
    }

    override fun onPreferenceTreeClick(preference: Preference): Boolean {
        if (preference.key == getString(R.string.logout_key)) {
                showLogoutDialog()
        }
        return super.onPreferenceTreeClick(preference)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        settingsViewModel.updateTheme()
        modeListPreference?.summary = getString(
            settingsViewModel.getSummaryByTheme()
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        dialog?.run {
            outState.putBoolean(IS_SHOWING_KEY, isShowing)
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        savedInstanceState?.run {
            if (savedInstanceState.getBoolean(IS_SHOWING_KEY)) {
                showLogoutDialog()
            }
        }
    }

    private fun showLogoutDialog() {
        dialog = AlertDialog.Builder(requireContext())
            .setTitle(R.string.warning)
            .setMessage(R.string.logout_message)
            .setPositiveButton(R.string.positive_answer) { _, _ ->
                settingsViewModel.logout()
            }
            .setNegativeButton(R.string.negative_answer) { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog!!.show()
    }

    override fun onStop() {
        super.onStop()

        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        modeListPreference = null

        dialog?.dismiss()
    }

    companion object {
        private const val IS_SHOWING_KEY = "is_showing"
    }
}