package fiveagency.internship.food.movieapp.ui.actordetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fiveagency.internship.food.domain.model.PersonMovieCredit
import fiveagency.internship.food.domain.model.PersonMovieCredits
import fiveagency.internship.food.movieapp.R
import fiveagency.internship.food.movieapp.ui.utils.ImageLoader
import kotlinx.android.synthetic.main.actor_details_movie_item_layout.view.*

class ActorDetailsMovieAdapter(val layoutInflater: LayoutInflater, val imageLoader: ImageLoader) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var personMovieCredits: PersonMovieCredits = PersonMovieCredits(emptyList())

    fun setItems(personMovieCredits: PersonMovieCredits) {
        this.personMovieCredits = personMovieCredits
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ActorDetailsMovieItem(layoutInflater.inflate(R.layout.actor_details_movie_item_layout, parent, false), imageLoader)
    }

    override fun getItemCount(): Int = personMovieCredits.credits.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ActorDetailsMovieItem).render(personMovieCredits.credits[position])
    }

    class ActorDetailsMovieItem(itemView: View, private val imageLoader: ImageLoader) : RecyclerView.ViewHolder(itemView) {
        fun render(personMovieCredit: PersonMovieCredit) {
            with(itemView) {
                imageLoader.renderImage(personMovieCredit.backdropPath, backdropImage, 0f)
            }
        }
    }
}