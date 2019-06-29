package soup.qr.ui.databinding

import androidx.annotation.CallSuper
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import soup.qr.BR

open class DataBindingViewHolder<T>(
    private val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {

    @CallSuper
    open fun bind(item: T?) {
        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
    }
}
