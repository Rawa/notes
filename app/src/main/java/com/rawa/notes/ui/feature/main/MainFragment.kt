package com.rawa.notes.ui.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Box
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRowFor
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rawa.notes.R
import com.rawa.notes.domain.Note
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainFragment : Fragment(), NotesView {
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
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                render(viewState = MainViewState(emptyList()))
            }
        }
    }

    @Composable
    fun render(viewState: MainViewState) {
        MaterialTheme(
            colors = MaterialTheme.colors.copy(
                primary = Color(resources.getColor(R.color.colorPrimary, null)),
                primaryVariant = Color(resources.getColor(R.color.colorPrimaryDark, null)),
                secondary = Color(resources.getColor(R.color.colorAccent, null))
            ),
            content = {

                if (viewState.notes.isEmpty()) {
                    Empty()
                } else {
                    Notes(viewState.notes)
                }

                Box(
                    gravity = Alignment.BottomEnd,
                ) {
                    FloatingActionButton(
                        icon = { Icon(Icons.Default.Add) },
                        elevation = 8.dp,
                        onClick = {
                            findNavController().navigate(MainFragmentDirections.actionMainFragmentToAddNote())
                        },
                        modifier = Modifier.padding(16.dp)
                    )
                }
            })
    }

    @Composable
    fun Empty() {
        Text(text = "There are no notes")
    }

    @Composable
    fun Notes(notes: List<NotesRow>) {
        LazyRowFor(items = notes, itemContent = {
            when (it) {
                is NotesRow.NoteRow -> {
                    NoteWidget(it)
                }
            }
        })
    }

    @Composable
    fun NoteWidget(note: NotesRow.NoteRow) {
        Text(note.note.title)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
        rv_main_notes.layoutManager = LinearLayoutManager(requireContext())
        bindRV(rv_main_notes)
        fab_main_addnote.setOnClickListener {
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
         */
    }

    /**
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
    }
    }
     */
}
