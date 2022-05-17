package com.yolanda.jsbridgelib.util

import android.app.Activity
import java.util.*
import kotlin.system.exitProcess

object AppManger {
    private val activityStack: Stack<Activity> = Stack()

    /**
     * 获取个数
     */
    fun getSize(): Int{
        return activityStack.size
    }

    /**
     * 添加Activity到堆栈
     */
    fun addActivity(activity: Activity?) {
        activityStack.add(activity)
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    fun currentActivity(): Activity? {
        return activityStack.lastElement()
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    fun finishActivity() {
        val activity = activityStack.lastElement()
        finishActivity(activity)
    }

    /**
     * 结束指定的Activity
     */
    fun finishActivity(act: Activity?) {
        if (act == null){
            return
        }
        val iterator = activityStack.iterator()
        while (iterator.hasNext()) {
            val activitySave = iterator.next()
            if (act == activitySave) {
                iterator.remove()
                if (!act.isFinishing) {
                    act.finish()
                }
            }
        }
    }

    /**
     * 获取指定activity
     */
    fun getActivity(cls: Class<*>): Activity? {
        val iterator: Iterator<Activity> = activityStack.iterator()
        while (iterator.hasNext()) {
            val activitySave = iterator.next()
            if (cls == activitySave.javaClass) {
                return activitySave
            }
        }
        return null
    }

    /**
     * 获取指定下标activity
     */
    fun getActivity(index : Int): Activity? {
        if (index >= activityStack.size){
            return null
        }
        return activityStack[index]
    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivity() {
        var i = 0
        val size = activityStack.size
        while (i < size) {
            if (null != activityStack[i]) {
                activityStack[i].finish()
            }
            i++
        }
        activityStack.clear()
    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivityTop() {
        var i = 0
        val size = activityStack.size
        while (i < size) {
            if (null != activityStack[i]) {
                activityStack[i].finish()
            }
            i++
        }
        activityStack.clear()
    }

    /**
     * 退出应用程序
     */
    fun appExit() {
        finishAllActivity()
        exitProcess(0)
    }
}