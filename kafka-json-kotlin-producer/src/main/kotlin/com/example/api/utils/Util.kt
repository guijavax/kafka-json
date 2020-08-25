package com.example.api.utils

class Util {
    companion object {
        fun removeSpecialCaracterFromString(obj : String) = obj.replace("[^a-zA-Z0-9]", "")
    }
}