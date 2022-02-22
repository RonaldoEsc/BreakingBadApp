package com.hrec.miappdebreakingbad.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hrec.miappdebreakingbad.MainActivity
import com.hrec.miappdebreakingbad.R
import com.hrec.miappdebreakingbad.adapters.MyItemRecyclerViewAdapter

class ListOfCharactersFragment : Fragment() {

    private var columnCount = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_characters_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = mContext?.let {
                    MyItemRecyclerViewAdapter(MainActivity.mCharactersList,
                        it
                    )
                }
            }
        }
        return view
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"
        private var mContext: MainActivity? = null

        @JvmStatic
        fun newInstance(columnCount: Int, context: MainActivity) =
            ListOfCharactersFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
                mContext = context
            }
    }
}