package top.xjunz.returntransitionpatcher.sample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.transition.Slide
import android.view.View
import android.widget.Switch
import top.xjunz.returntransitionpatcher.ReturnTransitionPatcher

class SecondActivity : Activity() {

    private val swEnablePatch: Switch by lazy {
        findViewById(R.id.sw_enable_patch)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.enterTransition = Slide()
        setContentView(R.layout.activity_second)
    }

    override fun onPause() {
        super.onPause()
        if (swEnablePatch.isChecked) {
            ReturnTransitionPatcher.patchActivity(this)
        }
    }

    fun launchThirdActivity(view: View) {
        startActivity(Intent(this, ThirdActivity::class.java))
    }

    fun backToFirstActivity(view: View) {
        onBackPressed()
    }

}