package ie.conorcasey.sharido.activities

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.firebase.ui.auth.AuthUI
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import ie.conorcasey.sharido.R
import ie.conorcasey.sharido.main.MainApp
import kotlinx.android.synthetic.main.nav_header_main.view.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

  val button: Button = findViewById<Button>(R.id.sign_out) as Button


  private lateinit var appBarConfiguration: AppBarConfiguration
  private lateinit var Auth: FirebaseAuth
  lateinit var app: MainApp
  val signout = R.id.sign_out

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val toolbar: Toolbar = findViewById(R.id.toolbar)
    setSupportActionBar(toolbar)
    app = application as MainApp

/*
    val fab: FloatingActionButton = findViewById(R.id.fab)
    fab.setOnClickListener { view ->
      Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          .setAction("Action", null).show()
    }*/
    val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
    val navView: NavigationView = findViewById(R.id.nav_view)
    val navController = findNavController(R.id.nav_host_fragment)
    //val nav_header = findViewById(R.id.nav_header)
    // Passing each menu ID as a set of Ids because each
    // menu should be considered as top level destinations.
    appBarConfiguration = AppBarConfiguration(setOf(
        R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
    setupActionBarWithNavController(navController, appBarConfiguration)
    navView.setupWithNavController(navController)
    navView.getHeaderView(0).nav_header.text = app.currentUser?.displayName
    navView.getHeaderView(0).nav_email.text = app.currentUser?.email
    //sign_out.setOnClickListener(this)
  }

  override fun onClick(v: View) {
    when (v.id) {
      R.id.sign_out -> {
        signOut()
      }
      else -> {
        // else condition
      }
    }
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    // Inflate the menu; this adds items to the action bar if it is present.
    menuInflater.inflate(R.menu.main, menu)
    return true
  }



  private fun signOut() {
    AuthUI.getInstance()
      .signOut(this)
      .addOnCompleteListener { startActivity<LoginActivity>() }
    finish()
  }



  override fun onSupportNavigateUp(): Boolean {
    val navController = findNavController(R.id.nav_host_fragment)
    return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
  }
}