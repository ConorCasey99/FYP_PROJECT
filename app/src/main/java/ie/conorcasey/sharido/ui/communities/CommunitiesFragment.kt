package ie.conorcasey.sharido.ui.communities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ie.conorcasey.sharido.R
import ie.conorcasey.sharido.main.MainApp
import kotlinx.android.synthetic.main.fragment_communities.view.*
import org.jetbrains.anko.AnkoLogger

class CommunitiesFragment : Fragment(), AnkoLogger{

    lateinit var app: MainApp
    lateinit var loader : AlertDialog
    lateinit var root: View


  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    val root = inflater.inflate(R.layout.fragment_communities, container, false)
    //val textView: TextView = root.findViewById(R.id.titleText)
    // slideshowViewModel.text.observe(viewLifecycleOwner, Observer {
    //  textView.text = it
    //})
//    root.recyclerView.setLayoutManager(LinearLayoutManager(activity))
   // setSwipeRefresh()
    root.communitiesRecyclerView.setLayoutManager(LinearLayoutManager(activity))
    return root
  }
  }








