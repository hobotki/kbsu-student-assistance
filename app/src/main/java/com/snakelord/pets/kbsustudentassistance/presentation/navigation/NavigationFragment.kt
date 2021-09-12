package com.snakelord.pets.kbsustudentassistance.presentation.navigation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.snakelord.pets.kbsustudentassistance.domain.model.location.LocationModel
import com.snakelord.pets.kbsustudentassistance.databinding.FragmentNavigationBinding
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.collapse
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.expand
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.isExpanded
import com.snakelord.pets.kbsustudentassistance.presentation.common.fragment.BaseFragment
import com.snakelord.pets.kbsustudentassistance.presentation.navigation.adapter.locations.LocationsAdapter
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.map.CameraPosition

/**
 * Фрагмент навигации по университету
 *
 * @author Murad Luguev on 26-08-2021
 */
class NavigationFragment : BaseFragment() {

    private lateinit var binding: FragmentNavigationBinding

    private val navigationViewModel: NavigationViewModel
            by navGraphViewModels(navGraphId) { factory }

    private lateinit var institutesBottomSheet: BottomSheetBehavior<LinearLayout>
    private var bottomSheetExpanded = false

    override fun onStart() {
        super.onStart()

        MapKitFactory.getInstance()
            .onStart()
        binding.mapView.onStart()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNavigationBinding.inflate(inflater, container, false)

        institutesBottomSheet = BottomSheetBehavior.from(binding.bottomSheetContent.root)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigationViewModel.currentLocation.observe(viewLifecycleOwner, ::showSelectedPoint)
        navigationViewModel.locations.observe(viewLifecycleOwner, ::showLocationList)

        restoreBottomSheetState(savedInstanceState)

        binding.bottomSheetContent.institutes.layoutManager = LinearLayoutManager(requireContext())

        binding.bottomSheetContent.dragView.setOnClickListener {
            updateBottomSheetState()
        }

        checkArguments()
    }

    private fun checkArguments() {
        val args = requireArguments()
        val instituteId = args.getInt(ARGUMENT_INSTITUTE_ID)
        navigationViewModel.showLocationById(instituteId)
    }

    private fun updateBottomSheetState() {
        if (!bottomSheetExpanded) {
            institutesBottomSheet.expand()
        } else {
            institutesBottomSheet.collapse()
        }
        bottomSheetExpanded = !bottomSheetExpanded
    }

    override fun onResume() {
        super.onResume()
        if (bottomSheetExpanded)
            institutesBottomSheet.expand()
    }

    private fun restoreBottomSheetState(savedInstanceState: Bundle?) {
        savedInstanceState?.run {
            val isBottomSheetExpanded = getBoolean(BOTTOM_SHEET_STATE_KEY)
            if (isBottomSheetExpanded) {
                bottomSheetExpanded = isBottomSheetExpanded
            }
        }
    }

    private fun showSelectedPoint(locationModel: LocationModel) {
        institutesBottomSheet.collapse()
        binding.mapView.map.move(
            CameraPosition(locationModel.locationPoint, 17.5f, 319.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 0.5f),
            null
        )
    }

    private fun showLocationList(locations: List<LocationModel>) {

        val locationsAdapter = LocationsAdapter(
            locations,
            navigationViewModel::showSelectedEntrance,
            ::makeAPath
        )
        binding.bottomSheetContent.institutes.adapter = locationsAdapter

        addPlaceMarks(locations)
    }

    private fun makeAPath(locationModel: LocationModel) {
        institutesBottomSheet.collapse()
        val point = locationModel.locationPoint
        val pathUri = Uri.parse(
            YANDEX_MAP_URI_PATH +
                    "${point.latitude}," +
                    "${point.longitude}"
        )
        startActivity(Intent(Intent.ACTION_VIEW, pathUri))
    }

    private fun addPlaceMarks(locations: List<LocationModel>) {
        for (location in locations) {
            binding.mapView
                .map
                .mapObjects
                .addPlacemark(location.locationPoint)
        }
    }

    override fun onStop() {
        super.onStop()

        binding.mapView.onStop()
        MapKitFactory.getInstance()
            .onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putBoolean(BOTTOM_SHEET_STATE_KEY, institutesBottomSheet.isExpanded)
    }

    companion object {
        private const val BOTTOM_SHEET_STATE_KEY = "bottom_sheet_state_key"
        private const val YANDEX_MAP_URI_PATH = "yandexmaps://maps.yandex.ru/?rtext=~"
        private const val ARGUMENT_INSTITUTE_ID = "instituteId"
    }
}