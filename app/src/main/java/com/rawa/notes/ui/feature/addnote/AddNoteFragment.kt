package com.rawa.notes.ui.feature.addnote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.rawa.notes.R
import com.rawa.notes.domain.Note
import com.rawa.notes.ui.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class AddNoteFragment : Fragment(), AddNoteView {

    private val viewModel: AddNoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_addnote, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
        b_addnote_save.setOnClickListener {
            val note = Note(
                tiet_addnote_title.text.toString(),
                tiet_addnote_text.text.toString()
            )
            lifecycleScope.launch {
                viewModel.save(note)
                hideKeyboard()
                findNavController().navigateUp()
            }
        }
        */
    }
}
