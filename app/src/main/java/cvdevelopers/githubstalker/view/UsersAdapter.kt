package cvdevelopers.githubstalker.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import cvdevelopers.githubstalker.R
import cvdevelopers.githubstalker.databinding.UsersRowBinding
import cvdevelopers.githubstalker.viewmodel.observable.RandomUserObservable


class UsersAdapter : RecyclerView.Adapter<UsersAdapter.RandomUsersViewHolder>() {

    var userList: List<RandomUserObservable> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomUsersViewHolder {
        val viewBinding: UsersRowBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.users_row, parent, false
        )
        return RandomUsersViewHolder(viewBinding)
    }


    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: RandomUsersViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun setUsers(users: List<RandomUserObservable>) {
        this.userList = users
        notifyDataSetChanged()
    }

    inner class RandomUsersViewHolder(private val viewBinding: UsersRowBinding) :
            RecyclerView.ViewHolder(viewBinding.root) {

        fun onBind(position: Int) {
            val userEntity = userList[position]
            viewBinding.users = userEntity
        }
    }

}


