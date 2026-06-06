package com.example.cursosant.common.utils

import android.app.Application
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.widget.Toast
import androidx.core.net.toUri
import com.example.cursosant.R


class IntentUtils(private val context: Application) {
    fun showMap(lat: Double, lon: Double, label: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.apply {
            data = "geo:0.0?q=$lat,$lon($label)".toUri()
            `package`= "com.google.android.apps.maps"
        }
        startIntent(intent)
    }

    private fun startIntent(intent: Intent){
        val chooser: Intent = Intent.createChooser(intent,
            context.getString(R.string.title_intent_chooser))
        //add tag to necessary to execute startIntent out of ActivityMain
        chooser.addFlags(FLAG_ACTIVITY_NEW_TASK)
        if(chooser.resolveActivity(context.packageManager) != null){
            context.startActivity(chooser)
        }else{
            Toast.makeText(context,
                context.getString(R.string.msg_not_app_relation_intent),
                Toast.LENGTH_SHORT)
                .show()
        }
    }
}