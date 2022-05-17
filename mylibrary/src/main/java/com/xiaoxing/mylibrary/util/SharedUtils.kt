package com.yolanda.jsbridgelib.util

import android.content.Context
import android.content.SharedPreferences

/**

 *@Description: 本地存储

 *@Author: qushaohua

 *@CreateDate: 2022/4/25 5:22 下午

 */
object SharedUtils {
    const val FILE_NAME = "JsLibrary"
    lateinit var sp: SharedPreferences

    fun init(context: Context){
        sp = context.applicationContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    }
    /**
     * 添加boolean值
     */
    fun putBoolean(key: String,value: Boolean){
        sp.edit().putBoolean(key,value).apply()
    }
    /**
     * 获取boolean值
     */
    fun getBoolean(key: String,default: Boolean): Boolean{
        return sp.getBoolean(key,default)
    }

    /**
     * 添加String值
     */
    fun putString(key: String){
        sp.edit().putString(key,"").apply()
    }
    /**
     * 获取boolean值
     */
    fun getString(key: String,default: String = ""): String{
        return sp.getString(key,default)?:""
    }

    /**
     * 判断是否存在
     */
    fun contains(key: String): Boolean{
        return sp.contains(key)
    }
}