package com.rawa.notes.ui.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rawa.notes.R
import com.rawa.notes.domain.Note
import com.rawa.notes.ui.view.note.NoteCard
import com.squareup.cycler.Recycler
import com.squareup.cycler.toDataSource
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {
    private lateinit var recycler: Recycler<NotesRow>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_main_notes.layoutManager = LinearLayoutManager(requireContext())
        bindRV(rv_main_notes)

        recycler.data = listOf(
            NotesRow.NoteRow(Note(1, "Title", "Text")),
            NotesRow.NoteRow(Note(2, "Title A very long title that won't fit completely in the card", "Text")),
            NotesRow.NoteRow(Note(3, "Title", "A very long text what hopefully will wrap")),
            NotesRow.NoteRow(Note(4, "Title", "This is a mega super long text that will be ellipsized at the end because it is too long to be shown within the 3 lines of the textview. So lets add the extra words to tip the size")),
            NotesRow.NoteRow(Note(5, "Title", "Text")),
            NotesRow.NoteRow(Note(6, "Title A very long title that won't fit completely in the card", "Text")),
            NotesRow.NoteRow(Note(7, "Title", "A very long text what hopefully will wrap")),
            NotesRow.NoteRow(Note(8, "Title", "This is a mega super long text that will be ellipsized at the end because it is too long to be shown within the 3 lines of the textview. So lets add the extra words to tip the size"))
        ).toDataSource()

        fab_main_addnote.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToAddNote())
        }
    }

    private fun bindRV(rv: RecyclerView) {
        recycler = Recycler.adopt(rv) {
            row<NotesRow.NoteRow, NoteCard> {
                create { context ->
                    view = NoteCard(context)
                    bind { item ->
                        view.render(item.note)
                    }
                }
            }
            extraItem<NotesRow.NoItems, View> {
                create {
                    this.view = layoutInflater.inflate(R.layout.notesrow_noitems, rv_main_notes, false)
                }

            }
        }
    }
}
