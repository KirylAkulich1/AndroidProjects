package com.example.databasetest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.RingtoneManager
import android.net.Uri
import android.os.Binder
import android.os.Build
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.databasetest.room.ExeActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


class TimerService: Service() {
    var mTimer: CountDownTimer?=null
    lateinit var layoutChanger:TimerActivity.LayOutChanger
    lateinit var currActivity:ExeActivity

    private val ACTION_STRING_SERVICE = "ToService"
    private val ACTION_STRING_ACTIVITY = "ToActivity"

    val serviceReceiver:BroadcastReceiver=object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
          //  Toast.makeText(getApplicationContext(), "received message in service..!", Toast.LENGTH_SHORT).show();
            Log.d("Service", "Sending broadcast to activity");
            val action=intent.getIntExtra("action",0)
            val rep=intent.getIntExtra("rep",0)
            val duration=intent.getIntExtra("duration",0)
            Log.e("service","| "+action.toString())
            when(action) {
               1-> {
                   mTimer = object : CountDownTimer((rep*duration* 1000).toLong(), (duration*1000).toLong()) {
                       override fun onFinish() {
                           val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                           val r = RingtoneManager.getRingtone(applicationContext, notification)
                           //layoutChanger.onNext()
                           r.play()
                           val mintent = Intent()
                           mintent.putExtra("action", 1)
                           mSendBroadcast(mintent)
                       }

                       override fun onTick(p0: Long) {
                           if(p0==0L)
                               return

                        //   val mIntent=Intent()
                         //  mIntent.putExtra("action",2)
                          // mSendBroadcast(mIntent)
                           Log.e("tick","OnTick")
                       }

                   }.start()
               }
                2->{
                    if(mTimer!=null) {
                        mTimer?.cancel()
                        mTimer=null
                    }
                }
                3->{
                    val rest=intent.getIntExtra("rest",0)
                    Log.e("service",rest.toString()+"| "+action.toString())
                    mTimer = object : CountDownTimer((rest *1000).toLong(), (rest*1000).toLong()) {
                        override fun onFinish() {
                            val mintent = Intent()
                            mintent.putExtra("action", 3)
                            mSendBroadcast(mintent)
                        }
                        override fun onTick(p0: Long) {
                        }

                    }.start()
                }
            }

        }
    }
    companion object {
        const val NOTIFICAITON_ID = 0
    }
    override fun onBind(p0: Intent?): IBinder? {
        displayNotification()
        if(p0==null)
            displayNotification()
        else {

        }
        return TimerServiceBinder()
    }

    override fun onCreate() {
        super.onCreate()
        Log.e("tag","ServiceCreated")
        if (serviceReceiver != null) {
            val intentFilter = IntentFilter(ACTION_STRING_SERVICE)
            registerReceiver(serviceReceiver, intentFilter)
        }
    }
    private fun displayNotification(){
        val remoteView=RemoteViews(packageName,R.layout.notification_layout)
        val manager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationBuilder=NotificationCompat.Builder(this,"Default")
        notificationBuilder.setContent(remoteView)
        notificationBuilder.setSmallIcon(R.drawable.ic_baseline_accessibility_new_24)
        if (Build.VERSION.SDK_INT > 26) {
            manager.createNotificationChannel(NotificationChannel("ID", "Main", NotificationManager.IMPORTANCE_DEFAULT))
            notificationBuilder.setChannelId("ID")
        }
        val notification = notificationBuilder.build()
        startForeground(0, notification)
        manager.notify(NOTIFICAITON_ID, notification)

    }
    private fun mSendBroadcast(intent:Intent) {
        intent.action = ACTION_STRING_ACTIVITY
        sendBroadcast(intent)
    }

    inner class TimerServiceBinder:Binder(){

       fun getCountDownTiner()=mTimer
        fun setLayoutChanger(changer:TimerActivity.LayOutChanger){
            layoutChanger=changer
        }
        fun setTimer(timer:CountDownTimer){
            mTimer=timer
        }
    }

}