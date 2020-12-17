mumpackage com.example.battleship

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var mAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth=FirebaseAuth.getInstance()
        val ft=supportFragmentManager.beginTransaction()
        val fragment1=BattleFieldFragment.newInstance()
        ft.add(R.id.enemy_field,fragment1)
        ft.commit()

        val ft1=supportFragmentManager.beginTransaction()
        val fragment2=BattleFieldFragment.newInstance()
        ft1.add(R.id.my_field,fragment2)
        ft1.commit()
    }
    fun onLoginClicked(v: View){
        val intent=Intent(this,SignInActivity::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()

    }
}