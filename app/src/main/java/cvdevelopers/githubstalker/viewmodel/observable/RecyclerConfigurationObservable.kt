package cvdevelopers.githubstalker.viewmodel.observable

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cvdevelopers.githubstalker.BR

class RecyclerConfigurationObservable : BaseObservable() {

    @get:Bindable
    var adapter: RecyclerView.Adapter<*>? = null
        set(adapter) {
            field = adapter
            notifyPropertyChanged(BR.adapter)
        }

    companion object {
        @JvmStatic
        @BindingAdapter("app:configuration")
        fun configureRecyclerView(recyclerView: RecyclerView, configurationObservable: RecyclerConfigurationObservable) {
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, LinearLayoutManager.VERTICAL, false)
            recyclerView.itemAnimator = DefaultItemAnimator()
            recyclerView.adapter = configurationObservable.adapter
        }
    }
}