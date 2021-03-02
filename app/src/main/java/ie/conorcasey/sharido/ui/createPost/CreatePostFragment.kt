package ie.conorcasey.sharido.ui.createPost
/*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import ie.conorcasey.sharido.R
import ie.conorcasey.sharido.main.MainApp
import ie.conorcasey.sharido.models.CommunityModel
import kotlinx.android.synthetic.main.fragment_create_community.*
import kotlinx.android.synthetic.main.fragment_create_community.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.support.v4.toast
import java.util.*

class CreatePostFragment : Fragment(), AnkoLogger {

  lateinit var app: MainApp
  lateinit var loader : AlertDialog
  var community = CommunityModel()
  var followed = false


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    app = activity?.application as MainApp
  }


  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    val root = inflater.inflate(R.layout.fragment_create_community, container, false)



    setButtonListener(root)
    //setFollowedListener(root)
    return root
  }

  companion object {
    @JvmStatic
    fun newInstance() =
        CreateCommunityFragment().apply {
          arguments = Bundle().apply {}
        }
  }




/*
  fun setFollowedListener (layout: View) {
    layout.follow.setOnClickListener(object : View.OnClickListener {
      override fun onClick(view: View?) {
        if (!followed) {
          layout.imageFollowed.setImageResource(android.R.drawable.star_big_on)
          followed = true
        }
        else {
          layout.imageFollowed.setImageResource(android.R.drawable.star_big_off)
          followed = false
        }
      }
    })
  }*/



  fun setButtonListener(layout: View){                                           //function adapted from code from Mobile Application Development 2 module labs

    layout.create_community_button.setOnClickListener{                           //function adapted from code from Mobile Application Development 2 module labs

      val communityNameVal= layout.communityName.text.toString()
      val communityDescriptionVal = layout.communityDescription.text.toString()
      val communityCategoryVal =  spCategories.selectedItem.toString()


      if(community.communityName.isEmpty())
        toast("Please enter a name for your community")
      else if (community.communityDescription.isEmpty()) {
        toast("Please enter a description for your community")
      }else{
        createNewCommunity(CommunityModel(
            communityName = communityNameVal,
            communityDescription = communityDescriptionVal,
            communityCategory = communityCategoryVal
            // communityPic = app.userImage.toString(),
            // followed = following,
        ))
      }
    }
  }

  fun createNewCommunity(community: CommunityModel /*post: PostModel*/) {               //function adapted from code from Mobile Application Development 2 module labs
    // Create new routine at /community & /community/$uid
    //showLoader(loader, "Adding Community to Firebase")
    //val newDb: DatabaseReference = dbReference
    info("Firebase DB Reference : ${app.database}")        //function adapted from code from Mobile Application Development 2 module labs
    val uid = app.currentUser!!.uid                                 //function adapted from code from Mobile Application Development 2 module labs
    val key = app.database.child("communites").push().key
    var randomCommunityId = UUID.randomUUID().toString()
    //function adapted from code from Mobile Application Development 2 module labs
    if (key == null) {                                              //function adapted from code from Mobile Application Development 2 module labs
      info("Firebase Error : Key Empty")                   //function adapted from code from Mobile Application Development 2 module labs
      return                                                        //function adapted from code from Mobile Application Development 2 module labs
    }
    community.uid = key
    val communityValues = community.toMap()                          //function adapted from code from Mobile Application Development 2 module labs
    val childUpdates = HashMap<String, Any>()                        //function adapted from code from Mobile Application Development 2 module labs
    childUpdates["/communities/$key"] = communityValues              //function adapted from code from Mobile Application Development 2 module labs
    childUpdates["/user-communities/$uid/$key"] = communityValues
    //communityId = randomCommunityId//function adapted from code from Mobile Application Development 2 module labs
    app.database.updateChildren(childUpdates)                         //function adapted from code from Mobile Application Development 2 module labs
    // hideLoader(loader)
  }
}*/