package com.rawa.notes.ui.feature.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rawa.notes.R
import com.rawa.notes.repository.NotesRepository
import com.rawa.notes.ui.view.note.NoteCard
import com.squareup.cycler.Recycler
import com.squareup.cycler.toDataSource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainFragment : Fragment(), NotesView {
    private lateinit var recycler: Recycler<NotesRow>

    // TODO("Move to view model")
    @Inject
    lateinit var notes: NotesRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_main_notes.layoutManager = LinearLayoutManager(requireContext())
        bindRV(rv_main_notes)

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
                    this.view =
                        layoutInflater.inflate(R.layout.notesrow_noitems, rv_main_notes, false)
                }
            }
        }
    }

    @InternalCoroutinesApi
    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            notes.notes().collect { it ->
                Timber.d("Updating notes: $it")
                recycler.data = it.map { NotesRow.NoteRow(it) }.toDataSource()
            }
        }
    }
}
