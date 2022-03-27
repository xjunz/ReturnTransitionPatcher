package top.xjunz.returntransitionpatcher.sample

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View

class FirstActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
    }

    fun launchSecondActivityWithTransition(view: View) {
        val bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
        startActivity(Intent(this, SecondActivity::class.java), bundle)
    }

}