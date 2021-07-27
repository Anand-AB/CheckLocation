package com.anand.checklocationapp.presentation.locationList

import androidx.lifecycle.MutableLiveData
import com.anand.checklocationapp.data.contract.CommonApisRepo
import com.anand.checklocationapp.data.models.LocationListData
import com.anand.checklocationapp.presentation.core.BaseViewModel
import kotlinx.coroutines.launch

/**
 * Created by Anand A <anandabktda@gmail.com>
 * The ViewModel used for process location list data
 * */

class LocationListViewModel constructor(private val commonApisRepo: CommonApisRepo) :
    BaseViewModel() {

    val locationListSavedLiveData: MutableLiveData<List<LocationListData>> = MutableLiveData()

    fun getLocationListSaved() {
        launch {
            postValue(commonApisRepo.getLocationListSaved(), locationListSavedLiveData)
        }
    }
}