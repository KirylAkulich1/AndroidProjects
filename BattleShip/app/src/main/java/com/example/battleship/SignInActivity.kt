package com.example.battleship

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.battleship.ui.login.LoginFragment
import com.google.firebase.auth.FirebaseAuth


class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_sign_in)
        val ft=supportFragmentManager.beginTransaction()
        val fragment=LoginFragment()
        ft.add(R.id.login_fragment,fragment)
        ft.commit()
    }
    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build()
        )
        var actionCodeSettings = ActionCodeSettings.newBuilder()
            .setAndroidPackageName(/* yourPackageName= */ packageName, /* installIfNotAvailable= */ true,
        /* minimumVersion= */ null)
        .setHandleCodeInApp(true) // This must be set to true
            .setUrl("https://google.com") // This URL needs to be whitelisted
            .build();
      /*  startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(listOf(uthUI.IdpConfig.EmailBuilder().enableEmailLinkSignIn()
                    .setActionCodeSettings(actionCodeSettings).build()))
                .build(),
            RC_SIGN_IN)

       */
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                val user = FirebaseAuth.getInstance().currentUser
                // ...
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }
    companion object {

        private const val RC_SIGN_IN = 123
    }*/


}