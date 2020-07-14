package com.rawa.notes.ui.feature.addnote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.rawa.notes.R
import com.rawa.notes.domain.Note
import com.rawa.notes.repository.NotesRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_addnote.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddNoteFragment : Fragment() {

    // TODO("Move to view model")
    @Inject
    lateinit var notes: NotesRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_addnote, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        b_addnote_save.setOnClickListener {
            val note = Note(
                tiet_addnote_title.text.toString(),
                tiet_addnote_text.text.toString()
            )
            lifecycleScope.launch {
                notes.addNote(note)
                findNavController().navigateUp()
            }
        }
    }
}
