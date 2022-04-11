package com.snakelord.pets.kbsustudentassistance.presentation.navigation

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.snakelord.pets.kbsustudentassistance.R
import com.snakelord.pets.kbsustudentassistance.databinding.FragmentNavigationBinding
import com.snakelord.pets.kbsustudentassistance.domain.model.location.LocationModel
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.collapse
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.expand
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.isExpanded
import com.snakelord.pets.kbsustudentassistance.presentation.common.fragment.BaseFragment
import com.snakelord.pets.kbsustudentassistance.presentation.common.simple_interfaces.SimpleBottomSheetCallback
import com.snakelord.pets.kbsustudentassistance.presentation.navigation.adapter.locations.LocationsAdapter
import com.snakelord.pets.kbsustudentassistance.presentation.navigation.dialog.MapsBottomSheetDialog
import com.snakelord.pets.kbsustudentassistance.presentation.navigation.extensions.setNightTheme
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.map.CameraPosition
import com.yandex.runtime.image.ImageProvider

/**
 * Фрагмент навигации по университету
 *
 * @author Murad Luguev on 26-08-2021
 */
class NavigationFragment : BaseFragment(R.layout.fragment_navigation) {

    private var fragmentNavigationBinding: FragmentNavigationBinding? = null
    private val binding
        get() = requireBinding(fragmentNavigationBinding)

    private val navigationViewModel: NavigationViewModel
            by navGraphViewModels(navGraphId) { factory }

    private lateinit var bottomSheetCallback: BottomSheetBehavior.BottomSheetCallback

    private var institutesBottomSheet: BottomSheetBehavior<LinearLayout>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentNavigationBinding = FragmentNavigationBinding.bind(view)

        initInstituteBottomSheet()

        navigationViewModel.currentLocation.observe(viewLifecycleOwner, ::showSelectedPoint)
        navigationViewModel.locations.observe(viewLifecycleOwner, ::showLocationList)

        binding.institutesBottomSheet.institutesRecyclerView.layoutManager =
            LinearLayoutManager(requireContext())

        binding.institutesBottomSheet.dragView.setOnClickListener { updateBottomSheetState() }

        checkArguments()
    }

    private fun initInstituteBottomSheet() {
        institutesBottomSheet = BottomSheetBehavior.from(binding.institutesBottomSheet.root)
        bottomSheetCallback = getBottomSheetCallback()
        institutesBottomSheet?.addBottomSheetCallback(bottomSheetCallback)
    }

    private fun getBottomSheetCallback(): BottomSheetBehavior.BottomSheetCallback {
        return object : SimpleBottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    navigationViewModel.setBottomSheetExpandedState(true)
                } else {
                    navigationViewModel.setBottomSheetExpandedState(false)
                }
            }
        }
    }

    private fun restoreBottomSheetState() {
        if (navigationViewModel.isBottomSheetExpanded)
            institutesBottomSheet?.expand()
    }

    private fun checkArguments() {
        val args = requireArguments()
        val instituteId = args.getInt(ARGUMENT_INSTITUTE_ID)
        navigationViewModel.showLocationById(instituteId)
    }

    private fun setMapTheme() {
        binding.entranceMapView.setNightTheme(
            navigationViewModel.isAppInDarkTheme()
        )
    }

    override fun onStart() {
        super.onStart()

        MapKitFactory.getInstance()
            .onStart()
        binding.entranceMapView.onStart()

        setMapTheme()
    }

    override fun onResume() {
        super.onResume()

        restoreBottomSheetState()
    }

    private fun updateBottomSheetState() {
        if (!institutesBottomSheet?.isExpanded!!) {
            institutesBottomSheet?.expand()
        } else {
            institutesBottomSheet?.collapse()
        }
    }

    private fun showSelectedPoint(locationModel: LocationModel) {
        institutesBottomSheet?.collapse()
        binding.entranceMapView.map.move(
            CameraPosition(locationModel.locationPoint, MAP_ZOOM, MAP_AZIMUTH, MAP_TILT),
            Animation(Animation.Type.SMOOTH, ZOOM_ANIMATION_DURATION),
            null
        )
    }

    private fun showLocationList(locations: List<LocationModel>) {
        val locationsAdapter = LocationsAdapter(
            locations,
            navigationViewModel::showSelectedEntrance,
            ::makeAPath
        )
        binding.institutesBottomSheet.institutesRecyclerView.adapter = locationsAdapter

        addPlaceMarks(locations)
    }

    private fun makeAPath(locationModel: LocationModel) {
        val mapsBottomSheetDialog = MapsBottomSheetDialog.Builder()
            .setIsYandexMapsInstalled(navigationViewModel.isYandexMapsInstalled())
            .setIsGoogleMapsInstalled(navigationViewModel.isGoogleMapsInstalled())
            .setPoint(locationModel.locationPoint)
            .create()
        mapsBottomSheetDialog.show(parentFragmentManager, null)
    }

    private fun addPlaceMarks(locations: List<LocationModel>) {
        for (location in locations) {
            binding.entranceMapView.map.mapObjects
                .addPlacemark(location.locationPoint,
                    ImageProvider.fromResource(
                        requireContext(),
                        R.drawable.ic_pin
                    )
                )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        navigationViewModel.setBottomSheetExpandedState(institutesBottomSheet?.isExpanded == true)
    }

    override fun onStop() {
        super.onStop()

        binding.entranceMapView.onStop()
        MapKitFactory.getInstance()
            .onStop()

        institutesBottomSheet?.removeBottomSheetCallback(bottomSheetCallback)
    }

    companion object {
        private const val ARGUMENT_INSTITUTE_ID = "instituteId"
        private const val MAP_ZOOM = 17.5f
        private const val MAP_AZIMUTH = 319.0f
        private const val MAP_TILT = 0.0f
        private const val ZOOM_ANIMATION_DURATION = 0.2f
    }
}
