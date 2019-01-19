package com.example.mg156.assignment4

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import java.io.Serializable


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MovieFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class MovieFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: Int? = 0

    lateinit var movie: MovieDataClass
    var posterId: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movie = it.getSerializable(ARG_PARAM1) as MovieDataClass
            posterId = it.getInt(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val movieView = inflater.inflate(R.layout.fragment_movie, container, false)
        val movie_id = movieView.findViewById(R.id.movieId) as TextView
        val movie_title = movieView.findViewById(R.id.movieTitle) as TextView
        val movie_image = movieView.findViewById(R.id.movieImage) as ImageView
        val movie_overview = movieView.findViewById(R.id.movieOverview) as TextView
        val movie_language = movieView.findViewById(R.id.movieLanguage) as TextView
        val movie_year = movieView.findViewById(R.id.movieYear) as TextView

        movie_title.setText("Movie Title: " + movie?.title.toString())
        movie_overview.setText("Movie Overview:" + movie?.overview.toString())
        movie_image.setImageResource(posterId!!)
        movie_year.setText("Release Date: " + movie?.release_date.toString())
        movie_language.setText("Movie Language: " + movie?.original_language.toString())
        movie_id.setText("Movie ID: " + movie?.id.toString())

        return movieView
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: MovieDataClass, param2: Int) : MovieFragment{
            val args = Bundle()
            args.putSerializable(ARG_PARAM1,param1 as Serializable)
            args.putInt(ARG_PARAM2,param2)
            val fragment = MovieFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
