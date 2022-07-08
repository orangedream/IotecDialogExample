package tw.iotec.dialogexample.util

import android.app.AlertDialog
import android.content.Context
import android.widget.EditText

class IotecKotlinDialogUtil {
    companion object{
        //************************************************************************************
        // OK/Cancel Dialog using AlertDialog
        //************************************************************************************
        private var okCancelAlertDialog: AlertDialog? = null

        interface ConfirmDialogCallback {
            fun onResult(isOK: Boolean)
        }

        fun launchOkCancelAlertDialog(
            context: Context,
            title: String?,
            message: String?,
            okStr: String,
            cancelStr: String?,
            callback: ConfirmDialogCallback
        ) {
            okCancelAlertDialog?.dismiss() // dismiss previous dialog if exists

            val alert = AlertDialog.Builder(context)
            alert.setTitle(title)
            alert.setMessage(message)

            alert.setPositiveButton(
                okStr
            ) { _, _ ->
                callback.onResult(true)
                okCancelAlertDialog?.dismiss()
                okCancelAlertDialog = null
            }

            if(cancelStr != null) {
                alert.setNegativeButton(
                    cancelStr
                ) { _, _ ->
                    callback.onResult(false)
                    okCancelAlertDialog?.dismiss()
                    okCancelAlertDialog = null
                }
            }

            okCancelAlertDialog = alert.create()
            alert.show()
        }

        //************************************************************************************
        // OK/Cancel/text input TextDialog using AlertDialog
        //************************************************************************************
        private var textInputDialog: AlertDialog? = null


        interface TextDialogCallback {
            fun onTextInput(isOK: Boolean, inputText: String?)
        }

        fun launchTextInputDialog(
            context: Context,
            title: String?,
            defaultMessage: String?,
            hint:String?,
            okStr: String,
            cancelStr: String?,
            callback: TextDialogCallback
        ) {
            textInputDialog?.dismiss()

            val alert = AlertDialog.Builder(context)
            alert.setTitle(title)

            val edittext = EditText(context)
            edittext.setText(defaultMessage)
            edittext.hint = hint
            alert.setView(edittext)

            alert.setPositiveButton(okStr
            ) { _, _ ->
                val inputText = edittext.text.toString()
                callback.onTextInput(true, inputText)
                textInputDialog?.dismiss()
                textInputDialog = null
            }
            if (cancelStr != null) {
                alert.setNegativeButton(cancelStr
                ) { _, _ ->
                    callback.onTextInput(false, null)
                    textInputDialog?.dismiss()
                    textInputDialog = null
                }
            }
            textInputDialog = alert.create()
            textInputDialog?.show()
        }

        //************************************************************************************
        // Three options dialog using AlertDialog
        //************************************************************************************
        private var threeOptionDialog: AlertDialog? = null
        enum class ThreeOptionsDialogSelection {
            OPTION1, OPTION2, OPTION3
        }

        interface ThreeOptionDialogCallback {
            fun onResult(option: ThreeOptionsDialogSelection)
        }

        fun launchThreeOptionDialog(
            context: Context,
            title: String?,
            message: String?,
            option1: String,
            option2: String?,
            option3: String?, callback: ThreeOptionDialogCallback
        ) {
            threeOptionDialog?.dismiss()

            val alert = AlertDialog.Builder(context)
            alert.setTitle(title)
            if (message != null) alert.setMessage(message)
            alert.setPositiveButton(
                option1
            ) { _, _ ->
                callback.onResult(ThreeOptionsDialogSelection.OPTION1)
                threeOptionDialog?.dismiss()
                threeOptionDialog = null
            }

            if(option2 != null) {
                alert.setNegativeButton(
                    option2
                ) { _, _ ->
                    callback.onResult(ThreeOptionsDialogSelection.OPTION2)
                    threeOptionDialog?.dismiss()
                    threeOptionDialog = null
                }
            }

            if(option3 != null) {
                alert.setNeutralButton(
                    option3
                ) { _, _ ->
                    callback.onResult(ThreeOptionsDialogSelection.OPTION3)
                    threeOptionDialog?.dismiss()
                    threeOptionDialog = null
                }
            }

            threeOptionDialog = alert.create()
            threeOptionDialog?.show()
        }
    }
}