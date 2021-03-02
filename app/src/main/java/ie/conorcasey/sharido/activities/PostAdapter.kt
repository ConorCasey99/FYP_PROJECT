package ie.conorcasey.sharido.activities
// import android.view.LayoutInflater
// import android.view.View
// import android.view.ViewGroup
// import androidx.core.net.toUri
// import androidx.recyclerview.widget.RecyclerView
// import com.squareup.picasso.Picasso
// import ie.conorcasey.sharido.R
// import ie.conorcasey.sharido.models.CommunityModel
// import ie.conorcasey.sharido.models.PostModel
// import jp.wasabeef.picasso.transformations.CropCircleTransformation
// import kotlinx.android.synthetic.main.community_card.view.*
//
//
// interface PostListener {
// fun onPostClick(post: PostModel)
// }
//
// class PostAdapter constructor(private var post: ArrayList<PostModel>,
// private val listener: PostListener, reportall: Boolean)
// : RecyclerView.Adapter<PostAdapter.MainHolder>() {
//
// val reportAll = reportall
// val arrayList = ArrayList<PostModel>()
//
// override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
// return MainHolder(
// LayoutInflater.from(parent.context).inflate(
// R.layout.post_card,
// parent,
// false
// )
// )
// }
//
// override fun onBindViewHolder(holder: MainHolder, position: Int) {
// val post = posts[holder.adapterPosition]
// holder.bind(post, listener, reportAll)
// }
//
// override fun getItemCount(): Int = posts.size
//
// fun removeAt(position: Int){
// posts.removeAt(position)
// notifyItemRemoved(position)
// }
//
//
// class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
// fun bind(post: PostModel, listener : CommunityListener, reportAll: Boolean) {
// itemView.tag = post
// itemView.communityName.text = community.communityName
//
// itemView.communityCategory.text = community.communityCategory
//
// if(!reportAll)
// itemView.setOnClickListener { listener.onCommunityClick(community) }
//
// if(!community.communityPic.isEmpty()) {
// Picasso.get().load(community.communityPic.toUri())
// .resize(180, 180)
// .transform(CropCircleTransformation())
// .into(itemView.imageview_community_picture)
// }
// else
// itemView.imageview_community_picture.setImageResource(R.mipmap.ic_launcher)
//
// }
//
// }
// }