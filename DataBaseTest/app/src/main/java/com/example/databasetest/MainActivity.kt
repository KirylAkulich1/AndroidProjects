package com.example.databasetest

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import com.example.databasetest.pref.MyPreferences
import com.example.databasetest.viewModels.MainViewModel
import com.example.databasetest.viewModels.MainViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    var FRAGMENT_TAG="ActivityList"
    var MAIN_FRAGMENT_TAG="MAIN_ACTIVITY_LIST"
    var splashDialog:Dialog?=null
    var activityChooserDialog:Dialog?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this,MainViewModelFactory(application)).get(MainViewModel::class.java)
       /* val ft=supportFragmentManager.beginTransaction()
        val fragment=MainFragment.newInstance()
        ft.add(R.id.main_activities,fragment,MAIN_FRAGMENT_TAG)
        ft.commit()
        showSplashScreen()
        mainViewModel = ViewModelProviders.of(this,MainViewModelFactory(application)).get(MainViewModel::class.java)
        GlobalScope.launch(mainViewModel.dispatcher) {
            mainViewModel.initActivity().join()
            delay(1000L)
            runOnUiThread{
                removeSplash()

            }
        }
        mainViewModel.subscibe(fragment)
        fragment.setCRUDListner(mainViewModel)
*/

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
    fun onAddActivityClicked(v: View){
        activityChooserDialog= Dialog(this)
        activityChooserDialog!!.setContentView(R.layout.activity_choose_dialog)
        val fragment=ActivityFragment.newInstance(mainViewModel)
        fragment.show(supportFragmentManager,"Act")
        //val listFragment=supportFragmentManager.findFragmentByTag(FRAGMENT_TAG)
        //val fragmenttransaction=supportFragmentManager.beginTransaction()
        /*if(listFragment==null)
        {
            fragmenttransaction.add(R.id.add_activity_list,ActivityFragment.newInstance(mainViewModel),FRAGMENT_TAG)
            fragmenttransaction.commit()
        }
        activityChooserDialog!!.setOnCancelListener{
            //fragmenttransaction.remove(listFragment!!)
            //fragmenttransaction.commit()
        }
        activityChooserDialog!!.show()

         */
    }
    fun onStartclicked(v:View){
        val intent=Intent(this,TimerActivity::class.java)
        intent.putExtra("exeId",mainViewModel.exersice!!.id)
        startActivity(intent)
    }
    fun onAddExerciseClicked(v:View){
        val intent=Intent(this,NewExerciseActivity::class.java)
        startActivity(intent)
    }
    fun onAllExersiceClicked(v:View){
        val intent=Intent(this,ExersiceActivity::class.java)
        startActivityForResult(intent,1)
    }
    fun onSettingsButtonClicked(v:View){
        val intent=Intent(this,SettingsActivity::class.java)
        startActivityForResult(intent,1)

    }
    private fun checkTheme() {
        when (MyPreferences(this).darkMode) {
            0 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                delegate.applyDayNight()
            }
            1 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                delegate.applyDayNight()
            }
            2 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                delegate.applyDayNight()
            }
        }
    }
    fun showSplashScreen(){
        splashDialog= Dialog(this)
        splashDialog!!.setContentView(R.layout.splash_layout)
        splashDialog!!.setCancelable(false)
        splashDialog!!.show();
        Log.e("Dial","DialogLoaded")
    }
    fun removeSplash(){
        splashDialog!!.dismiss()
        splashDialog=null
        Log.e("Splash","Dismiss")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data==null){return}
        val currActivity=data.getIntExtra("exeId",1)
        Toast.makeText(this,currActivity.toString(),Toast.LENGTH_SHORT).show()
        mainViewModel.renewExercise(currActivity)
        val ft=supportFragmentManager.beginTransaction()
        var fragment=supportFragmentManager.findFragmentByTag(MAIN_FRAGMENT_TAG)
        if(fragment!=null)
            ft.remove(fragment)
        fragment=MainFragment.newInstance()
        ft.add(R.id.main_activities,fragment,MAIN_FRAGMENT_TAG)
        ft.commit()
        mainViewModel.subscibe(fragment)
        fragment.setCRUDListner(mainViewModel)
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onResume() {
        super.onResume()
        mainViewModel.checkactivity()
        val ft=supportFragmentManager.beginTransaction()
        var fragment=supportFragmentManager.findFragmentByTag( MAIN_FRAGMENT_TAG)
        if(fragment!=null)
            ft.remove(fragment)
        fragment=MainFragment.newInstance()
        ft.add(R.id.main_activities,fragment,MAIN_FRAGMENT_TAG)
        ft.commit()
        //showSplashScreen()
        mainViewModel.subscibe(fragment)
        fragment.setCRUDListner(mainViewModel)
        findViewById<Button>(R.id.btn2).invalidate()

    }
}