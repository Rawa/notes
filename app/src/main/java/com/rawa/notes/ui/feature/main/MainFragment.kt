package com.rawa.notes.ui.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.rawa.notes.R
import com.rawa.notes.ui.feature.detail.DetailArg
import com.rawa.notes.ui.view.note.NoteCard
import com.squareup.cycler.Recycler
import com.squareup.cycler.toDataSource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainFragment : Fragment(), NotesView {
    private lateinit var recycler: Recycler<NotesRow>

    private val mainViewModel: MainViewModel by viewModels()

    private val navArgs by navArgs<MainFragmentArgs>()

    // TODO Find a nicer way to do clear the navigational argument. With androidx fragment there exists "setFragmentResultListener" and "setFragmentResult" for communication between fragment, however, these requiring dealing with bundles.
    private var noteDeletedArg: NoteDeleted? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noteDeletedArg = navArgs.noteDeleted
    }

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

        viewLifecycleOwner.lifecycleScope.launch {
            mainViewModel.notes.collect {
                Timber.d("New list of rows: $it")

                recycler.data = it.notes.toDataSource()
                recycler.extraItem = it.extraItem
            }
        }

        noteDeletedArg?.let {
            Snackbar.make(view, R.string.detail_delete_snackbar, Snackbar.LENGTH_LONG)
                .setAction(R.string.detail_delete_snackbar_action) { _ ->
                    requireActivity().lifecycleScope.launch {
                        mainViewModel.undoDeletion(it.id)
                    }
                }.show()
            noteDeletedArg = null
        }
    }

    private fun bindRV(rv: RecyclerView) {
        recycler = Recycler.adopt(rv) {
            row<NotesRow.NoteRow, NoteCard> {
                create { context ->
                    view = NoteCard(context)
                    bind { item ->
                        view.render(item.note)
                        view.setOnClickListener {
                            findNavController().navigate(
                                MainFragmentDirections.actionMainFragmentToDetailFragment(
                                    item.note.title,
                                    DetailArg(item.note.id)
                                )
                            )
                        }
                    }
                }
            }
            extraItem<NotesRow.NoItems, View> {
                create {
                    view = layoutInflater.inflate(R.layout.notesrow_noitems, rv_main_notes, false)
                }
            }
        }
    }
}
