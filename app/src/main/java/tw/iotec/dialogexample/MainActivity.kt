package tw.iotec.dialogexample

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import tw.iotec.dialogexample.util.IotecKotlinDialogUtil

@Suppress("UNUSED_PARAMETER")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showOnlySpinner(view: View) {
        IotecKotlinDialogUtil.launchHUD(this)
        Thread {
            Thread.sleep(3000)
            IotecKotlinDialogUtil.dismissHUD(this)
        }.start()
    }
    fun showSpinnerWithText(view: View) {
        IotecKotlinDialogUtil.launchHUD(this,"Loading...")
        Thread {
            Thread.sleep(3000)
            IotecKotlinDialogUtil.dismissHUD(this)
        }.start()

    }
    fun showRedSpinner(view: View) {
        IotecKotlinDialogUtil.launchRedHUD(this)
        Thread {
            Thread.sleep(3000)
            IotecKotlinDialogUtil.dismissHUD(this)
        }.start()
    }

    fun showMagentaSpinner(view: View) {
        IotecKotlinDialogUtil.launchHUDWithColor(this,"Hello Magenta!", Color.MAGENTA)
        Thread {
            Thread.sleep(3000)
            IotecKotlinDialogUtil.dismissHUD(this)
        }.start()
    }
}