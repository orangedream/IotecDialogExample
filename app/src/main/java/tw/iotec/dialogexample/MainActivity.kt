package tw.iotec.dialogexample

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import tw.iotec.dialogexample.util.IotecKotlinDialogUtil


@Suppress("UNUSED_PARAMETER")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun openCustomTextInputDialog(view: View) {
        val tvAnswer1 = findViewById<TextView>(R.id.tv_answer1)
        IotecKotlinDialogUtil.launchCustomTextInputDialog(this
            ,"What's your name?"
            ,"Name"
            ,null
            ,""
            ,"OK"
            ,"Cancel"
            ,object:IotecKotlinDialogUtil.Companion.CustomTextInputDialogCallback{
                @SuppressLint("SetTextI18n")
                override fun onCancel() {
                    tvAnswer1.text = "See you again~"
                }

                @SuppressLint("SetTextI18n")
                override fun onTextInput(inputText: String?) {
                    tvAnswer1.text = "Hello, $inputText"
                }

            })

    }
    fun openCheckLinkDialog(view: View) {
        val tvAnswer2 = findViewById<TextView>(R.id.tv_answer2)
        IotecKotlinDialogUtil.launchCheckboxLinkOkCancelDialog(this
        ,"Confirm visit iotec.tw"
        ,"Have you visit iotec.tw?"
        ,"Visit iotec.tw"
        ,"Yes"
        ,"Cancel"
        ,object:IotecKotlinDialogUtil.Companion.LinkOkCancelDialogCallback{
                override fun onClickLink() {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.iotec.tw"))
                    startActivity(browserIntent)
                }

                @SuppressLint("SetTextI18n")
                override fun onClickOK() {
                    tvAnswer2.text = "Great! "
                }

                @SuppressLint("SetTextI18n")
                override fun onClickCancel() {
                    tvAnswer2.text = "Pity... "
                }

            })

    }

    fun clickSimplePasswordDialog(view: View) {
        val tvAnswer3 = findViewById<TextView>(R.id.tv_answer3)
        IotecKotlinDialogUtil.launchSimplePasswordDialog(this
        ,IotecKotlinDialogUtil.Companion.PasswordType.NumberPassword
        ,"Input passcode"
        ,"OK"
        ,"Cancel"
        ,object:IotecKotlinDialogUtil.Companion.PasswordDialogCallback{
                @SuppressLint("SetTextI18n")
                override fun onPasswordInput(password: String?) {
                    tvAnswer3.text = "Passcode : $password"
                }

                @SuppressLint("SetTextI18n")
                override fun onPasswordInputCanceled() {
                    tvAnswer3.text = "Input canceled"
                }
            })
    }

}