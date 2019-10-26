package luyen.ninh.wallpaperx.presentations.features.map

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import luyen.ninh.wallpaperx.domain.data.Result
import luyen.ninh.wallpaperx.domain.data.local.room.entities.LocationEntity
import luyen.ninh.wallpaperx.domain.usecase.UseCase
import luyen.ninh.wallpaperx.presentations.helpers.Event
import timber.log.Timber

/**
 * Created by luyen_ninh on 2019-10-25.
 */
class MapViewModel(private val useCase: UseCase) : ViewModel() {
    private val _items = MutableLiveData<List<LocationEntity>>().apply { value = emptyList() }
    val items: LiveData<List<LocationEntity>> = _items

    private val _eventLocation = MutableLiveData<Event<LocationEntity>>()
    val eventLocationView: LiveData<Event<LocationEntity>> = _eventLocation

    fun registRealTimeList(lifecycleOwner: LifecycleOwner){
        useCase.locationUC.getAllLiveLocation().observe(lifecycleOwner, Observer {
            _items.value = it
        })

    }
    fun getAllLocation() {
        viewModelScope.launch {

            withContext(Dispatchers.Main) {
                useCase.locationUC.getAllLocation()
                    .let {
                        when(it){
                            is Result.Success ->
                                _items.value = it.data
                            is Result.Error ->
                                Timber.e("get all fail"+it.exception.message)

                        }
                    }
            }
        }
    }

    fun openLocation(location: LocationEntity) {


    }

    fun refresh() {
        getAllLocation()
    }
}