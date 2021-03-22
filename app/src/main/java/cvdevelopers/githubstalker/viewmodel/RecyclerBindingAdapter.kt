package cvdevelopers.githubstalker.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import java.util.*

//not used
class RecyclerBindingAdapter<T>(private val holderLayout: Int, /*private val variableId: Int, */
                                private val items: AbstractList<T>) : RecyclerView.Adapter<RecyclerBindingAdapter.BindingHolder>() {

    private var onItemClickListener: OnItemClickListener<T>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(holderLayout, parent, false)
        return BindingHolder(v)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        val item = items[position]
        //holder.binding!!.root.setOnClickListener { v: View? -> onItemClickListener?.onItemClick(position, item) }
        // holder.binding.setVariable(variableId, item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener<T>?) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener<T> {
        fun onItemClick(position: Int, item: T)
    }

    class BindingHolder(v: View?) : RecyclerView.ViewHolder(v!!) {
        val binding: ViewDataBinding? = DataBindingUtil.bind(v!!)
    }
}