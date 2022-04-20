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
    @Deprecated("Использовать NavigationCallback#showNavigationBar с параметром видимости")
    fun hideBottomNavigationView()

    /**
     * Функция, которая отображает BottomNavigationView
     */
    @Deprecated("Использовать NavigationCallback#showNavigationBar с параметром видимости")
    fun showNavigationView()

    /**
     * Функция, которая скрывает/отображает нижнюю
     * навигационную панель в зависимости от [show]
     *
     * @param show
     * * [true] - отображает навигационную панель
     * * [false] - скрывает навигационную панель
     */
    fun showNavigationBar(show: Boolean)
}
