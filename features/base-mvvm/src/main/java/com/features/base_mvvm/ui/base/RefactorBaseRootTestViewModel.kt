package com.features.base_mvvm.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hmju.core.ui.base.FragmentViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.kotlin.addTo
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2022/04/15
 */
@HiltViewModel
class RefactorBaseRootTestViewModel @Inject constructor(
) : FragmentViewModel() {

    private val _intervalText: MutableLiveData<String> by lazy { MutableLiveData() }
    val intervalText: LiveData<String> get() = _intervalText

    override fun onDirectCreate() {
        super.onDirectCreate()
        startInterval()
    }

    private fun startInterval() {
        Flowable.interval(1000, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _intervalText.value = "IntervalText $it"
            }, {

            }).addTo(compositeDisposable)
    }
}
