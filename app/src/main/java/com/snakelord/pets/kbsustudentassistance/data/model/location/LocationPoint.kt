package com.snakelord.pets.kbsustudentassistance.data.model.location

import com.yandex.mapkit.geometry.Point

class LocationPoint(latitude: Double, longitude: Double) : Point(latitude, longitude) {

    override fun equals(other: Any?): Boolean {
        if (other == null)
            return false

        if (other !is LocationPoint)
            return false

        return (latitude == other.latitude && longitude == other.longitude)
    }
}