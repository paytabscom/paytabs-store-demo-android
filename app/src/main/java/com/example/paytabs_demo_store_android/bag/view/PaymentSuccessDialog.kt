package com.example.paytabs_demo_store_android.bag.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.widget.AppCompatButton
import com.example.paytabs_demo_store_android.R
import com.example.paytabs_demo_store_android.utils.GoToHomeListener

class PaymentSuccessDialog(
    context: Context,
    private val listener: GoToHomeListener
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_payment_success)
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        val button: AppCompatButton = findViewById(R.id.goToHomeButton)
        button.setOnClickListener {
            dismiss()
            listener.onClick()
        }
    }

    override fun onStart() {
        super.onStart()
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

    }


}