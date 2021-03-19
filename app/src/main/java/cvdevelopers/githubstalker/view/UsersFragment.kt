package cvdevelopers.githubstalker.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import cvdevelopers.githubstalker.R
import cvdevelopers.githubstalker.databinding.FragmentUsersBinding
import cvdevelopers.githubstalker.utils.TAG
import cvdevelopers.githubstalker.viewmodel.RandomUsersViewModel
import kotlinx.android.synthetic.main.fragment_users.*
import org.koin.android.viewmodel.ext.android.viewModel

class UsersFragment : Fragment() {

    private val randomUsersViewModel by viewModel<RandomUsersViewModel>()
    private lateinit var usersAdapter: UsersAdapter
    private lateinit var mViewDataBinding: FragmentUsersBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_users, container, false)
        val mRootView = mViewDataBinding.root
        mViewDataBinding.lifecycleOwner = this
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setView()
        mViewDataBinding.viewModel = randomUsersViewModel
        randomUsersViewModel.getAllUsers()
        randomUsersViewModel.userList.observe(viewLifecycleOwner, {
            Log.d(TAG, it.size.toString())
            usersAdapter.setUsers(it)
        })
    }

    private fun setView() {
        usersAdapter = UsersAdapter()
        with(usersList) {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = usersAdapter
        }

    }
}
