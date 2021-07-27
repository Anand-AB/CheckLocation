package com.anand.checklocationapp.data.contract

import com.anand.checklocationapp.data.Either
import com.anand.checklocationapp.data.models.LocationListData
import com.anand.checklocationapp.domain.exceptions.MyException

interface CommonApisRepo {

    suspend fun getLocationListSaved(): Either<MyException, List<LocationListData>>

}