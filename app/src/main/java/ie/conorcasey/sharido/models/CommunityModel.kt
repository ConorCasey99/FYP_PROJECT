package ie.conorcasey.sharido.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CommunityModel(var uid: String = "",
                        var communityTitle: String = "N/A",
                        var communityDescription: String = "N/A",
                        var profilepic: String = "",
                        var followedCommunities: Boolean = false,
                        var email: String? = "joe@bloggs.com") : Parcelable