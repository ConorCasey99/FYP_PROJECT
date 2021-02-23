package ie.conorcasey.sharido.models


import android.os.Parcelable
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Parcelize
data class CommmunityPhotoModel(
    var uid: String? = "",
    var communityPic: String = ""): Parcelable {
  @Exclude
  fun toMap(): Map<String, Any?>{
    return mapOf(
        "uid" to uid,
        "communityPic" to communityPic
    )
  }
}