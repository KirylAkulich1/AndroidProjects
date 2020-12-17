package com.example.databasetest

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.SeekBar
import androidx.lifecycle.ViewModelProviders
import com.example.databasetest.viewModels.MainViewModel
import com.example.databasetest.viewModels.MainViewModelFactory
import com.example.databasetest.viewModels.NewExerciseViewModel
import com.example.databasetest.viewModels.NewExerciseViewModelFactory

class NewExerciseActivity : AppCompatActivity() {
    lateinit var red_seek_bar:SeekBar
    lateinit var blue_seek_bar:SeekBar
    lateinit var green_seek_bar:SeekBar
    lateinit var colorLayout:LinearLayout
    var splashDialog: Dialog?=null
    val FRAGMENT_TAG="NEWEXERCISETAG"
    lateinit var mViewModel:NewExerciseViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mew_exercise)
        mViewModel = ViewModelProviders.of(this, NewExerciseViewModelFactory(application)).get(NewExerciseViewModel::class.java)
        val ft=supportFragmentManager.beginTransaction()
        val fragment=MainFragment.newInstance()
        ft.add(R.id.activities_to_add,fragment)
        ft.commit()
        mViewModel.subscribe(fragment)
        fragment.setCRUDListner(mViewModel)

    }
    fun onSaveClicked(v:View){
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        builder.setTitle("With EditText")
        val dialogLayout = inflater.inflate(R.layout.new_exercise_dialog, null)
        colorLayout=dialogLayout.findViewById(R.id.color_value)
        red_seek_bar=dialogLayout.findViewById(R.id.red_value)
        red_seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                colorLayout.setBackgroundColor(getColor())

            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })
        green_seek_bar=dialogLayout.findViewById(R.id.green_value)
        green_seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                colorLayout.setBackgroundColor(getColor())

            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })
        blue_seek_bar=dialogLayout.findViewById(R.id.blue_value)
        blue_seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                colorLayout.setBackgroundColor(getColor())

            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })
        dialogLayout.findViewById<Button>(R.id.save_exercise_with_color).setOnClickListener{
            mViewModel.Save(dialogLayout.findViewById<EditText>(R.id.exercise_name_edit).text.toString(),getColor())
            finish()
        }
        builder.setView(dialogLayout)
        builder.show()
    }
    fun onAddClicked(v:View){
        val listFragment=supportFragmentManager.findFragmentByTag(FRAGMENT_TAG)
        val fragment=ActivityFragment.newInstance(mViewModel)
        fragment.show(supportFragmentManager,"Act")
       /* val fragmenttransaction=supportFragmentManager.beginTransaction()
        if(listFragment==null)
        {

            fragmenttransaction.add(R.id.add_activity_list_2,ActivityFragment.newInstance(mViewModel),FRAGMENT_TAG)
            fragmenttransaction.commit()
        }
        else{
            fragmenttransaction.remove(listFragment)
            fragmenttransaction.commit()
        }

        */
    }
    fun getColor()=Color.rgb(red_seek_bar.progress,green_seek_bar.progress,blue_seek_bar.progress)
    fun onCancelClicked(v:View){
        finish()
    }
}