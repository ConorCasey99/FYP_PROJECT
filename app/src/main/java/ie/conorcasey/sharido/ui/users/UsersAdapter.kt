package ie.conorcasey.sharido.ui.users
/*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ie.conorcasey.sharido.R
import ie.conorcasey.sharido.models.UserModel

import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.users_card.view.*


interface UserListener {
    fun onUserClick(user: UserModel)
}

class UsersAdapter constructor(private var users: ArrayList<UserModel>,
                                 private val listener: UserListener, reportall: Boolean)
    : RecyclerView.Adapter<UsersAdapter.MainHolder>() {

    val reportAll = reportall

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.users_card,
                parent,
                false
            )
        )
    }



    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val users = users[holder.adapterPosition]
        holder.bind(users, listener, reportAll)
    }

    override fun getItemCount(): Int = users.size

    fun removeAt(position: Int){
        users.removeAt(position)
        notifyItemRemoved(position)
    }
/*
    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: UserModel, listener : UserListener, reportAll: Boolean) {
            itemView.tag = user
            itemView.userName.text = user.userName

            if(!reportAll)
                itemView.setOnClickListener { listener.onUserClick(user) }

            if(!user.profilepic.isEmpty()) {
                Picasso.get().load(user.profilepic.toUri())
                    //.resize(180, 180)
                    .transform(CropCircleTransformation())
                    .into(itemView.imageIcon)
            }
            else
                itemView.imageIcon.setImageResource(R.mipmap.ic_launcher)

        }
    }*/
}

*/