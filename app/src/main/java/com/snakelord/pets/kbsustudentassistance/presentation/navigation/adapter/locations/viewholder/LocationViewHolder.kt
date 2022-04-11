package com.snakelord.pets.kbsustudentassistance.presentation.navigation.adapter.locations.viewholder

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.viewContext
import com.snakelord.pets.kbsustudentassistance.domain.model.location.LocationModel
import com.snakelord.pets.kbsustudentassistance.databinding.ItemInstituteBinding
import com.snakelord.pets.kbsustudentassistance.presentation.navigation.adapter.lectures_minimal.LecturesMinimalAdapter
import com.snakelord.pets.kbsustudentassistance.presentation.navigation.adapter.locations.LocationsAdapter

/**
 * ViewHolder для отображения информации о локации
 *
 * @property locationBinding экземпляр [ItemInstituteBinding] для работы с View
 * @property clickListener слушатель нажатий, принимающий в качестве параметров id кнопки и
 *           позицию элемента
 *
 * @author Murad Luguev on 26-08-2021
 */
class LocationViewHolder(
    private val locationBinding: ItemInstituteBinding,
    private val clickListener: (LocationsAdapter.Action, Int) -> Unit
) : RecyclerView.ViewHolder(locationBinding.root) {

    /**
     * Метод для связывания данных из [LocationModel] с необходимыми View
     *
     * @param locationModel экземпляр [LocationModel]
     */
    fun bind(locationModel: LocationModel) {
        locationBinding.instituteTextView.text = viewContext.getString(locationModel.enterRes)

        val linearLayoutManager = LinearLayoutManager(viewContext)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        val lecturesMinimalAdapter = LecturesMinimalAdapter(locationModel.lectures)

        locationBinding.lecturesRecyclerView.layoutManager = linearLayoutManager
        locationBinding.lecturesRecyclerView.adapter = lecturesMinimalAdapter

        locationBinding.openOnMapButton.setOnClickListener {
            clickListener.invoke(LocationsAdapter.Action.OPEN_ON_MAP, adapterPosition)
        }

        locationBinding.makeAPathButton.setOnClickListener {
            clickListener.invoke(LocationsAdapter.Action.MAKE_A_PATH, adapterPosition)
        }
    }
}
