package ie.conorcasey.sharido.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ie.conorcasey.sharido.R

class HomeFragment : Fragment() {


  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    val root = inflater.inflate(R.layout.fragment_home, container, false)
    val textView: TextView = root.findViewById(R.id.text_home)
    return root
  }
}