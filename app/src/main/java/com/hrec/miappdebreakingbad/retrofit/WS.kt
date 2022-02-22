package com.hrec.miappdebreakingbad.retrofit

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.hrec.miappdebreakingbad.models.Characters
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WS {
    companion object {
        private val ws by lazy { IWS.create() }

        fun getCharactersList(completiton:(List<Characters>) -> Unit) {
            val call = ws.getCharacterList()
            call.enqueue(object : Callback<JsonArray>{
                override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                    Log.e( "onFailure: ", "$t")
                }

                override fun onResponse(
                    call: Call<JsonArray>,
                    response: Response<JsonArray>
                ) {
                    response.let {
                        if(it.isSuccessful){
                            it.body()?.let { jsonResponse ->
                                val list = arrayListOf<Characters>()
                                jsonResponse.forEach { it1 ->
                                    val array = Gson().fromJson(it1, JsonObject::class.java)
                                    val obj = Gson().fromJson(array, Characters::class.java)
                                    list.add(obj)
                                }
                                completiton(list)
                            }
                        }
                    }
                }
            })
        }
    }
}