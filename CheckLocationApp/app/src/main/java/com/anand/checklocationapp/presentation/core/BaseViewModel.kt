package com.anand.checklocationapp.presentation.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anand.checklocationapp.data.Either
import com.anand.checklocationapp.domain.exceptions.MyException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * Created by Anand A <anandabktda@gmail.com>
 * */

open class BaseViewModel : ViewModel(), CoroutineScope {

    val exceptionLiveData: MutableLiveData<Exception> = MutableLiveData()

    var job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun <R> postValue(either: Either<MyException, R>, successLiveData: MutableLiveData<R>) {

        either.either({
            exceptionLiveData.postValue(it)
        }, {
            successLiveData.postValue(it)
        })

    }
}