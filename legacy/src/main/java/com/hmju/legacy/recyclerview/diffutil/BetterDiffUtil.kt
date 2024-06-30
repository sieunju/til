package com.hmju.legacy.recyclerview.diffutil

import androidx.recyclerview.widget.DiffUtil

/**
 * Description :
 *
 * Created by juhongmin on 2022/02/19
 */
class BetterDiffUtil(
    private val oldList: List<Any>,
    private val newList: List<Any>
) : DiffUtil.Callback() {

    companion object {
        val diffMap: HashMap<Any, String> by lazy { HashMap() }
    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    private fun getSimpleNameMap(obj: Any): String {
        if (!diffMap.containsKey(obj)) {
            diffMap[obj] = obj::class.java.simpleName
        }
        return diffMap[obj] ?: ""
    }

    override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean {
//        val simpleName = getSimpleNameMap(oldList[oldPos])
//        JLogger.d("SimpleName $simpleName")
        return when (getSimpleNameMap(oldList[oldPos])) {
            "Model1" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model1>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model2" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model2>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model3" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model3>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model4" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model4>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model5" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model5>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model6" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model6>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model7" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model7>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model8" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model8>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model9" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model9>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model10" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model10>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model11" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model11>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model12" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model12>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model13" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model13>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model14" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model14>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model15" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model15>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model16" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model16>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model17" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model17>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model18" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model18>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model19" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model19>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model20" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model20>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model21" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model21>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model22" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model22>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model23" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model23>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model24" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model24>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model25" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model25>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model26" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model26>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model27" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model27>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model28" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model28>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model29" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model29>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model30" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model30>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model31" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model31>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model32" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model32>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model33" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model33>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model34" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model34>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model35" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model35>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model36" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model36>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model37" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model37>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model38" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model38>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model39" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model39>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model40" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model40>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model41" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model41>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model42" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model42>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model43" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model43>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model44" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model44>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model45" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model45>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model46" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model46>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model47" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model47>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model48" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model48>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model49" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model49>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model50" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model50>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model51" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model51>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model52" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model52>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model53" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model52>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model54" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model52>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model55" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model55>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model56" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model56>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model57" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model57>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model58" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model58>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model59" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model59>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model60" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model60>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model61" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model61>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model62" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model62>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model63" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model63>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model64" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model64>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model65" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model65>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model66" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model66>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model67" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model67>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model68" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model68>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model69" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model69>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model70" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model70>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model71" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model71>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model72" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model72>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model73" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model73>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model74" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model74>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model75" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model75>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model76" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model76>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model77" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model77>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model78" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model78>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model79" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model79>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model80" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model80>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model81" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model81>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model82" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model82>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model83" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model83>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model84" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model84>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model85" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model85>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model86" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model86>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model87" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model87>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model88" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model88>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model89" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model89>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model90" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model90>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model91" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model91>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model92" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model92>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model93" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model93>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model94" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model94>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model95" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model95>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model96" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model96>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model97" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model97>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model98" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model98>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model99" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model99>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model100" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model100>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model101" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model101>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model102" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model102>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model103" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model103>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model104" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model104>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model105" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model105>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model106" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model106>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model107" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model107>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model108" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model108>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model109" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model109>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model110" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model110>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            else -> false
        }
    }

    override fun areContentsTheSame(oldPos: Int, newPos: Int): Boolean {
        return when (getSimpleNameMap(oldList[oldPos])) {
            "Model1" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model1>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model2" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model2>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model3" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model3>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model4" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model4>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model5" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model5>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model6" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model6>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model7" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model7>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model8" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model8>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model9" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model9>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model10" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model10>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model11" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model11>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model12" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model12>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model13" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model13>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model14" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model14>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model15" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model15>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model16" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model16>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model17" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model17>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model18" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model18>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model19" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model19>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model20" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model20>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model21" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model21>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model22" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model22>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model23" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model23>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model24" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model24>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model25" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model25>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model26" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model26>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model27" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model27>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model28" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model28>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model29" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model29>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model30" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model30>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model31" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model31>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model32" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model32>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model33" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model33>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model34" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model34>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model35" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model35>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model36" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model36>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model37" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model37>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model38" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model38>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model39" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model39>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model40" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model40>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model41" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model41>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model42" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model42>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model43" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model43>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model44" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model44>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model45" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model45>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model46" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model46>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model47" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model47>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model48" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model48>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model49" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model49>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model50" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model50>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model51" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model51>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model52" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model52>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model53" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model52>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model54" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model52>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model55" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model55>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model56" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model56>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model57" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model57>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model58" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model58>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model59" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model59>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            "Model60" -> {
                IsDiffUtil.compareSame<com.hmju.legacy.recyclerview.model.Model60>(oldList[oldPos], newList[newPos]) { old, new ->
                    old.id == new.id
                }
            }
            else -> false
        }
    }
}
