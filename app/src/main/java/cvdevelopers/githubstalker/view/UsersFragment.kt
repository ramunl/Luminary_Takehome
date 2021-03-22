package cvdevelopers.githubstalker.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import cvdevelopers.githubstalker.R
import cvdevelopers.githubstalker.databinding.FragmentUsersBinding
import cvdevelopers.githubstalker.viewmodel.RandomUsersViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class UsersFragment : Fragment() {
    private val randomUsersViewModel by viewModel<RandomUsersViewModel>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return (DataBindingUtil.inflate(inflater, R.layout.fragment_users, container, false) as FragmentUsersBinding).apply {
            lifecycleOwner = this@UsersFragment
            viewModel = randomUsersViewModel
        }.root
    }
}
