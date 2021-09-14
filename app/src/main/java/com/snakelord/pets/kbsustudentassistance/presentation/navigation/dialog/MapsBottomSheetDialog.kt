package com.snakelord.pets.kbsustudentassistance.presentation.navigation.dialog

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.snakelord.pets.kbsustudentassistance.databinding.DialogMapsBinding
import com.snakelord.pets.kbsustudentassistance.domain.model.location.LocationPoint
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.expand
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.gone
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.isPackageExist
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.visible

/**
 * Диалог для отображения выбора
 *
 * @author Murad Luguev on 15-09-2021
 */
class MapsBottomSheetDialog : BottomSheetDialogFragment() {

    private var mapsDialogBinding: DialogMapsBinding? = null
    private val binding
        get() = mapsDialogBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mapsDialogBinding = DialogMapsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args = requireArguments()
        val isYandexMapsInstalled = args.getBoolean(IS_YANDEX_MAPS_INSTALLED_KEY)
        val isGoogleMapsInstalled = args.getBoolean(IS_GOOGLE_MAPS_INSTALLED_KEY)

        if (isYandexMapsInstalled) {
            hideErrorMessage()
            val yandexMapsUri = args.getString(YANDEX_MAPS_URI_KEY)
            setButtonAction(binding.yandexMaps, yandexMapsUri)
        }

        if (isGoogleMapsInstalled) {
            hideErrorMessage()
            val googleMapsUri = args.getString(GOOGLE_MAPS_URI_KEY)
            setButtonAction(binding.googleMaps, googleMapsUri)
        }

        if (!isYandexMapsInstalled && !isGoogleMapsInstalled) {
            suggestToInstallYandexMaps()
        }

        expand()
    }

    private fun hideErrorMessage() {
        binding.makeAPathErrorMessage.gone()
        binding.installYandexMaps.gone()
    }

    private fun setButtonAction(button: Button, uri: String?) {
        button.visible()
        button.setOnClickListener {
            openIntent(uri)
        }
    }

    private fun openIntent(uri: String?) {
        if (!uri.isNullOrEmpty()) {
            val pathUri = Uri.parse(uri)
            val openMapIntent = Intent(Intent.ACTION_VIEW, pathUri)
            startActivity(openMapIntent)
        }
    }

    private fun suggestToInstallYandexMaps() {
        val context = requireContext()
        when {
            context.isPackageExist(PLAY_STORE_PACKAGE) -> {
                directToAppStore()
            }
            context.isPackageExist(HUAWEI_APP_GALLERY) -> {
                directToAppStore()
            }
            else -> {
                binding.installYandexMaps.gone()
                binding.dismissDialog.visible()
                binding.dismissDialog.setOnClickListener { dismiss() }
            }
        }
    }

    private fun directToAppStore() {
        binding.installYandexMaps.setOnClickListener {
            openIntent(YANDEX_MAPS_PLAY_STORE_URI)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        mapsDialogBinding = null
    }

    /**
     * Builder для создания диалогового окна с выбором приложения для создания маршрута
     *
     * @author Murad Luguev on 15-09-2021
     */
    class Builder {

        private val mapsBottomSheetDialog = MapsBottomSheetDialog()
        private val argsBundle = Bundle(BUNDLE_CAPACITY)

        /**
         * Функция, которая устанавливает [IS_YANDEX_MAPS_INSTALLED_KEY]
         *
         * @param isInstalled флаг, который определяет установлено приложение или нет
         *
         * @return текущий экземпляр [Builder]
         */
        fun setIsYandexMapsInstalled(isInstalled: Boolean): Builder {
            argsBundle.putBoolean(IS_YANDEX_MAPS_INSTALLED_KEY, isInstalled)
            return this
        }

        /**
         * Функция, которая устанавливает [IS_GOOGLE_MAPS_INSTALLED_KEY]
         *
         * @param isInstalled флаг, который определяет установлено приложение или нет
         *
         * @return текущий экземпляр [Builder]
         */
        fun setIsGoogleMapsInstalled(isInstalled: Boolean): Builder {
            argsBundle.putBoolean(IS_GOOGLE_MAPS_INSTALLED_KEY, isInstalled)
            return this
        }

        /**
         * Функция для установки конечной точки маршрутка
         *
         * @param locationPoint конеченая точка маршрута
         *
         * @return текущий экземпляр [Builder]
         */
        fun setPoint(locationPoint: LocationPoint): Builder {
            val latitude = locationPoint.latitude
            val longitude = locationPoint.longitude
            val yandexMapUri = getYandexMapsUri(latitude, longitude)
            val googleMapsUri = getGoogleMapsUri(latitude, longitude)
            argsBundle.putString(YANDEX_MAPS_URI_KEY, yandexMapUri)
            argsBundle.putString(GOOGLE_MAPS_URI_KEY, googleMapsUri)
            return this
        }

        private fun getYandexMapsUri(latitude: Double, longitude: Double): String {
            return YANDEX_MAPS_URI +
                    "$latitude, $longitude&rtt=pd"
        }

        private fun getGoogleMapsUri(latitude: Double, longitude: Double): String {
            return GOOGLE_MAPS_URI +
                    "$latitude, $longitude&mode=w"
        }

        /**
         * Функция создания диалога с заданными параметрами
         *
         * @return экземпляр диалога [MapsBottomSheetDialog]
         */
        fun create(): MapsBottomSheetDialog {
            mapsBottomSheetDialog.arguments = argsBundle
            return mapsBottomSheetDialog
        }

        companion object {
            private const val BUNDLE_CAPACITY = 4
            private const val YANDEX_MAPS_URI = "yandexmaps://maps.yandex.ru/?rtext=~"
            private const val GOOGLE_MAPS_URI = "google.navigation:q="
        }
    }

    companion object {
        private const val IS_YANDEX_MAPS_INSTALLED_KEY = "is_yandex_maps_installed"
        private const val IS_GOOGLE_MAPS_INSTALLED_KEY = "is_google_maps_installed"
        private const val YANDEX_MAPS_URI_KEY = "yandex_maps_uti"
        private const val GOOGLE_MAPS_URI_KEY = "google_maps_uri"

        private const val PLAY_STORE_PACKAGE = "com.android.vending"
        private const val HUAWEI_APP_GALLERY = "com.huawei.appmarket"
        private const val YANDEX_MAPS_PLAY_STORE_URI = "market://details?id=ru.yandex.yandexmaps"
    }
}