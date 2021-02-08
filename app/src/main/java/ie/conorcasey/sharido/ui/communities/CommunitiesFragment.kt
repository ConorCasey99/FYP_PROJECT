package ie.conorcasey.sharido.ui.communities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ie.conorcasey.sharido.R

class CommunitiesFragment : Fragment() {

  private lateinit var communitiesViewModel: CommunitiesViewModel

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    communitiesViewModel =
        ViewModelProvider(this).get(CommunitiesViewModel::class.java)
    val root = inflater.inflate(R.layout.fragment_gallery, container, false)
    val textView: TextView = root.findViewById(R.id.text_gallery)
    communitiesViewModel.text.observe(viewLifecycleOwner, Observer {
      textView.text = it
    })
    return root
  }
}