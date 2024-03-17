package com.features.rv_diff_util_performance

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.NumberPicker
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import com.features.rv_diff_util_performance.databinding.FPerformanceDiffUtilBinding
import com.hmju.core.ui.base.BaseFragment
import com.hmju.core.ui.base.BaseUiModel
import com.hmju.core.ui.base.FragmentViewModel
import com.hmju.core.ui.diffutil.BaseDiffUtil
import com.hmju.legacy.recyclerview.diffutil.BetterDiffUtil
import com.hmju.legacy.recyclerview.diffutil.IsDiffUtil
import com.hmju.legacy.recyclerview.diffutil.IsLegacyDiffUtil
import com.hmju.legacy.recyclerview.model.Model1
import com.hmju.legacy.recyclerview.model.Model10
import com.hmju.legacy.recyclerview.model.Model100
import com.hmju.legacy.recyclerview.model.Model101
import com.hmju.legacy.recyclerview.model.Model102
import com.hmju.legacy.recyclerview.model.Model103
import com.hmju.legacy.recyclerview.model.Model104
import com.hmju.legacy.recyclerview.model.Model105
import com.hmju.legacy.recyclerview.model.Model106
import com.hmju.legacy.recyclerview.model.Model107
import com.hmju.legacy.recyclerview.model.Model108
import com.hmju.legacy.recyclerview.model.Model109
import com.hmju.legacy.recyclerview.model.Model11
import com.hmju.legacy.recyclerview.model.Model110
import com.hmju.legacy.recyclerview.model.Model12
import com.hmju.legacy.recyclerview.model.Model13
import com.hmju.legacy.recyclerview.model.Model14
import com.hmju.legacy.recyclerview.model.Model15
import com.hmju.legacy.recyclerview.model.Model16
import com.hmju.legacy.recyclerview.model.Model17
import com.hmju.legacy.recyclerview.model.Model18
import com.hmju.legacy.recyclerview.model.Model19
import com.hmju.legacy.recyclerview.model.Model2
import com.hmju.legacy.recyclerview.model.Model20
import com.hmju.legacy.recyclerview.model.Model21
import com.hmju.legacy.recyclerview.model.Model22
import com.hmju.legacy.recyclerview.model.Model23
import com.hmju.legacy.recyclerview.model.Model24
import com.hmju.legacy.recyclerview.model.Model25
import com.hmju.legacy.recyclerview.model.Model26
import com.hmju.legacy.recyclerview.model.Model27
import com.hmju.legacy.recyclerview.model.Model28
import com.hmju.legacy.recyclerview.model.Model29
import com.hmju.legacy.recyclerview.model.Model3
import com.hmju.legacy.recyclerview.model.Model30
import com.hmju.legacy.recyclerview.model.Model31
import com.hmju.legacy.recyclerview.model.Model32
import com.hmju.legacy.recyclerview.model.Model33
import com.hmju.legacy.recyclerview.model.Model34
import com.hmju.legacy.recyclerview.model.Model35
import com.hmju.legacy.recyclerview.model.Model36
import com.hmju.legacy.recyclerview.model.Model37
import com.hmju.legacy.recyclerview.model.Model38
import com.hmju.legacy.recyclerview.model.Model39
import com.hmju.legacy.recyclerview.model.Model4
import com.hmju.legacy.recyclerview.model.Model40
import com.hmju.legacy.recyclerview.model.Model41
import com.hmju.legacy.recyclerview.model.Model42
import com.hmju.legacy.recyclerview.model.Model43
import com.hmju.legacy.recyclerview.model.Model44
import com.hmju.legacy.recyclerview.model.Model45
import com.hmju.legacy.recyclerview.model.Model46
import com.hmju.legacy.recyclerview.model.Model47
import com.hmju.legacy.recyclerview.model.Model48
import com.hmju.legacy.recyclerview.model.Model49
import com.hmju.legacy.recyclerview.model.Model5
import com.hmju.legacy.recyclerview.model.Model50
import com.hmju.legacy.recyclerview.model.Model51
import com.hmju.legacy.recyclerview.model.Model52
import com.hmju.legacy.recyclerview.model.Model53
import com.hmju.legacy.recyclerview.model.Model54
import com.hmju.legacy.recyclerview.model.Model55
import com.hmju.legacy.recyclerview.model.Model56
import com.hmju.legacy.recyclerview.model.Model57
import com.hmju.legacy.recyclerview.model.Model58
import com.hmju.legacy.recyclerview.model.Model59
import com.hmju.legacy.recyclerview.model.Model6
import com.hmju.legacy.recyclerview.model.Model60
import com.hmju.legacy.recyclerview.model.Model61
import com.hmju.legacy.recyclerview.model.Model62
import com.hmju.legacy.recyclerview.model.Model63
import com.hmju.legacy.recyclerview.model.Model64
import com.hmju.legacy.recyclerview.model.Model65
import com.hmju.legacy.recyclerview.model.Model66
import com.hmju.legacy.recyclerview.model.Model67
import com.hmju.legacy.recyclerview.model.Model68
import com.hmju.legacy.recyclerview.model.Model69
import com.hmju.legacy.recyclerview.model.Model7
import com.hmju.legacy.recyclerview.model.Model70
import com.hmju.legacy.recyclerview.model.Model71
import com.hmju.legacy.recyclerview.model.Model72
import com.hmju.legacy.recyclerview.model.Model73
import com.hmju.legacy.recyclerview.model.Model74
import com.hmju.legacy.recyclerview.model.Model75
import com.hmju.legacy.recyclerview.model.Model76
import com.hmju.legacy.recyclerview.model.Model77
import com.hmju.legacy.recyclerview.model.Model78
import com.hmju.legacy.recyclerview.model.Model79
import com.hmju.legacy.recyclerview.model.Model8
import com.hmju.legacy.recyclerview.model.Model80
import com.hmju.legacy.recyclerview.model.Model81
import com.hmju.legacy.recyclerview.model.Model82
import com.hmju.legacy.recyclerview.model.Model83
import com.hmju.legacy.recyclerview.model.Model84
import com.hmju.legacy.recyclerview.model.Model85
import com.hmju.legacy.recyclerview.model.Model86
import com.hmju.legacy.recyclerview.model.Model87
import com.hmju.legacy.recyclerview.model.Model88
import com.hmju.legacy.recyclerview.model.Model89
import com.hmju.legacy.recyclerview.model.Model9
import com.hmju.legacy.recyclerview.model.Model90
import com.hmju.legacy.recyclerview.model.Model91
import com.hmju.legacy.recyclerview.model.Model92
import com.hmju.legacy.recyclerview.model.Model93
import com.hmju.legacy.recyclerview.model.Model94
import com.hmju.legacy.recyclerview.model.Model95
import com.hmju.legacy.recyclerview.model.Model96
import com.hmju.legacy.recyclerview.model.Model97
import com.hmju.legacy.recyclerview.model.Model98
import com.hmju.legacy.recyclerview.model.Model99
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.Executors
import kotlin.random.Random
import kotlin.random.nextInt

/**
 * Description :
 *
 * Created by juhongmin on 3/8/24
 */
@AndroidEntryPoint
class DiffUtilPerformanceFragment : BaseFragment<FPerformanceDiffUtilBinding, FragmentViewModel>(
    R.layout.f_performance_diff_util
) {

    override val viewModel: FragmentViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm

    private val legacyTimeList = mutableListOf<Long>()
    private val betterLegacyTimeList = mutableListOf<Long>()
    private val betterTimeList = mutableListOf<Long>()
    private val v2TimeList = mutableListOf<Long>()

    private var countNumber = 100
    private var listCount = 1000

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            npCount.wrapSelectorWheel = false
            npCount.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
            npCount.minValue = 1
            npCount.maxValue = 50

            npCount.setOnValueChangedListener { picker, oldVal, newVal ->
                countNumber = newVal * 50
                tvCurrCount.text = "$countNumber 번 반복"
            }

            npListCount.wrapSelectorWheel = false
            npListCount.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
            npListCount.minValue = 1
            npListCount.maxValue = 50

            npListCount.setOnValueChangedListener { picker, oldVal, newVal ->
                listCount = newVal * 500
                tvListCount.text = "리스트 $listCount 개"
            }

            legacy.setOnClickListener {
                performLegacy(countNumber)
            }

            betterLegacy.setOnClickListener {
                performBetterLegacy(countNumber)
            }

            better.setOnClickListener {
                performBetter(countNumber)
            }

            diffUtilV2.setOnClickListener {
                performV2DiffUtil(countNumber)
            }
        }
    }

    private fun flowableList(count: Int): Flowable<List<BaseUiModel>> {
        return Flowable.just(count)
            .map { ranList(it) }
            .subscribeOn(Schedulers.io())
    }

    @SuppressLint("SetTextI18n")
    private fun performLegacy(count: Int) {
        legacyTimeList.clear()
        val dumpList = mutableListOf<BaseUiModel>()
        val workList = mutableListOf<Flowable<List<BaseUiModel>>>()
        for (idx in 0 until count) {
            workList.add(flowableList(listCount))
        }
        val disposable = Flowable.fromIterable(workList)
            .map { it.blockingFirst() }
            .map { newList ->
                val time = System.currentTimeMillis()
                DiffUtil.calculateDiff(IsLegacyDiffUtil(dumpList, newList))
                dumpList.clear()
                dumpList.addAll(newList)
                legacyTimeList.add(System.currentTimeMillis() - time)
                Timber.d("Legacy Count ${legacyTimeList.size}")
                return@map newList
            }
            .onBackpressureBuffer()
            .subscribeOn(Schedulers.from(Executors.newFixedThreadPool(2)))
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                var sumTime = 0L
                legacyTimeList.forEach {
                    sumTime += it
                }
                binding.tvLegacy.text = "Legacy ${sumTime / legacyTimeList.size}MS"
                Toast.makeText(requireContext(), "Legacy The End", Toast.LENGTH_SHORT).show()
            }.subscribe()
        viewModel.addDisposable(disposable)
    }

    @SuppressLint("SetTextI18n")
    private fun performBetterLegacy(count: Int) {
        betterLegacyTimeList.clear()
        val dumpList = mutableListOf<BaseUiModel>()
        val workList = mutableListOf<Flowable<List<BaseUiModel>>>()
        for (idx in 0 until count) {
            workList.add(flowableList(listCount))
        }
        val disposable = Flowable.fromIterable(workList)
            .map { it.blockingFirst() }
            .map { newList ->
                val time = System.currentTimeMillis()
                DiffUtil.calculateDiff(IsDiffUtil(dumpList, newList))
                dumpList.clear()
                dumpList.addAll(newList)
                betterLegacyTimeList.add(System.currentTimeMillis() - time)
                Timber.d("Better Legacy Count ${betterLegacyTimeList.size}")
                return@map newList
            }
            .onBackpressureBuffer()
            .subscribeOn(Schedulers.from(Executors.newFixedThreadPool(2)))
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                var sumTime = 0L
                betterLegacyTimeList.forEach {
                    sumTime += it
                }
                binding.tvBetterLegacy.text =
                    "Better Legacy ${sumTime / betterLegacyTimeList.size}MS"
                Toast.makeText(requireContext(), "Better Legacy The End", Toast.LENGTH_SHORT).show()
            }
            .subscribe()
        viewModel.addDisposable(disposable)
    }

    @SuppressLint("SetTextI18n")
    private fun performBetter(count: Int) {
        betterTimeList.clear()
        val dumpList = mutableListOf<BaseUiModel>()
        val workList = mutableListOf<Flowable<List<BaseUiModel>>>()
        for (idx in 0 until count) {
            workList.add(flowableList(listCount))
        }
        val disposable = Flowable.fromIterable(workList)
            .map { it.blockingFirst() }
            .map { newList ->
                val time = System.currentTimeMillis()
                DiffUtil.calculateDiff(BetterDiffUtil(dumpList, newList))
                dumpList.clear()
                dumpList.addAll(newList)
                betterTimeList.add(System.currentTimeMillis() - time)
                Timber.d("Better Count ${betterTimeList.size}")
                return@map newList
            }
            .onBackpressureBuffer()
            .subscribeOn(Schedulers.from(Executors.newFixedThreadPool(2)))
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                var sumTime = 0L
                betterTimeList.forEach {
                    sumTime += it
                }
                binding.tvBetter.text = "Better ${sumTime / betterTimeList.size}MS"
                Toast.makeText(requireContext(), "Better The End", Toast.LENGTH_SHORT).show()
            }
            .subscribe()
        viewModel.addDisposable(disposable)
    }

    private fun performV2DiffUtil(count: Int) {
        v2TimeList.clear()
        val dumpList = mutableListOf<BaseUiModel>()
        val workList = mutableListOf<Flowable<List<BaseUiModel>>>()
        for (idx in 0 until count) {
            workList.add(flowableList(listCount))
        }
        val disposable = Flowable.fromIterable(workList)
            .map { it.blockingFirst() }
            .map { newList ->
                val time = System.currentTimeMillis()
                DiffUtil.calculateDiff(BaseDiffUtil(dumpList, newList))
                dumpList.clear()
                dumpList.addAll(newList)
                v2TimeList.add(System.currentTimeMillis() - time)
                Timber.d("DiffUtilV2 Count ${v2TimeList.size}")
                return@map newList
            }
            .onBackpressureBuffer()
            .subscribeOn(Schedulers.from(Executors.newFixedThreadPool(2)))
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                var sumTime = 0L
                v2TimeList.forEach {
                    sumTime += it
                }
                binding.tvDiffV2.text = "DiffUtilV2 ${sumTime / v2TimeList.size}MS"
                Toast.makeText(requireContext(), "DiffUtilV2 The End", Toast.LENGTH_SHORT).show()
            }
            .subscribe()
        viewModel.addDisposable(disposable)
    }

    private fun ranList(size: Int): List<BaseUiModel> {
        val list = mutableListOf<BaseUiModel>()
        for (idx in 0 until size) {
            list.add(ranDataModel())
        }
        return list
    }

    private fun ranDataModel(): BaseUiModel {
        return when (Random.nextInt(0..110)) {
            0 -> Model1()
            1 -> Model2()
            2 -> Model3()
            3 -> Model4()
            4 -> Model5()
            5 -> Model6()
            6 -> Model7()
            7 -> Model8()
            8 -> Model9()
            9 -> Model10()
            10 -> Model11()
            11 -> Model12()
            12 -> Model13()
            13 -> Model14()
            14 -> Model15()
            15 -> Model16()
            16 -> Model17()
            17 -> Model18()
            18 -> Model19()
            19 -> Model20()
            20 -> Model21()
            21 -> Model22()
            22 -> Model23()
            23 -> Model24()
            24 -> Model25()
            25 -> Model26()
            26 -> Model27()
            27 -> Model28()
            28 -> Model29()
            29 -> Model30()
            30 -> Model31()
            31 -> Model32()
            32 -> Model33()
            33 -> Model34()
            34 -> Model35()
            35 -> Model36()
            36 -> Model37()
            37 -> Model38()
            38 -> Model39()
            39 -> Model40()
            40 -> Model41()
            41 -> Model42()
            42 -> Model43()
            43 -> Model44()
            44 -> Model45()
            45 -> Model46()
            46 -> Model47()
            47 -> Model48()
            48 -> Model49()
            49 -> Model50()
            50 -> Model51()
            51 -> Model52()
            52 -> Model53()
            53 -> Model54()
            54 -> Model55()
            55 -> Model56()
            56 -> Model57()
            57 -> Model58()
            58 -> Model59()
            59 -> Model60()
            60 -> Model61()
            61 -> Model62()
            62 -> Model63()
            63 -> Model64()
            64 -> Model65()
            65 -> Model66()
            66 -> Model67()
            67 -> Model68()
            68 -> Model69()
            69 -> Model70()
            70 -> Model71()
            71 -> Model72()
            72 -> Model73()
            73 -> Model74()
            74 -> Model75()
            75 -> Model76()
            76 -> Model77()
            77 -> Model78()
            78 -> Model79()
            79 -> Model80()
            80 -> Model81()
            81 -> Model82()
            82 -> Model83()
            83 -> Model84()
            84 -> Model85()
            85 -> Model86()
            86 -> Model87()
            87 -> Model88()
            88 -> Model89()
            89 -> Model90()
            90 -> Model91()
            91 -> Model92()
            92 -> Model93()
            93 -> Model94()
            94 -> Model95()
            95 -> Model96()
            96 -> Model97()
            97 -> Model98()
            98 -> Model99()
            99 -> Model100()
            100 -> Model101()
            101 -> Model102()
            102 -> Model103()
            103 -> Model104()
            104 -> Model105()
            105 -> Model106()
            106 -> Model107()
            107 -> Model108()
            108 -> Model109()
            109 -> Model110()
            else -> Model110()
        }
    }
}
