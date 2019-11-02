package fiveagency.internship.food.movieapp.ui.moviedetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fiveagency.internship.food.domain.model.Cast
import fiveagency.internship.food.movieapp.R
import fiveagency.internship.food.movieapp.ui.utils.ImageLoader
import kotlinx.android.synthetic.main.cast_item_layout.view.*

class MovieDetailsCastAdapter(
    private val layoutInflater: LayoutInflater,
    private val imageLoader: ImageLoader
) : RecyclerView.Adapter<MovieDetailsCastAdapter.CastViewHolder>() {

    private var castList: List<Cast> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        return CastViewHolder(layoutInflater.inflate(R.layout.cast_item_layout, parent, false), imageLoader)
    }

    override fun getItemCount(): Int = castList.size

    fun setCast(cast: List<Cast>) {
        castList = cast
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.render(castList[position])
    }

    class CastViewHolder(itemView: View, private val imageLoader: ImageLoader) : RecyclerView.ViewHolder(itemView) {

        fun render(cast: Cast) {
            with(cast) {
                itemView.castName.text = name
                itemView.characterName.text = character
                imageLoader.renderImage(cast.profileUrl, itemView.profilePicture, 0f)
            }
        }
    }
}