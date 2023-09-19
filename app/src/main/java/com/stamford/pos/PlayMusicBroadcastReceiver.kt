package com.stamford.pos

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class PlayMusicBroadcastReceiver: BroadcastReceiver() {
    private var TAG = "PlayMusicBroadcastReceiver"

    override fun onReceive(context: Context, intent: Intent) {
        StringBuilder().apply {
            append("Action: ${intent.action}\n")
            append("URI: ${intent.toUri(Intent.URI_INTENT_SCHEME)}\n")
            toString().also {
                log ->
                Log.d(TAG, log)
                Toast.makeText(context,log, Toast.LENGTH_LONG).show()
            }
            if (intent.action == ACTION_POS_PLAY){
                Intent(context, PlayMusicInBGService::class.java).also {
                    intent -> context.startService(intent)
                    Log.i(TAG, "Play music in bg clicked.")
                }
            }
            if (intent.action == ACTION_POS_STOP) {
                Intent(context, PlayMusicInBGService::class.java).also {
                    intent -> context.stopService(intent)
                    Log.i(TAG, "Stop music in bg clicked.")
                }
            }
        }
    }
}