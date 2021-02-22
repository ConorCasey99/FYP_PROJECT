package ie.conorcasey.sharido.ui.communities

//import ie.conorcasey.sharido.helpers.createLoader
//import ie.conorcasey.sharido.helpers.hideLoader
//import ie.conorcasey.sharido.helpers.showLoader
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ie.conorcasey.sharido.R
import ie.conorcasey.sharido.main.MainApp
import kotlinx.android.synthetic.main.fragment_users.view.*
import org.jetbrains.anko.AnkoLogger

class CommunitiesFragment : Fragment(), AnkoLogger {

    lateinit var app: MainApp
    lateinit var loader : AlertDialog
    lateinit var root: View

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
    root.recyclerView.setLayoutManager(LinearLayoutManager(activity))
   // setSwipeRefresh()
    return root
  }

  companion object {
    @JvmStatic
    fun newInstance() =
        CommunitiesFragment().apply {
          arguments = Bundle().apply { }
        }
  }

  override fun onResume() {
    super.onResume()
    //getAllCommunities()
  }}
