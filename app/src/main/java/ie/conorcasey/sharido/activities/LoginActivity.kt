package ie.conorcasey.sharido.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthMethodPickerLayout
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import ie.conorcasey.sharido.R
import ie.conorcasey.sharido.main.MainApp
import org.jetbrains.anko.startActivity

//Login functionality based on code from mobile application development 2 module
class LoginActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var app: MainApp

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as MainApp

        checkSignedIn()
    }

    private fun checkSignedIn() {                                         //function adapted from code from Mobile Application Development 2 module labs
        val auth = FirebaseAuth.getInstance()                             //function adapted from code from Mobile Application Development 2 module labs
        if (auth.currentUser != null) {                                   //function adapted from code from Mobile Application Development 2 module labs
            // already signed in
            app.currentUser = FirebaseAuth.getInstance().currentUser!!    //function adapted from code from Mobile Application Development 2 module labs
            app.database = FirebaseDatabase.getInstance().reference       //function adapted from code from Mobile Application Development 2 module labs
            app.storage = FirebaseStorage.getInstance().reference         //function adapted from code from Mobile Application Development 2 module labs
            startActivity<MainActivity>()                                 //function adapted from code from Mobile Application Development 2 module labs
        }
        else
        // if not signed in
            createSignInIntent()                                           //function adapted from code from Mobile Application Development 2 module labs
    }

    private fun createSignInIntent() {
        // [START auth_fui_create_intent]
        // Choose authentication providers
        val providers = arrayListOf(                                      //function adapted from code from Mobile Application Development 2 module labs
            AuthUI.IdpConfig.EmailBuilder().build(),                      //function adapted from code from Mobile Application Development 2 module labs
            AuthUI.IdpConfig.PhoneBuilder().build(),                      //function adapted from code from Mobile Application Development 2 module labs
            AuthUI.IdpConfig.GoogleBuilder().build())                     //function adapted from code from Mobile Application Development 2 module labs

        val customLayout = AuthMethodPickerLayout                          //function adapted from code from Mobile Application Development 2 module labs
            .Builder(R.layout.login)                                       //function adapted from code from Mobile Application Development 2 module labs
            .setGoogleButtonId(R.id.googleSignInButton)                    //function adapted from code from Mobile Application Development 2 module labs
            .setEmailButtonId(R.id.emailSignInButton)                      //function adapted from code from Mobile Application Development 2 module labs
            .setPhoneButtonId(R.id.phoneSignInButton)                      //function adapted from code from Mobile Application Development 2 module labs
            .build()                                                       //function adapted from code from Mobile Application Development 2 module labs

        // Create and launch sign-in intent
        startActivityForResult(                                            //function adapted from code from Mobile Application Development 2 module labs
            AuthUI.getInstance()                                           //function adapted from code from Mobile Application Development 2 module labs
                .createSignInIntentBuilder()                               //function adapted from code from Mobile Application Development 2 module labs
                .setAvailableProviders(providers)                          //function adapted from code from Mobile Application Development 2 module labs
                .setIsSmartLockEnabled(false,true) // true,true for Smart Lock
                .setTheme(R.style.FirebaseLoginTheme)                      //function adapted from code from Mobile Application Development 2 module labs
                .setAuthMethodPickerLayout(customLayout)                   //function adapted from code from Mobile Application Development 2 module labs
                .build(),                                                  //function adapted from code from Mobile Application Development 2 module labs
            123)
        // [END auth_fui_create_intent]

    }

    // [START auth_fui_result]
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {  //function adapted from code from Mobile Application Development 2 module labs
        super.onActivityResult(requestCode, resultCode, data)                          //function adapted from code from Mobile Application Development 2 module labs

        if (requestCode == 123) {                                                      //function adapted from code from Mobile Application Development 2 module labs
            val response = IdpResponse.fromResultIntent(data)                          //function adapted from code from Mobile Application Development 2 module labs

            if (resultCode == Activity.RESULT_OK) {                                    //function adapted from code from Mobile Application Development 2 module labs
                // Successfully signed in
                app.currentUser = FirebaseAuth.getInstance().currentUser!!             //function adapted from code from Mobile Application Development 2 module labs
                app.database = FirebaseDatabase.getInstance().reference                //function adapted from code from Mobile Application Development 2 module labs
                app.storage = FirebaseStorage.getInstance().reference                  //function adapted from code from Mobile Application Development 2 module labs

                startActivity<MainActivity>()                                          //function adapted from code from Mobile Application Development 2 module labs

            } else {
                startActivity<LoginActivity>()                                         //function adapted from code from Mobile Application Development 2 module labs
            }
        }
    }

    override fun onClick(v: View) { createSignInIntent() }//function adapted from code from Mobile Application Development 2 module labs
}