package com.rawa.notes.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.rawa.notes.R
import com.rawa.notes.feature.detail.DetailArg
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        b_main_detail.setOnClickListener {
            findNavController()
                .navigate(
                    MainFragmentDirections.actionMainFragmentToDetailFragment(
                        DetailArg("This is a argument")
                    )
                )
        }
    }
}
