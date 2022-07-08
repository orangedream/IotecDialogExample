package tw.iotec.dialogexample

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import tw.iotec.dialogexample.util.IotecKotlinDialogUtil
import tw.iotec.dialogexample.util.IotecKotlinDialogUtil.Companion.ThreeOptionsDialogSelection.*

@Suppress("UNUSED_PARAMETER")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openYesNoDialog(view: View) {
        IotecKotlinDialogUtil.launchOkCancelAlertDialog(this,
            "Hello World",
            "Can you hear me?",
            "Yes",
            "No",
            object:IotecKotlinDialogUtil.Companion.ConfirmDialogCallback
            {
                @SuppressLint("SetTextI18n")
                override fun onResult(isOK: Boolean) {
                    val tvAnswer1 = findViewById<TextView>(R.id.tv_answer1)
                    if(isOK){
                        tvAnswer1.text = "Yes I heard you."
                    }else{
                        tvAnswer1.text = "Nope. What did you said?"
                    }
                }
            })
    }

    fun openTextInputDialog(view: View) {
        IotecKotlinDialogUtil.launchTextInputDialog(
            this,
            "Input user name",
            "",
            "What's your name?",
            "OK",
            "Cancel",
            object:IotecKotlinDialogUtil.Companion.TextDialogCallback{
                @SuppressLint("SetTextI18n")
                override fun onTextInput(isOK: Boolean, inputText: String?) {
                    val tvAnswer2 = findViewById<TextView>(R.id.tv_answer2)
                    if(isOK){
                        tvAnswer2.text = "Hello $inputText."
                    }else{
                        tvAnswer2.text = "I cannot hear you."
                    }
                }
            }
        )

    }

    fun openThreeOptionsDialog(view: View) {
        IotecKotlinDialogUtil.launchThreeOptionDialog(this,
            "Party invitation",
            "Will you go to the party?",
            "Yes!",
            "Nope",
            "Maybe",
            object:IotecKotlinDialogUtil.Companion.ThreeOptionDialogCallback{
                @SuppressLint("SetTextI18n")
                override fun onResult(option: IotecKotlinDialogUtil.Companion.ThreeOptionsDialogSelection) {
                    val tvAnswer3 = findViewById<TextView>(R.id.tv_answer3)
                    when(option){
                        OPTION1 -> tvAnswer3.text = "That's great!"
                        OPTION2 -> tvAnswer3.text = "Oh! That's pity!"
                        OPTION3 -> tvAnswer3.text = "Come on~ Let's go~"
                    }
                }
            }
        )

    }
}