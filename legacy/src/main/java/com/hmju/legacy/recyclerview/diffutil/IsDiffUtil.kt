package com.hmju.legacy.recyclerview.diffutil

import androidx.recyclerview.widget.DiffUtil
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

/**
 * Description : InstanceOf 개선된 방식
 *
 * Created by juhongmin on 2022/02/19
 */
class IsDiffUtil(
    private val oldList: List<Any>,
    private val newList: List<Any>
) : DiffUtil.Callback() {
    companion object {
        inline fun <reified T : Any> compareSame(
            old: Any,
            new: Any,
            function: (T, T) -> Boolean
        ): Boolean {
            return if (old is T && new is T) {
                function(old, new)
            } else {
                false
            }
        }
    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean {
        return when (val oldItem = oldList[oldPos]) {
            is com.hmju.legacy.recyclerview.model.Model1 -> {
                compareSame<Model1>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model2 -> {
                compareSame<Model2>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model3 -> {
                compareSame<Model3>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model4 -> {
                compareSame<Model4>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model5 -> {
                compareSame<Model5>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model6 -> {
                compareSame<Model6>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model7 -> {
                compareSame<Model7>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model8 -> {
                compareSame<Model8>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model9 -> {
                compareSame<Model9>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model10 -> {
                compareSame<Model10>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model11 -> {
                compareSame<Model11>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model12 -> {
                compareSame<Model12>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model13 -> {
                compareSame<Model13>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model14 -> {
                compareSame<Model14>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model15 -> {
                compareSame<Model15>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model16 -> {
                compareSame<Model16>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model17 -> {
                compareSame<Model17>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model18 -> {
                compareSame<Model18>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model19 -> {
                compareSame<Model19>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model20 -> {
                compareSame<Model20>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model21 -> {
                compareSame<Model21>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model22 -> {
                compareSame<Model22>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model23 -> {
                compareSame<Model23>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model24 -> {
                compareSame<Model24>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model25 -> {
                compareSame<Model25>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model26 -> {
                compareSame<Model26>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model27 -> {
                compareSame<Model27>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model28 -> {
                compareSame<Model28>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model29 -> {
                compareSame<Model29>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model30 -> {
                compareSame<Model30>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model31 -> {
                compareSame<Model31>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model32 -> {
                compareSame<Model32>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model33 -> {
                compareSame<Model33>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model34 -> {
                compareSame<Model34>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model35 -> {
                compareSame<Model35>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model36 -> {
                compareSame<Model36>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model37 -> {
                compareSame<Model37>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model38 -> {
                compareSame<Model38>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model39 -> {
                compareSame<Model39>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model40 -> {
                compareSame<Model40>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model41 -> {
                compareSame<Model41>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model42 -> {
                compareSame<Model42>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model43 -> {
                compareSame<Model43>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model44 -> {
                compareSame<Model44>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model45 -> {
                compareSame<Model45>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model46 -> {
                compareSame<Model46>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model47 -> {
                compareSame<Model47>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model48 -> {
                compareSame<Model48>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model49 -> {
                compareSame<Model49>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model50 -> {
                compareSame<Model50>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model51 -> {
                compareSame<Model51>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model52 -> {
                compareSame<Model52>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model53 -> {
                compareSame<Model53>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model54 -> {
                compareSame<Model54>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model55 -> {
                compareSame<Model55>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model56 -> {
                compareSame<Model56>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model57 -> {
                compareSame<Model57>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model58 -> {
                compareSame<Model58>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model59 -> {
                compareSame<Model59>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model60 -> {
                compareSame<Model60>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model61 -> {
                compareSame<Model61>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model62 -> {
                compareSame<Model62>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model63 -> {
                compareSame<Model63>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model64 -> {
                compareSame<Model64>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model65 -> {
                compareSame<Model65>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model66 -> {
                compareSame<Model66>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model67 -> {
                compareSame<Model67>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model68 -> {
                compareSame<Model68>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model69 -> {
                compareSame<Model69>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model70 -> {
                compareSame<Model70>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model71 -> {
                compareSame<Model71>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model72 -> {
                compareSame<Model72>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model73 -> {
                compareSame<Model73>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model74 -> {
                compareSame<Model74>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model75 -> {
                compareSame<Model75>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model76 -> {
                compareSame<Model76>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model77 -> {
                compareSame<Model77>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model78 -> {
                compareSame<Model78>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model79 -> {
                compareSame<Model79>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model80 -> {
                compareSame<Model80>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model81 -> {
                compareSame<Model81>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model82 -> {
                compareSame<Model82>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model83 -> {
                compareSame<Model83>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model84 -> {
                compareSame<Model84>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model85 -> {
                compareSame<Model85>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model86 -> {
                compareSame<Model86>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model87 -> {
                compareSame<Model87>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model88 -> {
                compareSame<Model88>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model89 -> {
                compareSame<Model89>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model90 -> {
                compareSame<Model90>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model91 -> {
                compareSame<Model91>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model92 -> {
                compareSame<Model92>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model93 -> {
                compareSame<Model93>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model94 -> {
                compareSame<Model94>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model95 -> {
                compareSame<Model95>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model96 -> {
                compareSame<Model96>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model97 -> {
                compareSame<Model97>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model98 -> {
                compareSame<Model98>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model99 -> {
                compareSame<Model99>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model100 -> {
                compareSame<Model100>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model101 -> {
                compareSame<Model101>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model102 -> {
                compareSame<Model102>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model103 -> {
                compareSame<Model103>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model104 -> {
                compareSame<Model104>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model105 -> {
                compareSame<Model105>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model106 -> {
                compareSame<Model106>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model107 -> {
                compareSame<Model107>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model108 -> {
                compareSame<Model108>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model109 -> {
                compareSame<Model109>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model110 -> {
                compareSame<Model110>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            else -> false
        }
    }

    override fun areContentsTheSame(oldPos: Int, newPos: Int): Boolean {
        return when (val oldItem = oldList[oldPos]) {
            is com.hmju.legacy.recyclerview.model.Model1 -> {
                compareSame<Model1>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model2 -> {
                compareSame<Model2>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model3 -> {
                compareSame<Model3>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model4 -> {
                compareSame<Model4>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model5 -> {
                compareSame<Model5>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model6 -> {
                compareSame<Model6>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model7 -> {
                compareSame<Model7>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model8 -> {
                compareSame<Model8>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model9 -> {
                compareSame<Model9>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model10 -> {
                compareSame<Model10>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model11 -> {
                compareSame<Model11>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model12 -> {
                compareSame<Model12>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model13 -> {
                compareSame<Model13>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model14 -> {
                compareSame<Model14>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model15 -> {
                compareSame<Model15>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model16 -> {
                compareSame<Model16>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model17 -> {
                compareSame<Model17>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model18 -> {
                compareSame<Model18>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model19 -> {
                compareSame<Model19>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model20 -> {
                compareSame<Model20>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model21 -> {
                compareSame<Model21>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model22 -> {
                compareSame<Model22>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model23 -> {
                compareSame<Model23>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model24 -> {
                compareSame<Model24>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model25 -> {
                compareSame<Model25>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model26 -> {
                compareSame<Model26>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model27 -> {
                compareSame<Model27>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model28 -> {
                compareSame<Model28>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model29 -> {
                compareSame<Model29>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model30 -> {
                compareSame<Model30>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model31 -> {
                compareSame<Model31>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model32 -> {
                compareSame<Model32>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model33 -> {
                compareSame<Model33>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model34 -> {
                compareSame<Model34>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model35 -> {
                compareSame<Model35>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model36 -> {
                compareSame<Model36>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model37 -> {
                compareSame<Model37>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model38 -> {
                compareSame<Model38>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model39 -> {
                compareSame<Model39>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model40 -> {
                compareSame<Model40>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model41 -> {
                compareSame<Model41>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model42 -> {
                compareSame<Model42>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model43 -> {
                compareSame<Model43>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model44 -> {
                compareSame<Model44>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model45 -> {
                compareSame<Model45>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model46 -> {
                compareSame<Model46>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model47 -> {
                compareSame<Model47>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model48 -> {
                compareSame<Model48>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model49 -> {
                compareSame<Model49>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model50 -> {
                compareSame<Model50>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model51 -> {
                compareSame<Model51>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model52 -> {
                compareSame<Model52>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model53 -> {
                compareSame<Model53>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model54 -> {
                compareSame<Model54>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model55 -> {
                compareSame<Model55>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model56 -> {
                compareSame<Model56>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model57 -> {
                compareSame<Model57>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model58 -> {
                compareSame<Model58>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model59 -> {
                compareSame<Model59>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model60 -> {
                compareSame<Model60>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model61 -> {
                compareSame<Model61>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model62 -> {
                compareSame<Model62>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model63 -> {
                compareSame<Model63>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model64 -> {
                compareSame<Model64>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model65 -> {
                compareSame<Model65>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model66 -> {
                compareSame<Model66>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model67 -> {
                compareSame<Model67>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model68 -> {
                compareSame<Model68>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model69 -> {
                compareSame<Model69>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model70 -> {
                compareSame<Model70>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model71 -> {
                compareSame<Model71>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model72 -> {
                compareSame<Model72>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model73 -> {
                compareSame<Model73>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model74 -> {
                compareSame<Model74>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model75 -> {
                compareSame<Model75>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model76 -> {
                compareSame<Model76>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model77 -> {
                compareSame<Model77>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model78 -> {
                compareSame<Model78>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model79 -> {
                compareSame<Model79>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model80 -> {
                compareSame<Model80>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model81 -> {
                compareSame<Model81>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model82 -> {
                compareSame<Model82>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model83 -> {
                compareSame<Model83>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model84 -> {
                compareSame<Model84>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model85 -> {
                compareSame<Model85>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model86 -> {
                compareSame<Model86>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model87 -> {
                compareSame<Model87>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model88 -> {
                compareSame<Model88>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model89 -> {
                compareSame<Model89>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model90 -> {
                compareSame<Model90>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model91 -> {
                compareSame<Model91>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model92 -> {
                compareSame<Model92>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model93 -> {
                compareSame<Model93>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model94 -> {
                compareSame<Model94>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model95 -> {
                compareSame<Model95>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model96 -> {
                compareSame<Model96>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model97 -> {
                compareSame<Model97>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model98 -> {
                compareSame<Model98>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model99 -> {
                compareSame<Model99>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model100 -> {
                compareSame<Model100>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model101 -> {
                compareSame<Model101>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model102 -> {
                compareSame<Model102>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model103 -> {
                compareSame<Model103>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model104 -> {
                compareSame<Model104>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model105 -> {
                compareSame<Model105>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model106 -> {
                compareSame<Model106>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model107 -> {
                compareSame<Model107>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model108 -> {
                compareSame<Model108>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model109 -> {
                compareSame<Model109>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            is com.hmju.legacy.recyclerview.model.Model110 -> {
                compareSame<Model110>(oldItem, newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            else -> false
        }
    }
}