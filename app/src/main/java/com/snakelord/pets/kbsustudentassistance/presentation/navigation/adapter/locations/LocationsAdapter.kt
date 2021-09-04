package com.snakelord.pets.kbsustudentassistance.presentation.navigation.adapter.locations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import com.snakelord.pets.kbsustudentassistance.R
import com.snakelord.pets.kbsustudentassistance.data.model.location.LocationModel
import com.snakelord.pets.kbsustudentassistance.databinding.ItemInstituteBinding
import com.snakelord.pets.kbsustudentassistance.presentation.navigation.adapter.locations.viewholder.LocationViewHolder

/**
 * Адаптер для отображения локаций в RecyclerView
 *
 * @property locations список локаций
 * @property showLocation функция для отображения локации на карте
 * @property makeAPath функция для прокладывания маршрута в приложении карты
 *
 * @author Murad Luguev on 26-08-2021
 */
class LocationsAdapter(
    private val locations: List<LocationModel>,
    private val showLocation: (LocationModel) -> Unit,
    private val makeAPath: (LocationModel) -> Unit
) : RecyclerView.Adapter<LocationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val itemBinding = ItemInstituteBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return LocationViewHolder(itemBinding, ::performButtonClick)
    }

    private fun performButtonClick(@IdRes buttonId: Int, position: Int) {
        if (buttonId == R.id.openOnMap) {
            showLocation.invoke(locations[position])
        } else {
            makeAPath.invoke(locations[position])
        }
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(locations[position])
    }

    override fun getItemCount(): Int {
        return locations.size
    }
}