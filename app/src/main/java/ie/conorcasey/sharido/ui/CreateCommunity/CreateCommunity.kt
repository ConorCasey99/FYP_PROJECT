package ie.conorcasey.sharido.ui.CreateCommunity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ie.conorcasey.sharido.R

class CreateCommunity : Fragment() {

  private lateinit var createCommunityViewModel: CreateCommunityViewModel

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    createCommunityViewModel =
        ViewModelProvider(this).get(CreateCommunityViewModel::class.java)
    val root = inflater.inflate(R.layout.fragment_create_community, container, false)
    //val textView: TextView = root.findViewById(R.id.titleText)
   // slideshowViewModel.text.observe(viewLifecycleOwner, Observer {
    //  textView.text = it
    //})
    return root
  }
}