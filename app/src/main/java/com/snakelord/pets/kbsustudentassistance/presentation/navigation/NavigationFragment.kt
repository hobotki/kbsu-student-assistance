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
import com.snakelord.pets.kbsustudentassistance.databinding.FragmentNavigationBinding
import com.snakelord.pets.kbsustudentassistance.domain.model.location.LocationModel
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.collapse
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.expand
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.isExpanded
import com.snakelord.pets.kbsustudentassistance.presentation.common.fragment.BaseFragment
import com.snakelord.pets.kbsustudentassistance.presentation.common.simple_interfaces.SimpleBottomSheetCallback
import com.snakelord.pets.kbsustudentassistance.presentation.navigation.adapter.locations.LocationsAdapter
import com.snakelord.pets.kbsustudentassistance.presentation.navigation.extensions.setNightTheme
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.map.CameraPosition

/**
 * Фрагмент навигации по университету
 *
 * @author Murad Luguev on 26-08-2021
 */
class NavigationFragment : BaseFragment() {

    private var fragmentNavigationBinding: FragmentNavigationBinding? = null
    private val binding
        get() = fragmentNavigationBinding!!

    private val navigationViewModel: NavigationViewModel
            by navGraphViewModels(navGraphId) { factory }

    private lateinit var bottomSheetCallback: BottomSheetBehavior.BottomSheetCallback

    private lateinit var institutesBottomSheet: BottomSheetBehavior<LinearLayout>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        fragmentNavigationBinding = FragmentNavigationBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigationViewModel.currentLocation.observe(viewLifecycleOwner, ::showSelectedPoint)
        navigationViewModel.locations.observe(viewLifecycleOwner, ::showLocationList)

        initInstituteBottomSheet()

        binding.bottomSheetContent.institutes.layoutManager = LinearLayoutManager(requireContext())

        binding.bottomSheetContent.dragView.setOnClickListener {
            updateBottomSheetState()
        }

        checkArguments()

        setMapTheme()
    }

    private fun initInstituteBottomSheet() {
        institutesBottomSheet = BottomSheetBehavior.from(binding.bottomSheetContent.root)
        bottomSheetCallback = getBottomSheetCallback()
        institutesBottomSheet.addBottomSheetCallback(bottomSheetCallback)
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
            institutesBottomSheet.expand()
    }

    private fun checkArguments() {
        val args = requireArguments()
        val instituteId = args.getInt(ARGUMENT_INSTITUTE_ID)
        navigationViewModel.showLocationById(instituteId)
    }

    private fun setMapTheme() {
        binding.mapView.setNightTheme(
            navigationViewModel.isAppInDarkTheme()
        )
    }

    override fun onStart() {
        super.onStart()

        MapKitFactory.getInstance()
            .onStart()
        binding.mapView.onStart()
    }

    override fun onResume() {
        super.onResume()

        restoreBottomSheetState()
    }

    private fun updateBottomSheetState() {
        if (!institutesBottomSheet.isExpanded) {
            institutesBottomSheet.expand()
        } else {
            institutesBottomSheet.collapse()
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        navigationViewModel.setBottomSheetExpandedState(institutesBottomSheet.isExpanded)
    }

    override fun onStop() {
        super.onStop()

        binding.mapView.onStop()
        MapKitFactory.getInstance()
            .onStop()

        institutesBottomSheet.removeBottomSheetCallback(bottomSheetCallback)
    }

    companion object {
        private const val YANDEX_MAP_URI_PATH = "yandexmaps://maps.yandex.ru/?rtext=~"
        private const val ARGUMENT_INSTITUTE_ID = "instituteId"
    }
}