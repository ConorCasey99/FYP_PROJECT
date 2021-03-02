package ie.conorcasey.sharido.ui.communities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import ie.conorcasey.sharido.R
import ie.conorcasey.sharido.activities.CommunityAdapter
import ie.conorcasey.sharido.activities.CommunityListener
import ie.conorcasey.sharido.helpers.createLoader
import ie.conorcasey.sharido.helpers.hideLoader
import ie.conorcasey.sharido.helpers.showLoader
import ie.conorcasey.sharido.models.CommunityModel
import ie.conorcasey.sharido.ui.yourCommunities.YourCommunitiesFragment
import kotlinx.android.synthetic.main.fragment_communities.view.*
import org.jetbrains.anko.info

class CommunitiesFragment : YourCommunitiesFragment(), CommunityListener {

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    root = inflater.inflate(R.layout.fragment_communities, container, false)
    //val textView: TextView = root.findViewById(R.id.titleText)
    // slideshowViewModel.text.observe(viewLifecycleOwner, Observer {
    //  textView.text = it
    //})
    //root.recyclerView.setLayoutManager(LinearLayoutManager(activity))
    //setSwipeRefresh()
    root.communitiesRecyclerView.setLayoutManager(LinearLayoutManager(activity))
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
    getAllCommunities()
  }

  fun getAllCommunities() {
    loader = createLoader(requireActivity())
    showLoader(loader, "Downloading All Communities from Firebase")
    val communityList = ArrayList<CommunityModel>()
    app.database.child("communities")
        .addValueEventListener(object : ValueEventListener {
          override fun onCancelled(error: DatabaseError) {
            info("Firebase Community error : ${error.message}")
          }

          override fun onDataChange(snapshot: DataSnapshot) {
            hideLoader(loader)
            val children = snapshot.children
            children.forEach {
              val community = it.
              getValue<CommunityModel>(CommunityModel::class.java)

              communityList.add(community!!)
              root.communitiesRecyclerView.adapter =
                  CommunityAdapter(communityList, this@CommunitiesFragment, reportall = true)
              root.communitiesRecyclerView.adapter?.notifyDataSetChanged()
              checkSwipeRefresh()

              app.database.child("communities").removeEventListener(this)
            }
          }
        })
  }
  }








