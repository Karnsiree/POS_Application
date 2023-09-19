package com.stamford.pos

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.ui.AppBarConfiguration
import com.stamford.pos.databinding.ActivityCommandCenterBinding

class CommandCenterActivity: AppCompatActivity() {
    private val TAG = "CommandCenterActivity"
    private val CHANNEL_ID = "90200"
    private val notificationId = 1
    private val EXTRA_NOTIFICATION_ID = "notif_id"

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityCommandCenterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCommandCenterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.i(TAG, "Activity onCreated.")


//        val newOrderBtn = findViewById<Button>(R.id.new_order_btn)
//        // set on-click listener
//        newOrderBtn.setOnClickListener {
//            Toast.makeText(this@CommandCenterActivity, "New Order", Toast.LENGTH_SHORT).show()
//            //This code here will be executed whenever the login button is clicked
//            val intent = Intent(this, ProductCatActivity::class.java)
//            startActivity(intent)
//        }
//
//        val addProductBtn = findViewById<Button>(R.id.add_product_btn)
//        // set on-click listener
//        addProductBtn.setOnClickListener {
//            Toast.makeText(this@CommandCenterActivity, "Add Product", Toast.LENGTH_SHORT).show()
//            //This code here will be executed whenever the login button is clicked
//            val intent = Intent(this, ProductCRUDActivity::class.java)
//            startActivity(intent)
//        }
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_add_product)
//        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigationUp()
//    }

    fun onclick_new_order_btn (view: View){
        Log.i(TAG, "New Order Btn clicked. Order UI process started.")

        val intent = Intent(this, ProductCatActivity::class.java)

        Log.d(TAG, "Login process successful, going to start the ProductCatActivity")
        startActivity(intent)
    }

    fun onclick_add_product_btn (view: View){
        Log.i(TAG, "Add Product Btn clicked. ProductCRUDActivity process started.")

        val intent = Intent(this, ProductCRUDActivity::class.java)

        Log.d(TAG, "Login process successful, going to start the ProductCRUDActivity")
        startActivity(intent)
    }

    fun onclick_play_music_in_bg_btn (view: View) {
        Log.i(TAG, "Play music in bg clicked.")
        Intent(this@CommandCenterActivity, PlayMusicInBGService::class.java).also {
            intent -> startService(intent)
        }

        // Create an explicit intent for an Activity in your app
        val intent = Intent(this, CommandCenterActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent,0)

        val playIntent = Intent(this, PlayMusicBroadcastReceiver::class.java).apply {
            action = ACTION_POS_PLAY
            putExtra(EXTRA_NOTIFICATION_ID, notificationId)
        }

        val playPendingIntent: PendingIntent = PendingIntent.getBroadcast(this,0,playIntent,0)

        val stopIntent = Intent(this, PlayMusicBroadcastReceiver::class.java).apply {
            action = ACTION_POS_STOP
            putExtra(EXTRA_NOTIFICATION_ID, notificationId)
        }

        val stopPendingIntent: PendingIntent = PendingIntent.getBroadcast(this,0,stopIntent,0)

        // Create a channel
        createNotificationChannel()

        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_bg_music_stat)
            .setContentTitle("Stamford POS BG Music Status")
            .setContentText("Status = playStatus")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setColor(Color.BLUE)
            .setContentIntent(pendingIntent)
            .setAutoCancel(false)
            .addAction(R.drawable.ic_action_play,getString(R.string.play_str), playPendingIntent)
            .addAction(R.drawable.ic_action_stop,getString(R.string.stop_str), stopPendingIntent)
        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification
            // that you must define
            notify(notificationId, builder.build())
        }
    }

    fun onclick_stop_music_in_bg_btn (view: View) {
        Log.i(TAG, "Stop music in bg clicked.")
        Intent(this@CommandCenterActivity, PlayMusicInBGService::class.java).also {
                intent -> stopService(intent)
        }

        // Create an explicit intent for an Activity in your app
        val intent = Intent(this, CommandCenterActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent,0)

        val playIntent = Intent(this, PlayMusicBroadcastReceiver::class.java).apply {
            action = ACTION_POS_PLAY
            putExtra(EXTRA_NOTIFICATION_ID, notificationId)
        }

        val playPendingIntent: PendingIntent = PendingIntent.getBroadcast(this,0,playIntent,0)

        val stopIntent = Intent(this, PlayMusicBroadcastReceiver::class.java).apply {
            action = ACTION_POS_STOP
            putExtra(EXTRA_NOTIFICATION_ID, notificationId)
        }

        val stopPendingIntent: PendingIntent = PendingIntent.getBroadcast(this,0,stopIntent,0)

        // Create a channel
        createNotificationChannel()

        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_bg_music_stat)
            .setContentTitle("Stamford POS BG Music Status")
            .setContentText("Status = Stop playing music")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setColor(Color.BLUE)
            .setContentIntent(pendingIntent)
            .setAutoCancel(false)
            .addAction(R.drawable.ic_action_play,getString(R.string.play_str), playPendingIntent)
            .addAction(R.drawable.ic_action_stop,getString(R.string.stop_str), stopPendingIntent)
        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification
            // that you must define
            notify(notificationId, builder.build())
        }
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Play BG Status Channel"
            val descriptionText = "A notification to show play background music status"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}