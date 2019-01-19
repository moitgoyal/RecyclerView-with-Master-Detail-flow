package com.example.mg156.assignment4

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import jp.wasabeef.recyclerview.animators.ScaleInAnimator

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RecyclerViewFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    lateinit var recycleView: RecyclerView
    lateinit var recycleViewAdapter: RecyclerViewAdapter
    lateinit var selectall: Button
    lateinit var clearall: Button
    lateinit var delete: Button
    lateinit var sort: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            //param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootview = inflater.inflate(R.layout.fragment_recycler_view, container, false)

        selectall = rootview.findViewById(R.id.selectall) as Button
        clearall = rootview.findViewById(R.id.clearall) as Button
        delete = rootview.findViewById(R.id.delete) as Button
        sort = rootview.findViewById(R.id.sort) as Button
        recycleView = rootview.findViewById(R.id.recyclerViewId) as RecyclerView
        recycleViewAdapter = RecyclerViewAdapter()
        recycleView.setAdapter(recycleViewAdapter)
        recycleView.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(context)
        recycleView.setLayoutManager(mLayoutManager)

        recycleView.itemAnimator?.addDuration = 2000L
        recycleView.itemAnimator?.removeDuration = 3000L
        recycleView.itemAnimator?.changeDuration = 1000L
        recycleView.itemAnimator?.moveDuration = 1000L

        selectall.setOnClickListener {
            recycleViewAdapter.setSelectAll()
            recycleViewAdapter.notifyDataSetChanged()
        }
        clearall.setOnClickListener {
            recycleViewAdapter.setClearAll()
            recycleViewAdapter.notifyDataSetChanged()
        }
        sort.setOnClickListener {
            recycleViewAdapter.setSort()
            recycleViewAdapter.notifyDataSetChanged()
        }
        delete.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                recycleViewAdapter.setDelete()
            }
        })
        recycleViewAdapter.setMyItemClickListener(object : RecyclerViewAdapter.MyItemClickListener {
           override fun onItemClickedFromAdapter(movie : MovieDataClass) {
                listener?.onFragmentInteraction(movie)
            }

            override fun onItemLongClickedFromAdapter(position: Int) {
                recycleViewAdapter.addItem(position + 1, (movieList[position].copy()))
                recycleViewAdapter.notifyItemInserted(position + 1)
            }
        })
        adapterAnimation()
        return rootview
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
        fun onFragmentInteraction(movie : MovieDataClass)
    }

    private fun adapterAnimation() {
        val alphaAdapter = AlphaInAnimationAdapter(recycleViewAdapter)
        val scaleAdapter = ScaleInAnimationAdapter(alphaAdapter)
        recycleView.setAdapter(scaleAdapter)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) : RecyclerViewFragment{
            val args = Bundle()
            args.putSerializable(param1,"RecyclerFragment")
            val fragment = RecyclerViewFragment()
            fragment.arguments = args
            return fragment
        }

    }
}
