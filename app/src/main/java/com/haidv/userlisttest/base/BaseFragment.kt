package com.haidv.userlisttest.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<D : ViewDataBinding> : Fragment() {

    lateinit var binding: D

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    @LayoutRes
    abstract fun getLayoutRes(): Int

    fun showDialogError(content: String?) {
        val dialog = DialogError(context, content)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.show()
    }

}