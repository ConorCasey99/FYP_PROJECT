package ie.conorcasey.sharido.ui.communities


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ie.conorcasey.sharido.R

class Communities : Fragment() {

  private lateinit var communityViewModel: CommunityViewModel

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    communityViewModel =
        ViewModelProvider(this).get(CommunityViewModel::class.java)
    val root = inflater.inflate(R.layout.fragment_communities, container, false)
    //val textView: TextView = root.findViewById(R.id.titleText)
    // slideshowViewModel.text.observe(viewLifecycleOwner, Observer {
    //  textView.text = it
    //})
    return root
  }
}