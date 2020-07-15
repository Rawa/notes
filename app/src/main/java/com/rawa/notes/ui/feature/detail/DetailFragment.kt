package com.rawa.notes.ui.feature.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rawa.notes.R
import com.rawa.notes.domain.Note
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DetailFragment : Fragment(), DetailView {

    private val viewModel: DetailViewModel by viewModels()
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
        lifecycleScope.launch {
            viewModel.execute(args.id).collect {
                render(it)
            }
        }
        b_detail_delete.setOnClickListener {
            val note = it.tag as Note
            lifecycleScope.launch {
                viewModel.delete(note)
                findNavController().navigateUp()
            }
        }
        b_detail_save.setOnClickListener {
            var note = it.tag as Note
            note = note.copy(
                title = tiet_detail_title.text.toString(),
                text = tiet_detail_text.text.toString()
            )

            lifecycleScope.launch {
                viewModel.save(note)
                findNavController().navigateUp()
            }
        }
    }

    override fun noteId(): Long {
        return args.id
    }

    private fun render(detailViewState: DetailViewState) {
        tiet_detail_title.setText(detailViewState.note?.title.toString())
        tiet_detail_text.setText(detailViewState.note?.text.toString())
        b_detail_save.tag = detailViewState.note
        b_detail_delete.tag = detailViewState.note
    }
}
