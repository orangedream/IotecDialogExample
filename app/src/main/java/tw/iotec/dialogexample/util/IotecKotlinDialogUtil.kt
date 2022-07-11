package tw.iotec.dialogexample.util

import android.app.AlertDialog
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import tw.iotec.dialogexample.R


class IotecKotlinDialogUtil {
    companion object{
        //*******************************************************
        // HUD dialog
        //*******************************************************
        private var hudProgressDialog: AlertDialog? = null
        fun launchHUD(activity: AppCompatActivity, vararg message: String) {
            activity.runOnUiThread {
                hudProgressDialog?.dismiss() // dismiss previous dialog

                val builder = AlertDialog.Builder(activity, R.style.IotecSpinnerTheme)
                val inflater: LayoutInflater = activity.layoutInflater
                val contentView: View = inflater.inflate(R.layout.iotec_hud_dialog, null)
                builder.setView(contentView)
                val dialog =  builder.create()
                dialog.setCancelable(false) // Block the UI

                if (message.isNotEmpty()) {
                    val textView = contentView.findViewById<TextView>(R.id.loading_msg)
                    textView?.text = message[0]
                }

                hudProgressDialog = dialog
                dialog.show()
            }
        }

        fun dismissHUD(activity: AppCompatActivity) {
            activity.runOnUiThread{
                hudProgressDialog?.dismiss()
                hudProgressDialog = null
            }
        }

        fun launchRedHUD(activity: AppCompatActivity, vararg message: String) {
            activity.runOnUiThread {
                hudProgressDialog?.dismiss() // dismiss previous dialog

                val builder = AlertDialog.Builder(activity, R.style.IotecSpinnerTheme)
                val inflater: LayoutInflater = activity.layoutInflater
                val contentView: View = inflater.inflate(R.layout.iotec_red_hud_dialog, null)
                builder.setView(contentView)
                val dialog =  builder.create()
                dialog.setCancelable(false) // Block the UI

                if (message.isNotEmpty()) {
                    val textView = contentView.findViewById<TextView>(R.id.loading_msg)
                    textView?.text = message[0]
                }

                hudProgressDialog = dialog
                dialog.show()
            }
        }
        fun launchHUDWithColor(activity: AppCompatActivity, message: String?, color:Int) {
            activity.runOnUiThread {
                hudProgressDialog?.dismiss() // dismiss previous dialog

                val builder = AlertDialog.Builder(activity, R.style.IotecSpinnerTheme)
                val inflater: LayoutInflater = activity.layoutInflater
                val contentView: View = inflater.inflate(R.layout.iotec_red_hud_dialog, null)

                val progressBar = contentView.findViewById<ProgressBar>(R.id.loader)
                progressBar.indeterminateTintList = ColorStateList.valueOf(color)
                progressBar.indeterminateTintMode = PorterDuff.Mode.SRC_IN

                builder.setView(contentView)
                val dialog =  builder.create()
                dialog.setCancelable(false) // Block the UI

                message?.let{
                    val textView = contentView.findViewById<TextView>(R.id.loading_msg)
                    textView?.text = it
                }

                hudProgressDialog = dialog
                dialog.show()
            }
        }
    }
}