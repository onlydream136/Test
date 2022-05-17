package com.yolanda.jsbridgelib.util

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import java.util.*


/**

 *@Description: 系统方法

 *@Author: qushaohua

 *@CreateDate: 2022/3/28 11:50 上午

 */
object SystemUtils {
    /**
     * 获取状态栏高度
     * @return Int 单位为px
     */
    fun getStatusBarHeight(activity: Context): Int {
        var result = 0
        //获取状态栏高度的资源id
        val resourceId: Int = activity.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = activity.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    /**
     * 拨打电话（跳转到拨打电话界面）
     */
    fun callPhone(activity: Activity,phoneNum: String){
        val intent = Intent(Intent.ACTION_DIAL)
        val data = Uri.parse("tel:$phoneNum")
        intent.setData(data)
        activity.startActivity(intent)
    }

    /**
     * 复制
     */
    fun copy(context: Context, content: String){
        //获取剪贴板管理器：
        val cm = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        // 创建普通字符型ClipData
        val mClipData = ClipData.newPlainText("Label", content)
        //将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData)
    }

    /**
     * 复制
     */
    fun paste(context: Context): String{
        //获取剪贴板管理器：
        val cm = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        //获取剪贴板数据
        val mClipData = cm.primaryClip
        if (mClipData != null && mClipData.itemCount > 0){
            //从数据集中获取第一条文本数据
            return mClipData.getItemAt(0).text.toString()
        }
        return ""
    }

    /**
     * 获取系统语言
     */
    fun getLanguage(): String{
        val systemLocale = Locale.getDefault()
        val language = systemLocale.getLanguage()
        return language
    }

    /**
     * 获取app包名
     */
    fun getAppPackage(context: Context): String{
        val manage = context.packageManager
        try {
            val info = manage.getPackageInfo(context.packageName,0)
            return info.packageName
        }catch (e:Exception){
            return ""
        }
    }
}