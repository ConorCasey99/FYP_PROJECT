package ie.conorcasey.sharido.ui.yourCommunities

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
import kotlinx.android.synthetic.main.fragment_communities.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


open class YourCommunitiesFragment : Fragment(), AnkoLogger, CommunityListener {

  lateinit var app: MainApp
  lateinit var loader : AlertDialog
  lateinit var root: View
  private val communitiesList: List<CommunityModel> = ArrayList()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    app = activity?.application as MainApp
  }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    root = inflater.inflate(R.layout.fragment_communities, container, false)
    root.communitiesRecyclerView.setLayoutManager(LinearLayoutManager(activity))
    setSwipeRefresh()
   // recyclerView = root?.findViewById(R.id.recyclerView)
    // rest of my stuff
    // ?.setHasFixedSize(true)
   // recyclerView?.layoutManager = viewManager
   // recyclerView?.adapter = CommunityAdapter

    return root
  }

  fun deleteUserCommunity(userId: String, uid: String?) {
    app.database.child("user-communities").child(userId).child(uid!!)
        .addListenerForSingleValueEvent(
            object : ValueEventListener {
              override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.ref.removeValue()
              }
              override fun onCancelled(error: DatabaseError) {
                info("Firebase Community error : ${error.message}")
              }
            })
  }

  fun deleteCommunity(uid: String?) {
    app.database.child("communities").child(uid!!)
        .addListenerForSingleValueEvent(
            object : ValueEventListener {
              override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.ref.removeValue()
              }
              override fun onCancelled(error: DatabaseError) {
                info("Firebase Community error : ${error.message}")
              }
            })
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
    //Toast.makeText(activity, "Position is " + communitiesList.get(position), Toast.LENGTH_SHORT).show()
   // app.database.get("communities").child("posts")
  }

  override fun onResume() {
    super.onResume()
    if(this::class == YourCommunitiesFragment::class)
      getAllUserCommunities(app.currentUser!!.uid)
  }

  companion object {
    @JvmStatic
    fun newInstance() =
        YourCommunitiesFragment().apply {
          arguments = Bundle().apply { }
        }
  }

  /*
  fun onChildRemoved(dataSnapshot: DataSnapshot) {
    val key = dataSnapshot.key
    if (mKeys.contains(key)) {
      val index: Int = mKeys.indexOf(key)
      val item: T = mItems.get(index)
      mKeys.remove(index)
      mItems.remove(index)
      notifyItemRemoved(index)
      itemRemoved(item, key, index)
    }
  }*/


  fun getAllUserCommunities(userId: String?) {
    loader = createLoader(requireActivity())
    showLoader(loader, "Downloading your communities from Firebase")
    val communitiesList = ArrayList<CommunityModel>() //Create an array list of community model assigned to the variable communitiesList
    app.database.child("user-communities").child(userId!!)
        .addValueEventListener(object : ValueEventListener {
          override fun onCancelled(error: DatabaseError) {
            info("Firebase Routine error : ${error.message}")
          }

          override fun onDataChange(snapshot: DataSnapshot) {//triggered by data change
            hideLoader(loader)
            val children = snapshot.children
            children.forEach {
              val community = it.
              getValue<CommunityModel>(CommunityModel::class.java)

              communitiesList.add(community!!)
              root.communitiesRecyclerView.adapter =
                  CommunityAdapter(communitiesList, this@YourCommunitiesFragment, reportall = false)
              root.communitiesRecyclerView.adapter?.notifyDataSetChanged()
              checkSwipeRefresh()

              app.database.child("user-communities").child(userId)
                  .removeEventListener(this)
            }
          }
        })
  }


}