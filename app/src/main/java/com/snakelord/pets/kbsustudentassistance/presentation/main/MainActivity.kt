package com.snakelord.pets.kbsustudentassistance.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.snakelord.pets.kbsustudentassistance.R
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.gone
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.visible
import kotlin.properties.Delegates.notNull

/**
 * Главное и единственное Activity в приложении. Выступает хостом для всех фрагментов
 *
 * @author Murad Luguev on 27-08-2021
 */
class MainActivity : AppCompatActivity(), NavigationCallback {

    private var bottomNavigationView: BottomNavigationView by notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_KBSUStudentAssistance)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNavigation)

        NavigationUI.setupWithNavController(
            bottomNavigationView,
            findNavController(R.id.container)
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putBoolean(NAVIGATION_VISIBILITY_KEY, bottomNavigationView.isVisible)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        showNavigationBar(savedInstanceState.getBoolean(NAVIGATION_VISIBILITY_KEY))
    }

    override fun hideBottomNavigationView() {
        bottomNavigationView.gone()
    }

    override fun showNavigationView() {
        bottomNavigationView.visible()
    }

    override fun showNavigationBar(show: Boolean) {
        bottomNavigationView.isVisible = show
    }

    companion object {
        private const val NAVIGATION_VISIBILITY_KEY = "is_navigation_visible"
    }
}
