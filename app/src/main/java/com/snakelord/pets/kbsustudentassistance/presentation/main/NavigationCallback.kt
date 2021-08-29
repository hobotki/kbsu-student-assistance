package com.snakelord.pets.kbsustudentassistance.presentation.main

/**
 * Коллбек для взаимодействия с BottomNavigationView у Activity-хоста
 *
 * @author Murad Luguev on 27-08-2021
 */
interface NavigationCallback {
    /**
     * Функция, которая скрывает BottomNavigationView
     */
    fun hideBottomNavigationView()

    /**
     * Функция, которая отображает BottomNavigationView
     */
    fun showNavigationView()
}