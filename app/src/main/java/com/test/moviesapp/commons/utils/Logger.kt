package com.test.moviesapp.commons.utils

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.crashlytics.crashlytics

class Logger private constructor() {

    companion object {
        private val TAG = Logger::class.java.simpleName

        /**
         * Log to crashlytics, even though if not is an exception itself,
         * this is useful to know about some issues that do no produce exceptions
         */
        fun log(
            message: String,
            throwable: Throwable? = null,
            tag: String
        ) {
            try {
                Log.d(tag, message, throwable)
                throwable?.let {
                    FirebaseCrashlytics.getInstance().recordException(it)
                }
                FirebaseCrashlytics.getInstance().log("$tag: $message")
            } catch (e: Exception) {
                Log.e(TAG, e.localizedMessage, e)
            }
        }

        /**
         * Use to identify all the logs related to a user
         */
        fun setUser(userId: String) {
            if (userId.isEmpty()) return
            Firebase.crashlytics.setUserId(userId)
        }
    }
}
