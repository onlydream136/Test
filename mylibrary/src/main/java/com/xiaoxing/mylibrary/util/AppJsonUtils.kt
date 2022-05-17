package com.yolanda.jsbridgelib.util

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken

/**
 *@Description: 获取app预加载配置
 *
 *@Author: qushaohua
 *
 *@CreateDate: 2022/3/21 4:24 下午
 *
 */
object AppJsonUtils {

    /**
     * 获取app预加载配置
     * @return
     */
    fun getAppJson(json: String): HashMap<String, String> {
        val data = HashMap<String,String>()
        val jsonElement = JsonParser.parseString(json)
        if (!jsonElement.isJsonObject) {
            return data
        }
        val jsonObject = jsonElement.asJsonObject
        if (!jsonObject.has("page") || !jsonObject.has("preloadingPage")) {
            return data
        }
        val pagesObject = jsonObject.getAsJsonObject("page")
        val preloadingPagesArray = jsonObject.getAsJsonArray("preloadingPage")
        val keys: List<String> = Gson().fromJson(
            preloadingPagesArray.toString(),
            object : TypeToken<List<String?>?>() {}.type
        )
        if (keys.isEmpty()){
            return data
        }
        keys.forEach{
            if (pagesObject.has(it)){
                data[it] = pagesObject.get(it).asString
            }
        }
        return data
    }

}

