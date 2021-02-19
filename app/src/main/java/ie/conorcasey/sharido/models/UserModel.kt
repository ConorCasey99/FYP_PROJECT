package ie.conorcasey.sharido.models


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel(var uid: String = "",
                          var userName: String = "N/A",
                          var userEmail: String? = "name@email.com"
                        ) : Parcelable