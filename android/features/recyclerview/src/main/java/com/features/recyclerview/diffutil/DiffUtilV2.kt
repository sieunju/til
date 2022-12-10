package com.features.recyclerview.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.hmju.core.ui.base.BaseUiModel
import com.features.recyclerview.enums.DiffEnum
import com.features.recyclerview.model.GoodsOneUiModel
import com.features.recyclerview.model.GoodsTwoUiModel
import java.util.concurrent.ConcurrentHashMap

/**
 * Description :
 *
 * Created by juhongmin on 2022/06/26
 */
class DiffUtilV2(
    private val oldList: List<BaseUiModel>,
    private val newList: List<BaseUiModel>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize(): Int = newList.size

    companion object {
        val diffEnumMap: ConcurrentHashMap<String, DiffEnum> = ConcurrentHashMap()
    }

    override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean {
        val oldItem = oldList[oldPos]
        val newItem = newList[newPos]
        return if (oldItem.getClassName() == newItem.getClassName()) {
            oldItem.areItemsTheSame(newItem)
        } else {
            false
        }
    }

    override fun areContentsTheSame(oldPos: Int, newPos: Int): Boolean {
        val oldItem = oldList[oldPos]
        val newItem = newList[newPos]
        return if (oldItem.getClassName() == newItem.getClassName()) {
            oldItem.areContentsTheSame(newItem)
        } else {
            false
        }
    }

    private fun putDiffMap(oldItem: BaseUiModel, newItem: BaseUiModel) {
        if (!diffEnumMap.containsKey(oldItem.getClassName())) {
            if (oldItem is GoodsOneUiModel) {
                diffEnumMap["GoodsOneUiModel"] = DiffEnum.GOODS_ONE
            } else if (oldItem is GoodsTwoUiModel) {
                diffEnumMap["GoodsTwoUiModel"] = DiffEnum.GOODS_TWO
            }
        }

        if (!diffEnumMap.containsKey(newItem.getClassName())) {
            if (newItem is GoodsOneUiModel) {
                diffEnumMap["GoodsOneUiModel"] = DiffEnum.GOODS_ONE
            } else if (newItem is GoodsTwoUiModel) {
                diffEnumMap["GoodsTwoUiModel"] = DiffEnum.GOODS_TWO
            }
        }
    }
}
