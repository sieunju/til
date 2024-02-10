package com.features.recyclerview.ui.diffutil_performance

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.NumberPicker
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import com.hmju.core.ui.base.BaseFragment
import com.hmju.core.ui.base.BaseUiModel
import com.hmju.core.ui.base.FragmentViewModel
import com.features.recyclerview.BR
import com.features.recyclerview.R
import com.features.recyclerview.databinding.FPerformanceDiffUtilBinding
import com.hmju.legacy.recyclerview.diffutil.BetterDiffUtil
import com.features.recyclerview.diffutil.DiffUtilV2
import com.hmju.legacy.recyclerview.diffutil.IsDiffUtil
import com.hmju.legacy.recyclerview.diffutil.IsLegacyDiffUtil
import com.features.recyclerview.model.*
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.Executors
import kotlin.random.Random
import kotlin.random.nextInt

/**
 * Description : DiffUtil에 대해서 개선할 방향에 대해서 다시 생각해보니 너무 아니다 싶어서
 * 더 좋은 방안이 없는지..에 대한 고민을 한 TIL
 *
 * Created by juhongmin on 2022/02/19
 */
@AndroidEntryPoint
class DiffUtilPerformanceFragment :
    BaseFragment<FPerformanceDiffUtilBinding, FragmentViewModel>(R.layout.f_performance_diff_util) {

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
        Flowable.fromIterable(workList)
            .map { it.blockingFirst() }
            .map { newList ->
                val time = System.currentTimeMillis()
                val diffResult = DiffUtil.calculateDiff(
                    com.hmju.legacy.recyclerview.diffutil.IsLegacyDiffUtil(
                        dumpList,
                        newList
                    )
                )
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
            }
            .subscribe({}, {})
    }

    @SuppressLint("SetTextI18n")
    private fun performBetterLegacy(count: Int) {
        betterLegacyTimeList.clear()
        val dumpList = mutableListOf<BaseUiModel>()
        val workList = mutableListOf<Flowable<List<BaseUiModel>>>()
        for (idx in 0 until count) {
            workList.add(flowableList(listCount))
        }
        Flowable.fromIterable(workList)
            .map { it.blockingFirst() }
            .map { newList ->
                val time = System.currentTimeMillis()
                val diffResult = DiffUtil.calculateDiff(
                    com.hmju.legacy.recyclerview.diffutil.IsDiffUtil(
                        dumpList,
                        newList
                    )
                )
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
            .subscribe({}, {})
    }

    @SuppressLint("SetTextI18n")
    private fun performBetter(count: Int) {
        betterTimeList.clear()
        val dumpList = mutableListOf<BaseUiModel>()
        val workList = mutableListOf<Flowable<List<BaseUiModel>>>()
        for (idx in 0 until count) {
            workList.add(flowableList(listCount))
        }
        Flowable.fromIterable(workList)
            .map { it.blockingFirst() }
            .map { newList ->
                val time = System.currentTimeMillis()
                val diffResult = DiffUtil.calculateDiff(
                    com.hmju.legacy.recyclerview.diffutil.BetterDiffUtil(
                        dumpList,
                        newList
                    )
                )
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
            .subscribe({}, {})
    }

    private fun performV2DiffUtil(count: Int) {
        v2TimeList.clear()
        val dumpList = mutableListOf<BaseUiModel>()
        val workList = mutableListOf<Flowable<List<BaseUiModel>>>()
        for (idx in 0 until count) {
            workList.add(flowableList(listCount))
        }
        Flowable.fromIterable(workList)
            .map { it.blockingFirst() }
            .map { newList ->
                val time = System.currentTimeMillis()
                val diffResult = DiffUtil.calculateDiff(
                    DiffUtilV2(
                        dumpList,
                        newList
                    )
                )
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
            .subscribe({}, {})
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
            0 -> com.hmju.legacy.recyclerview.model.Model1()
            1 -> com.hmju.legacy.recyclerview.model.Model2()
            2 -> com.hmju.legacy.recyclerview.model.Model3()
            3 -> com.hmju.legacy.recyclerview.model.Model4()
            4 -> com.hmju.legacy.recyclerview.model.Model5()
            5 -> com.hmju.legacy.recyclerview.model.Model6()
            6 -> com.hmju.legacy.recyclerview.model.Model7()
            7 -> com.hmju.legacy.recyclerview.model.Model8()
            8 -> com.hmju.legacy.recyclerview.model.Model9()
            9 -> com.hmju.legacy.recyclerview.model.Model10()
            10 -> com.hmju.legacy.recyclerview.model.Model11()
            11 -> com.hmju.legacy.recyclerview.model.Model12()
            12 -> com.hmju.legacy.recyclerview.model.Model13()
            13 -> com.hmju.legacy.recyclerview.model.Model14()
            14 -> com.hmju.legacy.recyclerview.model.Model15()
            15 -> com.hmju.legacy.recyclerview.model.Model16()
            16 -> com.hmju.legacy.recyclerview.model.Model17()
            17 -> com.hmju.legacy.recyclerview.model.Model18()
            18 -> com.hmju.legacy.recyclerview.model.Model19()
            19 -> com.hmju.legacy.recyclerview.model.Model20()
            20 -> com.hmju.legacy.recyclerview.model.Model21()
            21 -> com.hmju.legacy.recyclerview.model.Model22()
            22 -> com.hmju.legacy.recyclerview.model.Model23()
            23 -> com.hmju.legacy.recyclerview.model.Model24()
            24 -> com.hmju.legacy.recyclerview.model.Model25()
            25 -> com.hmju.legacy.recyclerview.model.Model26()
            26 -> com.hmju.legacy.recyclerview.model.Model27()
            27 -> com.hmju.legacy.recyclerview.model.Model28()
            28 -> com.hmju.legacy.recyclerview.model.Model29()
            29 -> com.hmju.legacy.recyclerview.model.Model30()
            30 -> com.hmju.legacy.recyclerview.model.Model31()
            31 -> com.hmju.legacy.recyclerview.model.Model32()
            32 -> com.hmju.legacy.recyclerview.model.Model33()
            33 -> com.hmju.legacy.recyclerview.model.Model34()
            34 -> com.hmju.legacy.recyclerview.model.Model35()
            35 -> com.hmju.legacy.recyclerview.model.Model36()
            36 -> com.hmju.legacy.recyclerview.model.Model37()
            37 -> com.hmju.legacy.recyclerview.model.Model38()
            38 -> com.hmju.legacy.recyclerview.model.Model39()
            39 -> com.hmju.legacy.recyclerview.model.Model40()
            40 -> com.hmju.legacy.recyclerview.model.Model41()
            41 -> com.hmju.legacy.recyclerview.model.Model42()
            42 -> com.hmju.legacy.recyclerview.model.Model43()
            43 -> com.hmju.legacy.recyclerview.model.Model44()
            44 -> com.hmju.legacy.recyclerview.model.Model45()
            45 -> com.hmju.legacy.recyclerview.model.Model46()
            46 -> com.hmju.legacy.recyclerview.model.Model47()
            47 -> com.hmju.legacy.recyclerview.model.Model48()
            48 -> com.hmju.legacy.recyclerview.model.Model49()
            49 -> com.hmju.legacy.recyclerview.model.Model50()
            50 -> com.hmju.legacy.recyclerview.model.Model51()
            51 -> com.hmju.legacy.recyclerview.model.Model52()
            52 -> com.hmju.legacy.recyclerview.model.Model53()
            53 -> com.hmju.legacy.recyclerview.model.Model54()
            54 -> com.hmju.legacy.recyclerview.model.Model55()
            55 -> com.hmju.legacy.recyclerview.model.Model56()
            56 -> com.hmju.legacy.recyclerview.model.Model57()
            57 -> com.hmju.legacy.recyclerview.model.Model58()
            58 -> com.hmju.legacy.recyclerview.model.Model59()
            59 -> com.hmju.legacy.recyclerview.model.Model60()
            60 -> com.hmju.legacy.recyclerview.model.Model61()
            61 -> com.hmju.legacy.recyclerview.model.Model62()
            62 -> com.hmju.legacy.recyclerview.model.Model63()
            63 -> com.hmju.legacy.recyclerview.model.Model64()
            64 -> com.hmju.legacy.recyclerview.model.Model65()
            65 -> com.hmju.legacy.recyclerview.model.Model66()
            66 -> com.hmju.legacy.recyclerview.model.Model67()
            67 -> com.hmju.legacy.recyclerview.model.Model68()
            68 -> com.hmju.legacy.recyclerview.model.Model69()
            69 -> com.hmju.legacy.recyclerview.model.Model70()
            70 -> com.hmju.legacy.recyclerview.model.Model71()
            71 -> com.hmju.legacy.recyclerview.model.Model72()
            72 -> com.hmju.legacy.recyclerview.model.Model73()
            73 -> com.hmju.legacy.recyclerview.model.Model74()
            74 -> com.hmju.legacy.recyclerview.model.Model75()
            75 -> com.hmju.legacy.recyclerview.model.Model76()
            76 -> com.hmju.legacy.recyclerview.model.Model77()
            77 -> com.hmju.legacy.recyclerview.model.Model78()
            78 -> com.hmju.legacy.recyclerview.model.Model79()
            79 -> com.hmju.legacy.recyclerview.model.Model80()
            80 -> com.hmju.legacy.recyclerview.model.Model81()
            81 -> com.hmju.legacy.recyclerview.model.Model82()
            82 -> com.hmju.legacy.recyclerview.model.Model83()
            83 -> com.hmju.legacy.recyclerview.model.Model84()
            84 -> com.hmju.legacy.recyclerview.model.Model85()
            85 -> com.hmju.legacy.recyclerview.model.Model86()
            86 -> com.hmju.legacy.recyclerview.model.Model87()
            87 -> com.hmju.legacy.recyclerview.model.Model88()
            88 -> com.hmju.legacy.recyclerview.model.Model89()
            89 -> com.hmju.legacy.recyclerview.model.Model90()
            90 -> com.hmju.legacy.recyclerview.model.Model91()
            91 -> com.hmju.legacy.recyclerview.model.Model92()
            92 -> com.hmju.legacy.recyclerview.model.Model93()
            93 -> com.hmju.legacy.recyclerview.model.Model94()
            94 -> com.hmju.legacy.recyclerview.model.Model95()
            95 -> com.hmju.legacy.recyclerview.model.Model96()
            96 -> com.hmju.legacy.recyclerview.model.Model97()
            97 -> com.hmju.legacy.recyclerview.model.Model98()
            98 -> com.hmju.legacy.recyclerview.model.Model99()
            99 -> com.hmju.legacy.recyclerview.model.Model100()
            100 -> com.hmju.legacy.recyclerview.model.Model101()
            101 -> com.hmju.legacy.recyclerview.model.Model102()
            102 -> com.hmju.legacy.recyclerview.model.Model103()
            103 -> com.hmju.legacy.recyclerview.model.Model104()
            104 -> com.hmju.legacy.recyclerview.model.Model105()
            105 -> com.hmju.legacy.recyclerview.model.Model106()
            106 -> com.hmju.legacy.recyclerview.model.Model107()
            107 -> com.hmju.legacy.recyclerview.model.Model108()
            108 -> com.hmju.legacy.recyclerview.model.Model109()
            109 -> com.hmju.legacy.recyclerview.model.Model110()
            else -> com.hmju.legacy.recyclerview.model.Model110()
        }
    }
}
