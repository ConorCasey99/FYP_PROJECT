package ie.conorcasey.sharido.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ie.conorcasey.sharido.R
import ie.conorcasey.sharido.models.CommunityModel
import ie.conorcasey.sharido.models.PostModel
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.community_card.view.*


interface CommunityListener {
  fun onCommunityClick(community: CommunityModel)
}

class CommunityAdapter constructor(private var communities: ArrayList<CommunityModel>,
                                   private val listener: CommunityListener, reportall: Boolean)
  : RecyclerView.Adapter<CommunityAdapter.MainHolder>() {

  val reportAll = reportall
  val arrayList = ArrayList<PostModel>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
    return MainHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.community_card,
            parent,
            false
        )
    )
  }

  override fun onBindViewHolder(holder: MainHolder, position: Int) {
    val community = communities[holder.adapterPosition]
    holder.bind(community, listener, reportAll)
  }

  override fun getItemCount(): Int = communities.size

  fun removeAt(position: Int){
        communities.removeAt(position)
        notifyItemRemoved(position)
  }


  class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(community: CommunityModel,  listener : CommunityListener, reportAll: Boolean) {
      itemView.tag = community
      itemView.communityName.text = community.communityName
      itemView.communityCategory.text = community.communityCategory

      if(!reportAll)
        itemView.setOnClickListener { listener.onCommunityClick(community) }

      if(!community.communityPic.isEmpty()) {
        Picasso.get().load(community.communityPic.toUri())
            .resize(180, 180)
            .transform(CropCircleTransformation())
            .into(itemView.imageview_community_picture)
      }
      else
        itemView.imageview_community_picture.setImageResource(R.mipmap.ic_launcher)

    }

  }
}
