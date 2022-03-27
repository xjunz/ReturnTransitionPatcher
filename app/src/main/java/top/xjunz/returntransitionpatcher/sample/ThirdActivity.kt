package top.xjunz.returntransitionpatcher.sample

import android.app.Activity
import android.os.Bundle
import android.view.View

class ThirdActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
    }

    fun backToSecondActivity(view: View) {
        onBackPressed()
    }

}