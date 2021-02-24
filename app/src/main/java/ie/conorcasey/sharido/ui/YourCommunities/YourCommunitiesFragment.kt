package ie.conorcasey.sharido.ui.YourCommunities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import ie.conorcasey.sharido.R
import ie.conorcasey.sharido.activities.CommunityAdapter
import ie.conorcasey.sharido.activities.CommunityListener
import ie.conorcasey.sharido.helpers.createLoader
import ie.conorcasey.sharido.helpers.hideLoader
import ie.conorcasey.sharido.helpers.showLoader
import ie.conorcasey.sharido.main.MainApp
import ie.conorcasey.sharido.models.CommunityModel
import ie.conorcasey.sharido.ui.communities.CommunitiesFragment
import kotlinx.android.synthetic.main.fragment_communities.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


class YourCommunitiesFragment : Fragment(), AnkoLogger, CommunityListener {

  lateinit var app: MainApp
  lateinit var loader : AlertDialog
  lateinit var root: View

  private lateinit var yourCommunitiesViewModel: YourCommunitiesViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    app = activity?.application as MainApp
  }

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
     root.recyclerView.setLayoutManager(LinearLayoutManager(activity))
    // setSwipeRefresh()
    //root.recyclerView.setLayoutManager(LinearLayoutManager(activity))
    return root
  }

  open fun setSwipeRefresh() {
    root.swiperefresh.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
      override fun onRefresh() {
        root.swiperefresh.isRefreshing = true
        getAllUserCommunities(app.currentUser!!.uid)
      }
    })
  }

  fun checkSwipeRefresh() {
    if (root.swiperefresh.isRefreshing) root.swiperefresh.isRefreshing = false
  }

  override fun onCommunityClick(community: CommunityModel) {
    requireActivity().supportFragmentManager.beginTransaction()
        // .replace(R.id.homeFrame, EditRoutineFragment.newInstance(routine))
        .addToBackStack(null)
        .commit()
  }

  override fun onResume() {
    super.onResume()
    if(this::class == CommunitiesFragment::class)
      getAllUserCommunities(app.currentUser!!.uid)
  }

  companion object {
    @JvmStatic
    fun newInstance() =
        CommunitiesFragment().apply {
          arguments = Bundle().apply { }
        }
  }

  fun getAllUserCommunities(userId: String?) {
    loader = createLoader(requireActivity())
    showLoader(loader, "Downloading your communities from Firebase")
    val communitiesList = ArrayList<CommunityModel>() //Create an array list of community model assigned to the variable communitiesList
    app.database.child("user-communities").child(userId!!)
        .addValueEventListener(object : ValueEventListener {
          override fun onCancelled(error: DatabaseError) {
            info("Firebase Routine error : ${error.message}")
          }

          override fun onDataChange(snapshot: DataSnapshot) {
            hideLoader(loader)
            val children = snapshot.children
            children.forEach {
              val community = it.
              getValue<CommunityModel>(CommunityModel::class.java)

              communitiesList.add(community!!)
              root.recyclerView.adapter =
                  CommunityAdapter(communitiesList, this@YourCommunitiesFragment, reportall = false)
              root.recyclerView.adapter?.notifyDataSetChanged()
              checkSwipeRefresh()

              app.database.child("user-communities").child(userId)
                  .removeEventListener(this)
            }
          }
        })
  }


}