package com.rawa.notes.ui.feature.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.rawa.notes.R
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private val navArgs by navArgs<DetailFragmentArgs>()
    private val args by lazy { navArgs.detailNavArg }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_detail_text.text = args.name
    }
}
