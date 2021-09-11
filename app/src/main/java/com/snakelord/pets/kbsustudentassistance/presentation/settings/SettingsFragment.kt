package com.snakelord.pets.kbsustudentassistance.presentation.settings

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.snakelord.pets.kbsustudentassistance.R
import com.snakelord.pets.kbsustudentassistance.presentation.application.KbsuStudentAssistanceApp
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.navigationCallback
import com.snakelord.pets.kbsustudentassistance.presentation.common.state.UIStates

/**
 * Экран настроек
 *
 * @author Murad Luguev on 12-09-2021
 */
class SettingsFragment : PreferenceFragmentCompat() {

    private val settingsViewModel: SettingsViewModel by navGraphViewModels(R.id.nav_graph) {
        KbsuStudentAssistanceApp.applicationComponent.viewModelFactory()
    }

    private var dialog: AlertDialog? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.fragment_settings, rootKey)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settingsViewModel.uiStates.observe(viewLifecycleOwner, ::updateUIState)
    }

    private fun updateUIState(state: UIStates) {
        if (state == UIStates.Successful) {
            findNavController()
                .navigate(R.id.action_settingsFragment_to_loginFragment)
            navigationCallback.hideBottomNavigationView()
        }
    }

    override fun onPreferenceTreeClick(preference: Preference): Boolean {
        when (preference.key) {
            getString(R.string.logout_key) -> {
                buildLogoutDialog()
            }
        }
        return super.onPreferenceTreeClick(preference)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        dialog?.run {
            outState.putBoolean(IS_SHOWING_KEY, isVisible)
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        savedInstanceState?.run {
            val isShowing = savedInstanceState.getBoolean(IS_SHOWING_KEY)
            if (isShowing) {
                buildLogoutDialog()
                dialog!!.show()
            }
        }
    }

    private fun buildLogoutDialog() {
        dialog = AlertDialog.Builder(requireContext())
            .setTitle(R.string.warning)
            .setMessage(R.string.logout_message)
            .setPositiveButton(R.string.positive_answer) { _, _ ->
                settingsViewModel.logout()
            }
            .setNegativeButton(R.string.negative_answer) { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .create()
        dialog!!.show()
    }

    override fun onStop() {
        super.onStop()

        dialog?.dismiss()
    }

    companion object {
        const val IS_SHOWING_KEY = "is_showing"
    }
}