package com.snakelord.pets.kbsustudentassistance.domain.model.location

import com.yandex.mapkit.geometry.Point

/**
 * Расширение класса [Point] с реализованными методами [equals] и [hashCode]
 *
 * @param latitude широта точки на карте
 * @param longitude долгота точки на карте
 *
 * @author Murad Luguev on 27-08-2021
 */
class LocationPoint(latitude: Double, longitude: Double) : Point(latitude, longitude) {

    override fun equals(other: Any?): Boolean {
        if (other == null)
            return false

        if (other !is LocationPoint)
            return false

        return (latitude == other.latitude && longitude == other.longitude)
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}