package com.rawa.notes.ui.feature.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rawa.notes.R
import com.rawa.notes.domain.Note
import com.rawa.notes.ui.feature.Async
import com.rawa.notes.ui.feature.Fail
import com.rawa.notes.ui.feature.Loading
import com.rawa.notes.ui.feature.Success
import com.rawa.notes.ui.feature.Uninitialized
import com.rawa.notes.ui.feature.main.NoteDeleted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.launch
import ru.ldralighieri.corbind.view.clicks
import timber.log.Timber

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
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.bind(this@DetailFragment).collect {
                render(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.d("OnDestroyView!")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("OnDestroy!")
    }

    override fun noteId(): Long {
        return args.id
    }

    override fun events(): Flow<DetailEvent> {
        return merge(
            b_detail_delete.clicks()
                .map {
                    DetailEvent.DeleteNote(b_detail_delete.tag as Note)
                },
            b_detail_save.clicks()
                .map {
                    var note = b_detail_save.tag as Note
                    note = note.copy(
                        title = tiet_detail_title.text.toString(),
                        text = tiet_detail_text.text.toString()
                    )
                    DetailEvent.SaveNote(note)
                }
        )
    }

    override suspend fun navigate(dest: DetailDestination) {
        when (dest) {
            DetailDestination.Main -> findNavController().navigateUp()
            is DetailDestination.NoteDeleted -> findNavController().navigate(
                DetailFragmentDirections.actionDetailFragmentToMainFragment(NoteDeleted(dest.id))
            )
        }
    }

    private fun render(state: Async<DetailViewState>) {
        when (state) {
            is Success -> {
                tiet_detail_title.setText(state().note?.title.toString())
                tiet_detail_text.setText(state().note?.text.toString())
                b_detail_save.tag = state().note
                b_detail_delete.tag = state().note
            }
            is Fail -> {
                Timber.d("Error: $state")
                Toast.makeText(requireContext(), "Error: $state", Toast.LENGTH_SHORT).show()
            }
            Uninitialized, is Loading -> {
                // Currently do nothing, assuming loading will be instant
            }
        }
    }
}
