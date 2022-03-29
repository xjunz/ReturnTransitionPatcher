# Android Return Transition Patcher ![](https://img.shields.io/badge/maven-v1.1.0-green)

> A patcher to workaround the issue on Android 10 and above that after an `Activity` going through
> `onStop()`, its (shared element) return transition is gone.
>
> 修复Android 10及以上当`Activity`执行完`onStop`后，该`Activity`的 (Shared Element) Return Transition消失的问题。

## How and why it works?  该补丁是如何工作的？

See the [post](https://www.xjunz.top/2022/03/28/Android-Return-Transition-Patcher/) here (in Chinese).  请查看这篇[博文](https://www.xjunz.top/2022/03/28/Android-Return-Transition-Patcher/)。

## Any example?  有没有例子？

You can `git clone` this project and `run app` to confirm the return transition bug and to see if the patch works.

你可以`git clone`此项目，然后`run app`，在你的设备上确认这个BUG以及检查此补丁是否生效。

## Apply to your project  在你的项目中使用

- Add this dependency to your project.  将此依赖添加到你的项目中：

```groovy
// You can always keep this patch up to date. 你可以始终保持此补丁为最新版。
implementation 'io.github.xjunz:return-transition-patcher:+'
```

- You can pick a patch method as needed. 你可以根据需要选择修复方式：

```kotlin
// Call this to patch all activities. 此方法可以为所有Activity打补丁。
ReturnTransitionPatcher.patchAll(application)
// Call this to patch a specific activty. 此方法可以为特定的Activity打补丁。
ReturnTransitionPatcher.patchActivity(activity)
```

> **Note**: either patch method has to be called before `Activity.onStop()` . 
>
> **注**：任一修复方法都应该在`Activity.onStop()`之前调用。

