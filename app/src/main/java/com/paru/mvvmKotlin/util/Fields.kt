package com.paru.mvvmKotlin.util

class Fields {
    companion object {
        var USERNAME: String = "un"
        var PASSWORD: String = "p"
        var VERSION = "version"

        //header fields

        var CONTENT_TYPE: String = "Content-Type"
        var ACCEPT: String = "Accept"
        var VERSION_CODE: String = "versionCode"
        var AUTHORIZATION: String = "Authorization"

        //response success code
        val STATUS_SUCCESS = 0
        val STATUS_FAILED = 1
    }
}
