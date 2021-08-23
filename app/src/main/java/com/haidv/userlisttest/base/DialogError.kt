package com.haidv.userlisttest.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import com.haidv.userlisttest.R

class DialogError(context: Context?, private val content: String?) : Dialog(context!!) {

    var textViewContent: TextView? = null
    var buttonOk: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_error)
        window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window!!.attributes.windowAnimations = R.style.DialogBotToTopAnim
        setCancelable(false)

        textViewContent = findViewById(R.id.textViewContent)
        buttonOk = findViewById(R.id.buttonOk)

        textViewContent?.text = content

        buttonOk?.setOnClickListener { dismiss() }
    }

}