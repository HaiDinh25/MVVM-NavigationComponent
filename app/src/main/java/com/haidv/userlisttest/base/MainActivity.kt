package com.haidv.userlisttest.base

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.haidv.userlisttest.R
import com.haidv.userlisttest.databinding.ActivityMainBinding
import com.haidv.userlisttest.utils.IOnBackPressed

class MainActivity : AppCompatActivity() {

    lateinit var controller: NavController
    private var binding: ActivityMainBinding? = null
    private var header: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initUI()
        controller = findNavController(R.id.navHostFragment)
    }

    private fun initUI() {
        header = binding?.layoutHeader
        setStatusBarBackground(R.drawable.bg_back_ground)
    }

    fun setStatusBarBackground(backGround: Int) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
        val background = ContextCompat.getDrawable(this, backGround)
        window.setBackgroundDrawable(background)
    }

    fun setHeaderTitle(title: String) {
        val textViewTitle: TextView? = header?.findViewById(R.id.textViewTitle)
        textViewTitle?.text = title
    }

    fun showButtonBack(isShow: Boolean) {
        val buttonBack: TextView? = header?.findViewById(R.id.buttonBack)
        buttonBack?.visibility = if (isShow) {
            View.VISIBLE
        } else {
            View.GONE
        }
        buttonBack?.setOnClickListener { onBackPressed() }
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.container)
        if (fragment !is IOnBackPressed || !(fragment as IOnBackPressed).onBackPressed()) {
            super.onBackPressed()
        }
    }

}