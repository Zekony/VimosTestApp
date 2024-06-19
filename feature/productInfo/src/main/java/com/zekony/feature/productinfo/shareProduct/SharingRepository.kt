package com.zekony.feature.productinfo.shareProduct

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK

class SharingRepository {

    fun shareProductInfo(text: String, activityContext: Context) {
        val sendIntent: Intent = Intent.createChooser(Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                text
            )
            type = "text/plain"
            flags = FLAG_ACTIVITY_NEW_TASK
        }, null)
        activityContext.startActivity(sendIntent)
    }
}