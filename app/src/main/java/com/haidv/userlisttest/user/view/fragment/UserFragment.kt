package com.haidv.userlisttest.user.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.haidv.userlisttest.base.BaseFragment
import com.haidv.userlisttest.base.MainActivity
import com.haidv.userlisttest.R
import com.haidv.userlisttest.databinding.FragmentUserBinding
import com.haidv.userlisttest.user.data.User
import com.haidv.userlisttest.user.view.adapter.UserAdapter
import com.haidv.userlisttest.user.view.viewmodel.UserViewModel
import com.haidv.userlisttest.utils.Status

class UserFragment : BaseFragment<FragmentUserBinding>() {

    private var adapter: UserAdapter? = null

    private val userViewModel: UserViewModel by lazy {
        @Suppress("CAST_NEVER_SUCCEEDS")
        ViewModelProvider(
            this,
            UserViewModel.UserViewModelFactory(requireActivity().application)
        )[UserViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_user
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        initUI()
        return view
    }

    private fun initUI() {
        (activity as MainActivity).setHeaderTitle(getString(R.string.user_list_title))
        (activity as MainActivity).showButtonBack(false)
        (activity as MainActivity).setStatusBarBackground(R.drawable.bg_back_ground)

        adapter = UserAdapter(requireContext(), onItemLicked)
        binding.let {
            it.rcUser.setHasFixedSize(true)
            it.rcUser.layoutManager = LinearLayoutManager(context)
            it.rcUser.adapter = adapter

            it.swipeLayout.setOnRefreshListener {
                refreshData()
            }
        }
        refreshData()
    }

    private fun refreshData() {
        userViewModel.getUserFromAPI().observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.swipeLayout.isRefreshing = false
                        userViewModel.deleteAllUserToDatabase()
                        resource.data?.let { users ->
                            userViewModel.insertAllUserToDatabase(users)
                            adapter?.users = users
                        }
                    }
                    Status.ERROR -> {
                        showDialogError(resource.message)
                        binding.swipeLayout.isRefreshing = false
                        getUserFromDatabase()
                    }
                    Status.LOADING -> {
                        binding.swipeLayout.isRefreshing = true
                    }
                }
            }
        })
    }

    private fun getUserFromDatabase() {
        userViewModel.getAllUser().observe(viewLifecycleOwner, {
            adapter?.users = it
        })
    }

    private val onItemLicked: (User) -> Unit = {
        val controller = findNavController()
        val bundle = Bundle()
        bundle.putSerializable("USER", it)
        controller.navigate(R.id.userDetailFragment, bundle)
    }

}