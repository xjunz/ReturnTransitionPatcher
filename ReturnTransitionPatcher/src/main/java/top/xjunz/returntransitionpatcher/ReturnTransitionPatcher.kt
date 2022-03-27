package top.xjunz.returntransitionpatcher

import android.app.Activity
import android.app.Application
import android.app.Instrumentation
import android.os.Build
import android.os.Bundle
import android.util.Log
import top.xjunz.returntransitionpatcher.ReturnTransitionPatcher.patchActivity
import top.xjunz.returntransitionpatcher.ReturnTransitionPatcher.patchAll

/**
 * A patcher to fix the issue on Android 10 and above that after an [Activity] going through
 * [onStop][Activity.onStop], its (shared element) return transition is gone.
 *
 * You can simply call [patchAll] to patch all activities or call [patchActivity] to patch a
 * specific [Activity].
 *
 */
object ReturnTransitionPatcher {

    private val TAG_PATCH_STATE = "xjunz.ReturnTransitionPatcher.PATCHED".hashCode()

    /**
     * Call this to patch all activities in the [application].
     */
    fun patchAll(application: Application) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) return
        application.registerActivityLifecycleCallbacks(object :
            ActivityLifecycleCallbacksAdapter {

            override fun onActivityPreStopped(activity: Activity) {
                doPatch(activity)
            }
        })
    }

    private fun doPatch(activity: Activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) return
        // When the [onActivityPreStopped] is called because of finishing, ignore it.
        if (activity.isFinishing) return
        // Store a patch state in the decor view's tag to avoid repeated patching.
        val decor = activity.window.peekDecorView()
        if (decor.getTag(TAG_PATCH_STATE) != true) {
            Instrumentation().callActivityOnSaveInstanceState(activity, Bundle())
            decor.setTag(TAG_PATCH_STATE, true)
            Log.i("ReturnTransitionPatcher", "${activity.javaClass.simpleName} is patched.")
        }
    }

    /**
     * Patch a specific [activity]. You may call this anytime before the first possible [Activity.onStop],
     * such as in [Activity.onCreate].
     */
    fun patchActivity(activity: Activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) return
        activity.registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacksAdapter {
            override fun onActivityPreStopped(activity: Activity) {
                doPatch(activity)
            }
        })
    }
}