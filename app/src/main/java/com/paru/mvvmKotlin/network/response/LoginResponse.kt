package com.paru.mvvmKotlin.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import lombok.AccessLevel
import lombok.Data
import lombok.Getter
import lombok.Setter


@Data
class LoginResponse {

    @SerializedName("status") @Expose
    internal var status: Int? = null

    @SerializedName("id") @Expose
    internal var id: String? = null

}