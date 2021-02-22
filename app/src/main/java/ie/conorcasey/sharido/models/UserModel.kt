package ie.conorcasey.sharido.models


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(val uid: String = "",
                          val userName: String = "N/A",
                          val valuserEmail: String? = "name@email.com"
                        ) : Parcelable