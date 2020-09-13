package com.easymsging.android

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallState
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.shreyaspatil.easyupipayment.EasyUpiPayment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
       // supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        val num = PhoneNumberUtils.getNumberFromIntent(intent,this)
        setContentView(R.layout.activity_main)
        edNumber.requestFocus()


        img_donate.setOnClickListener {
            Intent(this, DonateActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        if (!num.isNullOrEmpty()){
            edNumber.setText(num)
            edNumber.nextFocusDownId
        }

            button2.setOnClickListener {
                val cntryCode: String = edCode.text.toString().trim()
                val number: String = edNumber.text.toString().trim()
                if (cntryCode.isNullOrBlank()) {
                    edCode.error = "Enter Country Code"
                    return@setOnClickListener
                }
                if (number.isNullOrBlank()) {
                    edNumber.error = "Enter  Mobile Number"
                    return@setOnClickListener
                }

                val mobile:String = cntryCode+number
                val message:String = edText.text.toString()

                val link: String = "https://api.whatsapp.com/send?phone=$mobile&text=$message"

                val intent = Intent(Intent.ACTION_VIEW)
                intent.setData(Uri.parse(link))

                startActivity(Intent.createChooser(intent, null))
            }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

    }
}