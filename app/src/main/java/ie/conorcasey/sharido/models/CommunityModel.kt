package ie.conorcasey.sharido.models

import android.os.Parcelable
import com.google.firebase.database.Exclude
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CommunityModel(
                          var communityId:String = "",
                          var uid: String = "",
                          var communityName: String = "N/A",
                          var communityDescription: String = "N/A",
                          var communityCategory: String = "N/A",
                          var communityPic: String = "",
                          var followed: Boolean = false
                          ) : Parcelable {

  @Exclude
  fun toMap(): Map<String, Any?> {
    return mapOf(
        "uid" to uid,
        "communityName" to communityName,
        "communityDescription" to communityDescription,
        "communityCategory" to communityCategory,
        "communityPic" to communityPic,
        "followed" to followed)
  }
}