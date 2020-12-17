package com.example.databasetest

import android.content.*
import android.os.Bundle
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.databasetest.viewModels.TimerActivityViewModel
import com.example.databasetest.viewModels.TimerViewModelFactory
import java.util.zip.InflaterOutputStream


class TimerActivity : AppCompatActivity() {
    private lateinit var timerViewModel:TimerActivityViewModel
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var  timeService:TimerService.TimerServiceBinder
    private val INFO_TAG="INFO_TAG"
    private lateinit var tickListner: onTimertickListner
    private val ACTION_STRING_SERVICE = "ToService"
    private val ACTION_STRING_ACTIVITY = "ToActivity"
    private var isStarted=false
    //STEP1: Create a broadcast receiver
    private val activityReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
       //     Toast.makeText(getApplicationContext(), "received message in activity..!", Toast.LENGTH_SHORT).show();
            val action=intent.getIntExtra("action",0)
            Log.e("act",action.toString())
            when(action){
                0->Log.e("","")
                1->{
                    Log.e("case1",action.toString())
                    val mintent=Intent()
                    mintent.putExtra("action",3)
                    mintent.putExtra("rest",timerViewModel.getCurrentActivity().rest)
                    mSendBroadcast(mintent)
                }
                2-> tickListner.onTick(timerViewModel.currnetActivity)
                3->{
                    tickListner.onFinish( timerViewModel.currnetActivity)
                    timerViewModel.onNext()
                    if(timerViewModel.canNext()) {
                        while(timerViewModel.canNext() && timerViewModel.getCurrentActivity().repitations==0)
                            timerViewModel.onNext()
                        if(!timerViewModel.canNext())
                            return
                        //tickListner.onFinish(timerViewModel.currnetActivity)
                        tickListner.onBegin(timerViewModel.currnetActivity)


                    }
                    val mintent=Intent()
                    mintent.putExtra("action",1)
                    mintent.putExtra("rep",timerViewModel.getCurrentActivity().repitations)
                    mintent.putExtra("duration",timerViewModel.getCurrentActivity().duration)
                    mSendBroadcast(mintent)
                }
            }
        }
    }
    private val connection=object:ServiceConnection{
        override fun onServiceDisconnected(p0: ComponentName?) {

        }

        override fun onServiceConnected(p0: ComponentName, service: IBinder) {

            if(service is TimerService.TimerServiceBinder)
            {
                service.setLayoutChanger(LayOutChanger())
                timeService=service
            }
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)
        val intent=getIntent()
        timerViewModel=ViewModelProviders.of(this,TimerViewModelFactory(application,intent.getIntExtra("exeId",1))).get(TimerActivityViewModel::class.java)
        val ft=supportFragmentManager.beginTransaction()
        val fragment=ActivityInfoFragment.newInstance(timerViewModel)
        ft.add(R.id.info_activity_list,fragment,INFO_TAG)
        ft.commit()
        tickListner=fragment
        bindService(Intent(this,TimerService::class.java),connection, Context.BIND_AUTO_CREATE)
        if (activityReceiver != null) {
            val intentFilter = IntentFilter(ACTION_STRING_ACTIVITY)
//Map the intent filter to the receiver
            //Map the intent filter to the receiver
            registerReceiver(activityReceiver, intentFilter)
        }
    }
    fun onStartTimerClicked(v: View){
        if(!isStarted)
        {val intent=Intent()
        intent.putExtra("action",1)
        intent.putExtra("rep",timerViewModel.getCurrentActivity().repitations)
        intent.putExtra("duration",timerViewModel.getCurrentActivity().duration)
        tickListner.onBegin(timerViewModel.currnetActivity)
        mSendBroadcast(intent)
            isStarted=true
        }

    }
    fun onStopClicked(v:View){
        val intent=Intent()
        intent.putExtra("action",2)
        mSendBroadcast(intent)
        isStarted=false
    }
    fun onResetClicked(v:View){
        timerViewModel.onReset()
        val intent=Intent()
        intent.putExtra("action",2)
        mSendBroadcast(intent)
        val ft=supportFragmentManager.beginTransaction()
        var fragment=supportFragmentManager.findFragmentByTag(INFO_TAG)//ActivityInfoFragment.newInstance(timerViewModel)
        if(fragment!=null)
            ft.remove(fragment)
        fragment=ActivityInfoFragment.newInstance(timerViewModel)
        ft.add(R.id.info_activity_list,fragment,INFO_TAG)
        ft.commit()
    }
    private fun mSendBroadcast(intent:Intent) {
        intent.action = ACTION_STRING_SERVICE
        sendBroadcast(intent)
    }
    fun onPrevActivityClicked(v:View){
        val intent=Intent()
        intent.putExtra("action",2)
        mSendBroadcast(intent)
        tickListner.onSkip(timerViewModel.currnetActivity)
        do{
            timerViewModel.onPrev()
        }
        while(timerViewModel.currnetActivity!=-1 && timerViewModel.getCurrentActivity().repitations==0)

        if(timerViewModel.currnetActivity==-1 || timerViewModel.getCurrentActivity().repitations==0) {
            timerViewModel.currnetActivity=0
            return
        }
        intent.putExtra("action",1)
        intent.putExtra("rep",timerViewModel.getCurrentActivity().repitations)
        intent.putExtra("duration",timerViewModel.getCurrentActivity().duration)
        tickListner.onBegin(timerViewModel.currnetActivity)
        mSendBroadcast(intent)
    }
    fun onNextActivityClicked(v:View){
        val intent=Intent()
        intent.putExtra("action",2)
        mSendBroadcast(intent)
        tickListner.onSkip(timerViewModel.currnetActivity)
        do{
            timerViewModel.onNext()
        }
        while(timerViewModel.canNext() && timerViewModel.getCurrentActivity().repitations==0)

        if(!timerViewModel.canNext() ||timerViewModel.getCurrentActivity().repitations==0) {
            timerViewModel.currnetActivity=0
            return
        }
        intent.putExtra("action",1)
        intent.putExtra("rep",timerViewModel.getCurrentActivity().repitations)
        intent.putExtra("duration",timerViewModel.getCurrentActivity().duration)
        tickListner.onBegin(timerViewModel.currnetActivity)
        mSendBroadcast(intent)
    }
    inner class LayOutChanger{
        fun onNext(){
            if(timerViewModel.canNext())
                {
                    val intent=Intent(this@TimerActivity,TimerService::class.java)
                    tickListner.onFinish(timerViewModel.currnetActivity)
                    timerViewModel.onNext()
                    tickListner.onBegin(timerViewModel.currnetActivity)
                    intent.putExtra("action",1)
                    intent.putExtra("rep",timerViewModel.getCurrentActivity().repitations)
                    intent.putExtra("duration",timerViewModel.getCurrentActivity().duration)


                }

        }
    }
    interface onTimertickListner{
        fun onTick(pos:Int)
        fun onFinish(pos: Int)
        fun onBegin(pos:Int)
        fun onNext(pos:Int)
        fun onSkip(pos:Int)
    }
}