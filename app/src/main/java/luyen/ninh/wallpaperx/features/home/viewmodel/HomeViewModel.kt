package luyen.ninh.wallpaperx.features.home.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import luyen.ninh.wallpaperx.domain.data.Result
import luyen.ninh.wallpaperx.domain.data.local.room.entities.LocationEntity
import luyen.ninh.wallpaperx.domain.usecase.UseCase
import luyen.ninh.wallpaperx.base.utils.TimeHelper
import luyen.ninh.wallpaperx.helpers.Event
import timber.log.Timber
import java.text.DecimalFormat

/**
 * Created by luyen_ninh on 2019-10-13.
 */
class HomeViewModel(private val useCase: UseCase) : ViewModel() {
    private val _items = MutableLiveData<List<LocationEntity>>().apply { value = emptyList() }
    val items: LiveData<List<LocationEntity>> = _items

    private val _eventLocation = MutableLiveData<Event<LocationEntity>>()
    val eventLocationView:LiveData<Event<LocationEntity>> = _eventLocation

    private val _step:MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val step:LiveData<String> = _step

    private val _velocity:MediatorLiveData<String> = MediatorLiveData<String>().apply { value = "" }
    val velocity:LiveData<String> = _velocity

    private val _velocityAVG:MediatorLiveData<String> = MediatorLiveData<String>().apply { value = "" }
    val velocityAVG:LiveData<String> = _velocity


    fun registRealTimeList(lifecycleOwner: LifecycleOwner){
        //Location
        useCase.locationUC.getAllLiveLocation().observe(lifecycleOwner, Observer {
            _items.value = it
        })

        //Velocity
        var oldV = 0
        useCase.locationUC.getCurrentVelocityKmPH()
            .observe(lifecycleOwner, Observer {
                it?.let {
                    viewModelScope.launch {
                        with(Dispatchers.Main){
                            if (oldV > it.toInt())
                                for (v in oldV downTo it.toInt()){
                                    _velocity.value = "$v km/h"
                                    delay(500)
                                }
                            else
                                for (v in oldV .. it.toInt()){
                                    _velocity.value = "$v km/h"
                                    delay(500)
                                }

                            oldV = it.toInt()
                        }
                    }
                }
            })

//        useCase.locationUC.getCurrentVelocityKmPH()
//            .observe(lifecycleOwner, Observer {
//                viewModelScope.launch {
//                    with(Dispatchers.Main){
//                        for (v in oldV..it.toInt()){
//                            _velocity.value = "$v km/h"
//                            delay(100)
//                        }
//                        oldV = it.toInt()
//                    }
//                }
//
//            })
        //Step
        useCase.stepUC.getAllStepPerDayLive(TimeHelper.getStartTimeOfToday(),TimeHelper.getEndTimeOfToDay())
            .observe(lifecycleOwner, Observer {
                _step.value = "${it.size} steps"
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

    fun openLocation(location:LocationEntity) {
        _eventLocation.value = Event(location)

    }

    fun refresh() {
        deleteAll()
    }
    private fun deleteAll(){
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                    useCase.locationUC.deleteAllLocation()
                }
            }
    }
}