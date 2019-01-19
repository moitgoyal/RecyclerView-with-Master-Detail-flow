package com.example.mg156.assignment4

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FrontFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FrontFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FrontFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_front, container, false)
        val btnAbout = view.findViewById(R.id.btnAboutMe) as Button
        val btnMovie = view.findViewById(R.id.btnMovieDetails) as Button

        btnAbout.setOnClickListener(View.OnClickListener { v -> listener?.onFragmentInteraction(v) })
        btnMovie.setOnClickListener(View.OnClickListener { v -> listener?.onFragmentInteraction(v) })
        return view
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(v: View)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) : FrontFragment{
            val args = Bundle()
            args.putSerializable(param1,"fragmentFront")
            val fragment = FrontFragment()
            fragment.arguments = args
            return fragment
        }

    }
}
