package fiveagency.internship.food.movieapp.ui.actordetails

import android.animation.ValueAnimator
import android.graphics.Typeface
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.appbar.AppBarLayout
import fiveagency.internship.food.domain.model.ActorDetails
import fiveagency.internship.food.movieapp.R
import fiveagency.internship.food.movieapp.injection.fragment.FragmentComponent
import fiveagency.internship.food.movieapp.ui.base.BaseFragment
import fiveagency.internship.food.movieapp.ui.utils.ImageLoader
import kotlinx.android.synthetic.main.fragment_actor_details.*
import java.io.IOException
import javax.inject.Inject
import kotlin.math.abs
import kotlin.math.roundToInt

class ActorDetailsFragment : BaseFragment<ActorDetailsContract.Presenter>(), ActorDetailsContract.View {

    companion object {
        const val TAG: String = "ActorDetailsFragment"
        private const val ACTOR_ID = "actor_id"
        fun newInstance(actorId: Int): ActorDetailsFragment {
            return ActorDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ACTOR_ID, actorId)
                }
            }
        }
    }

    @Inject
    lateinit var imageLoader: ImageLoader

    private val timedAction = TimedAction(Runnable {
        movieImagesRecyclerView.layoutManager?.let {
            with(it as LinearLayoutManager) {
                if (itemCount != 0) movieImagesRecyclerView.smoothScrollToPosition((findFirstCompletelyVisibleItemPosition() + 1) % itemCount)
            }
        }
    })

    private val actorId: Int by lazy { arguments?.getInt(ACTOR_ID) ?: -1 }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_actor_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        initCollapsingToolbarLayout()

        placeOfBirthMapView.onCreate(savedInstanceState)

        arrowBack.setOnClickListener {
            presenter.goBack()
        }

        presenter.setView(this)
    }

    override fun onResume() {
        super.onResume()
        setStatusBarColor(R.color.transparent)
        presenter.onStart(actorId)
        timedAction.start()
    }

    override fun onPause() {
        timedAction.cancel()
        super.onPause()
    }

    override fun render(actorDetails: ActorDetails) {
        with(actorDetails) {
            birthDayText.text = birthday
            deathDayText.text = deathday
            biographyText.text = biography
            professionText.text = profession
            placeOfBirthText.text = placeOfBirth
            imageLoader.renderImage(profileImageUrl, profilePicture, 0f)
            collapsingToolbarLayout.title = name

            placeOfBirthMapView.getMapAsync {
                try {
                    val address = Geocoder(context).getFromLocationName(placeOfBirth, 1).firstOrNull()
                    address?.let { _ ->
                        val latLng = LatLng(address.latitude, address.longitude)

                        it.addMarker(
                            MarkerOptions()
                                .position(latLng)
                                .title(placeOfBirth)
                        )
                        it.moveCamera(CameraUpdateFactory.newLatLng(latLng))
                    } ?: showMapErrorState()
                } catch (exc: IOException) {
                    showMapErrorState()
                }
            }
            (movieImagesRecyclerView.adapter as? ActorDetailsMovieAdapter)?.setItems(actorMovieCredits)

            val animator = initRevealingMapAnimator(placeOfBirthMapContainer, placeOfBirthMapView.height)

            var isExpanded = false
            mapIcon.setOnClickListener {
                if (isExpanded) {
                    animator.reverse()
                } else {
                    animator.start()
                }
                isExpanded = !isExpanded
            }
        }
    }

    private fun initRevealingMapAnimator(
        view: View,
        height: Int,
        duration: Long = 500L,
        startValue: Float = 0f,
        endValue: Float = 100f
    ): ValueAnimator {
        val layoutParams = view.layoutParams
        return ValueAnimator.ofFloat(startValue, endValue).apply {
            addUpdateListener {
                layoutParams.height = (height * it.animatedFraction).roundToInt()
                view.layoutParams = layoutParams
            }
            this.duration = duration
        }
    }

    private fun showMapErrorState() {
        mapErrorView.visibility = View.VISIBLE
    }

    private fun initRecyclerView() {
        val adapter = ActorDetailsMovieAdapter(layoutInflater, imageLoader)
        val layoutManager = LinearLayoutManager(movieImagesRecyclerView.context, LinearLayoutManager.HORIZONTAL, false)
        val snapHelper = PagerSnapHelper()

        snapHelper.attachToRecyclerView(movieImagesRecyclerView)
        movieImagesRecyclerView.layoutManager = layoutManager
        movieImagesRecyclerView.adapter = adapter
        movieImagesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                when (newState) {
                    RecyclerView.SCROLL_STATE_IDLE -> timedAction.start()
                    else -> timedAction.cancel()
                }
            }
        })
    }

    private fun initCollapsingToolbarLayout() {
        collapsingToolbarLayout.setExpandedTitleTypeface(Typeface.create(collapsingToolbarLayout.expandedTitleTypeface, Typeface.BOLD))
        collapsingToolbarLayout.setCollapsedTitleTypeface(Typeface.create(collapsingToolbarLayout.collapsedTitleTypeface, Typeface.BOLD))

        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, offset ->
            profilePictureCard.alpha = 1 - abs(offset.toFloat() / appBarLayout.totalScrollRange) * 2f
        })
    }

    override fun inject(fragmentComponent: FragmentComponent?) {
        fragmentComponent?.inject(this)
    }

}