package com.haidv.userlisttest.userdetail.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.haidv.userlisttest.base.BaseFragment
import com.haidv.userlisttest.base.MainActivity
import com.haidv.userlisttest.R
import com.haidv.userlisttest.databinding.FragmentUserDetailBinding
import com.haidv.userlisttest.user.data.User
import com.haidv.userlisttest.utils.IOnBackPressed
import com.haidv.userlisttest.utils.Status
import kotlin.random.Random


class UserDetailFragment : BaseFragment<FragmentUserDetailBinding>(), IOnBackPressed {

    private var user: User? = null

    private val userDetailViewModel: UserDetailViewModel by lazy {
        @Suppress("CAST_NEVER_SUCCEEDS")
        ViewModelProvider(
            this,
            UserDetailViewModel.UserDetailViewModelFactory(requireActivity().application, user!!.id)
        )[UserDetailViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = it.getSerializable("USER") as User
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_user_detail
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
        (activity as MainActivity).setHeaderTitle(getString(R.string.user_detail_title))
        (activity as MainActivity).showButtonBack(true)

        binding.let {
            it.swipeLayout.setOnRefreshListener {
                refreshData()
                setBackGround()
                binding.swipeLayout.canChildScrollUp()
                binding.swipeLayout.isRefreshing = false
            }
        }
        setBackGround()
        refreshData()
    }

    private fun refreshData() {
        userDetailViewModel.getUserDetailFromAPI().observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { userDetail ->
                            showLoading(false)
                            binding.let { view ->
                                Glide.with(requireContext())
                                    .load(userDetail.avatar_url)
                                    .into(view.imageAvatar)
                                view.userDetail = userDetail
                            }
                        }
                        showLoading(false)
                    }
                    Status.ERROR -> {
                        showDialogError(resource.message)
                        showLoading(false)
                    }
                    Status.LOADING -> {
                        showLoading(true)
                    }
                }
            }
        })
    }

    @SuppressLint("Recycle")
    private fun setBackGround() {
        val background = resources.obtainTypedArray(R.array.BackGroundList)
        for (i in 0..background.length()) {
            val randomPos = Random.nextInt(background.length())
            binding.backGround.setImageResource(background.getResourceId(randomPos, 0))
            (activity as MainActivity).setStatusBarBackground(
                background.getResourceId(
                    randomPos,
                    0
                )
            )
        }
    }

    private fun showLoading(isShow: Boolean) {
        binding.progressBar.visibility = if (isShow) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun onBackPressed(): Boolean {
        return false
    }
}