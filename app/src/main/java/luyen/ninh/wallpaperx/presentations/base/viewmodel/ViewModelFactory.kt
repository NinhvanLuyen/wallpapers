package luyen.ninh.wallpaperx.presentations.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import luyen.ninh.wallpaperx.domain.usecase.UseCase
import luyen.ninh.wallpaperx.presentations.features.detail.DetailViewModel
import luyen.ninh.wallpaperx.presentations.features.home.viewmodel.HomeViewModel
import luyen.ninh.wallpaperx.presentations.features.map.MapViewModel

/**
 * Created by luyen_ninh on 2019-10-13.
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    private val useCase: UseCase
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(DetailViewModel::class.java) ->
                    DetailViewModel(useCase)

                isAssignableFrom(HomeViewModel::class.java) ->
                    HomeViewModel(useCase)

                isAssignableFrom(MapViewModel::class.java) ->
                    MapViewModel(useCase)

                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}