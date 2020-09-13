package com.easymsging.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.shreyaspatil.easyupipayment.EasyUpiPayment
import com.shreyaspatil.easyupipayment.listener.PaymentStatusListener
import com.shreyaspatil.easyupipayment.model.TransactionDetails
import kotlinx.android.synthetic.main.activity_donate.*

class DonateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donate)

        btnDonate.setOnClickListener {
            val trnid = "TID" + System.currentTimeMillis()
            val amount = edAmount.text.toString().trim()
            if (amount.isNullOrEmpty() || amount.toInt() != 0){
                val payment  = EasyUpiPayment(this) {
                    this.payeeName = "Rohit"
                    this.description = "Donate for Support"
                    this.payeeVpa = "supportrohit@ybl"
                    this.amount = "$amount.0"
                    this.transactionId = trnid
                    this.transactionRefId = trnid

                }

                payment.startPayment()

                payment.setPaymentStatusListener(object : PaymentStatusListener{
                    override fun onTransactionCancelled() {
                        Toast.makeText(this@DonateActivity, "Faild", Toast.LENGTH_SHORT).show()
                    }

                    override fun onTransactionCompleted(transactionDetails: TransactionDetails) {
                        Toast.makeText(this@DonateActivity, "Thank You For Supporting", Toast.LENGTH_SHORT).show()
                        finish()
                    }

                })
            }

        }


    }
}