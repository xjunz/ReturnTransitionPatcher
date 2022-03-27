package top.xjunz.returntransitionpatcher

import android.app.Activity
import android.app.Application
import android.app.Instrumentation
import android.os.Build
import android.os.Bundle
import top.xjunz.returntransitionpatcher.ReturnTransitionPatcher.patchActivity
import top.xjunz.returntransitionpatcher.ReturnTransitionPatcher.patchAll

/**
 * A patcher to fix the issue on Android 10 and above that after an [Activity] going through
 * [onStop][Activity.onStop], its (shared element) return transition is gone. You can simply call
 * [patchAll] in [Application.onCreate] to patch all activities or call [patchActivity] in
 * [Activity.onPause] (or anywhere else after [Activity.onStart] and before [Activity.onStop])
 * to patch a specific [Activity].
 *
 * The patcher supports up to API 31.
 */
object ReturnTransitionPatcher {

    /**
     * Call this to patch all activities in the [application].
     */
    fun patchAll(application: Application) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) return
        application.registerActivityLifecycleCallbacks(object :
            Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}

            override fun onActivityStarted(activity: Activity) {}

            override fun onActivityResumed(activity: Activity) {}

            override fun onActivityPaused(activity: Activity) {
                patchActivity(activity)
            }

            override fun onActivityStopped(activity: Activity) {}

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

            override fun onActivityDestroyed(activity: Activity) {}
        })
    }

    /**
     * Patch a specific [activity].
     */
    fun patchActivity(activity: Activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) return
        Instrumentation().callActivityOnSaveInstanceState(activity, Bundle())
    }
}