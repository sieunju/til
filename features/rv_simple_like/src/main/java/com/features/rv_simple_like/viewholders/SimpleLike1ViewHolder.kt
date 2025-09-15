package com.features.rv_simple_like.viewholders

import android.view.ViewGroup
import com.features.rv_simple_like.BR
import com.features.rv_simple_like.R
import com.features.rv_simple_like.databinding.VhSimpleLike1Binding
import com.hmju.core.ui.base.BaseViewModel
import com.hmju.core.ui.viewholders.BaseSimpleLikeViewHolder
import timber.log.Timber

/**
 * Description :
 *
 * Created by juhongmin on 3/2/24
 */
class SimpleLike1ViewHolder(
    parent: ViewGroup,
    viewModel: BaseViewModel? = null
) : BaseSimpleLikeViewHolder<VhSimpleLike1Binding>(
    parent,
    R.layout.vh_simple_like_1
) {

    init {
        bindRequestManager(BR.requestManager, viewModel)
        binding.imgLike.setOnClickListener {
            val model = binding.model ?: return@setOnClickListener
            simpleLikeClick(it, model.model.id)
        }
    }

    override fun onBindView(item: Any) {
        binding.setVariable(BR.model, item)
    }

    override fun onRefreshLike() {
        val model = binding.model ?: return
        simpleLikeChange(binding.imgLike, model.model.id)
        Timber.d("onRefreshLike")
    }
}