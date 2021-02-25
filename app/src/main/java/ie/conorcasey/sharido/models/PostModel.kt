package ie.conorcasey.sharido.models

import android.os.Parcelable
import com.google.firebase.database.Exclude
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostModel(var uid: String = "",
                          var userName: String = "",
                          var postTitle: String = "N/A",
                          var postContent: String = "N/A",
                          var email: String? = "joe@bloggs.com",
                          var community: String = "N/A",
                          var postPic: String = ""
                              ) : Parcelable {

  @Exclude
  fun toMap(): Map<String, Any?> {
    return mapOf(
        "uid" to uid,
        "postTitle" to postTitle,
        "postContent" to postContent,
        "email" to email,
        "community" to community,
        "postPic" to postPic
    )
  }
}