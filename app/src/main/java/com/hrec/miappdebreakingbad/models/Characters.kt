package com.hrec.miappdebreakingbad.models

import com.google.gson.annotations.SerializedName

class ListOfCharactersModel {
    @SerializedName("")
    var list: List<Characters>? = null
}

class Characters {
    @SerializedName("char_id")
    var charid: Int? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("birthday")
    var birthday: String? = null
    @SerializedName("occupation")
    var occupation: Array<String>? = null
    @SerializedName("img")
    var img: String? = null
    @SerializedName("status")
    var status: String? = null
    @SerializedName("nickname")
    var nickname: String? = null
    @SerializedName("appearance")
    var appearance: Array<Int>? = null
    @SerializedName("portrayed")
    var portrayed: String? = null
    @SerializedName("category")
    var category: String? = null
    @SerializedName("better_call_saul_appearance")
    var bettercallsaulappearance: Array<Int>? = null
}