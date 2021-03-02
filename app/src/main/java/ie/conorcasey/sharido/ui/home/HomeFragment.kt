package ie.conorcasey.sharido.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ie.conorcasey.sharido.R
import ie.conorcasey.sharido.main.MainApp

class HomeFragment : Fragment() {

  lateinit var app: MainApp

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    val root = inflater.inflate(R.layout.fragment_home, container, false)
    var homeName: TextView = root.findViewById(R.id.homeTitleText)
      return root
  }
}