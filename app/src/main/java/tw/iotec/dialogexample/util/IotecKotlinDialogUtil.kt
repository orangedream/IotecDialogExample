package tw.iotec.dialogexample.util

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.InputType
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import tw.iotec.dialogexample.R


class IotecKotlinDialogUtil {
    companion object{
        //************************************************************************************
        // OK/Cancel/text input TextDialog using custom xml
        //************************************************************************************
        private var customTextInputDialog: Dialog? = null


        interface CustomTextInputDialogCallback {
            fun onCancel()
            fun onTextInput(inputText: String?)
        }

        fun launchCustomTextInputDialog(
            context: Context,
            title: String,
            description: String?,
            defaultText: String?,
            hint : String?,
            okStr: String,
            cancelStr: String?,
            callback: CustomTextInputDialogCallback
        ) {
            customTextInputDialog?.dismiss()

            customTextInputDialog = Dialog(context)
            customTextInputDialog?.setCancelable(false)
            customTextInputDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            customTextInputDialog?.setContentView(R.layout.iotec_custom_text_input_dialog)

            val tvTitle = customTextInputDialog?.findViewById<TextView>(R.id.tv_title)
            tvTitle?.text = title

            val tvDesc = customTextInputDialog?.findViewById<TextView>(R.id.tv_desc)
            description?.let{tvDesc?.setText(it)}

            val etInput = customTextInputDialog?.findViewById<EditText>(R.id.et_input)
            defaultText?.let { etInput?.setText(it) }
            if (hint != null) {
                etInput?.hint = hint
            }else{
                etInput?.hint = ""
            }

            val tvCancel = customTextInputDialog?.findViewById<TextView>(R.id.tv_cancel)
            tvCancel?.text = cancelStr
            tvCancel?.setOnClickListener {
                callback.onCancel()
                customTextInputDialog?.dismiss()
                customTextInputDialog= null
            }

            val tvOK = customTextInputDialog?.findViewById<TextView>(R.id.tv_ok)
            tvOK?.text = okStr
            tvOK?.setOnClickListener {
                callback.onTextInput(etInput?.text.toString())
                customTextInputDialog?.dismiss()
                customTextInputDialog= null
            }

            customTextInputDialog?.show()
        }

        //************************************************************************************
        // Checkbox with message, link button, and OK/Cancel Dialog using Dialog with xml
        //************************************************************************************
        private var checkboxOkCancelDialog: Dialog? = null
        interface LinkOkCancelDialogCallback {
            fun onClickLink()
            fun onClickOK()
            fun onClickCancel()
        }

        fun launchCheckboxLinkOkCancelDialog(
            context: Context,
            title: String,
            message: String?,
            linkMessage: String?,
            okStr: String,
            cancelStr: String?,
            callback: LinkOkCancelDialogCallback
        ) {
            checkboxOkCancelDialog?.dismiss()

            checkboxOkCancelDialog = Dialog(context)
            checkboxOkCancelDialog?.setCancelable(false)
            checkboxOkCancelDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            checkboxOkCancelDialog?.setContentView(R.layout.iotec_dialog_check_link_ok_cancel)
            val tvTitle: TextView?  = checkboxOkCancelDialog?.findViewById(R.id.tv_title)
            tvTitle?.text = title

            if(message!=null) {
                val tvMessage: TextView? = checkboxOkCancelDialog?.findViewById(R.id.tv_message)
                tvMessage?.text = message
            }

            val tvLink: TextView? = checkboxOkCancelDialog?.findViewById(R.id.tv_link)
            if(linkMessage != null) {
                tvLink?.text = linkMessage
            }

            val tvOK: TextView? = checkboxOkCancelDialog?.findViewById(R.id.tv_ok)
            tvOK?.text = okStr

            val tvCancel: TextView? = checkboxOkCancelDialog?.findViewById(R.id.tv_cancel)
            if(cancelStr!=null) {
                tvCancel?.text = cancelStr
            }

            val cbCheckbox: CheckBox? = checkboxOkCancelDialog?.findViewById(R.id.checkbox)

            tvLink?.setOnClickListener {
                callback.onClickLink()
                checkboxOkCancelDialog?.dismiss()
                checkboxOkCancelDialog = null
            }
            tvCancel?.setOnClickListener {
                callback.onClickCancel()
                checkboxOkCancelDialog?.dismiss()
                checkboxOkCancelDialog = null
            }
            tvOK?.setOnClickListener {
                if (cbCheckbox?.isChecked == true) {
                    callback.onClickOK()
                    checkboxOkCancelDialog?.dismiss()
                    checkboxOkCancelDialog = null
                }
            }
            checkboxOkCancelDialog?.show()
        }
        //************************************************************************************
        // Simple Password Dialog
        // Simple dialog for input password
        //************************************************************************************
        var pwdDialog: AlertDialog? = null

        enum class PasswordType {
            NumberPassword, TextPassword, VisiblePassword, WebPassword
        }

        interface PasswordDialogCallback {
            fun onPasswordInput(password: String?)
            fun onPasswordInputCanceled()
        }

        fun launchSimplePasswordDialog(
            context: Context?,
            passwordType: PasswordType?,
            title: String?,
            okStr: String?,
            cancelStr: String?,
            callback: PasswordDialogCallback
        ) {
            val alert: AlertDialog.Builder = AlertDialog.Builder(context)
            val edittext = EditText(context)
            edittext.inputType = when (passwordType) {
                PasswordType.WebPassword -> InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD
                PasswordType.TextPassword -> InputType.TYPE_TEXT_VARIATION_PASSWORD
                PasswordType.NumberPassword -> InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_VARIATION_PASSWORD
                PasswordType.VisiblePassword -> InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                else -> InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            alert.setTitle(title)
            alert.setView(edittext)
            alert.setPositiveButton(okStr
            ) { _, _ ->
                val password = edittext.text.toString()
                callback.onPasswordInput(password)
                pwdDialog?.dismiss()
                pwdDialog = null
            }
            alert.setNegativeButton(cancelStr
            ) { _, _ ->
                callback.onPasswordInputCanceled()
                pwdDialog?.dismiss()
                pwdDialog = null
            }
            pwdDialog = alert.create()
            pwdDialog?.show()
        }
    }
}