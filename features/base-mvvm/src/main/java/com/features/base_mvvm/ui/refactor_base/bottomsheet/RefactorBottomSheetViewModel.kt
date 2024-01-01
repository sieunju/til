package com.features.base_mvvm.ui.refactor_base.bottomsheet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.features.base_mvvm.usecase.GetGoodsUseCase
import com.hmju.core.ui.base.BottomSheetViewModel
import com.hmju.core.model.params.GoodsParameter
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.kotlin.addTo
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.random.Random

/**
 * Description :
 *
 * Created by juhongmin on 2022/04/18
 */
@HiltViewModel
class RefactorBottomSheetViewModel @Inject constructor(
    private val getGoodsUseCase: GetGoodsUseCase
) : BottomSheetViewModel() {

    private val _blueTitle: MutableLiveData<String> by lazy { MutableLiveData() }
    val blueTitle: LiveData<String> get() = _blueTitle

    private val _redTitle: MutableLiveData<String> by lazy { MutableLiveData() }
    val redTitle: LiveData<String> get() = _redTitle

    val startDismiss: MutableLiveData<Unit> by lazy { MutableLiveData() }

    override fun onDirectCreate() {
        super.onDirectCreate()
        val queryMap = GoodsParameter()
        queryMap.pageNo = 2
        getGoodsUseCase(queryMap)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Timber.d("SUCC $it")
            }, {

            }).addTo(compositeDisposable)
    }

    override fun onDirectViewCreated() {
        super.onDirectViewCreated()
        startBlueTitle()
        startRedTitle()
    }

    private fun startBlueTitle() {
        Flowable.interval(0, 1000, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _blueTitle.value = "${Random.nextInt(1000)}_$it"
            }, {

            }).addTo(compositeDisposable)
    }

    private fun startRedTitle() {
        Flowable.interval(0, 1000, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _redTitle.value = "${Random.nextInt(1000)}_$it"
            }, {

            }).addTo(compositeDisposable)
    }

    override fun onDirectStop() {
        super.onDirectStop()
        Timber.d("onStop")
    }

    fun onDismiss() {
        startDismiss.value = null
    }
}
