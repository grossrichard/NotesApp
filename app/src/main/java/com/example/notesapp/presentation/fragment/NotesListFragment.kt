package com.example.notesapp.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentNoteListBinding
import com.example.notesapp.presentation.entity.NoteDetailMode
import com.example.notesapp.skeleton.mvvm.BaseMvvmFragment
import com.example.notesapp.presentation.util.UiUtils
import com.example.notesapp.presentation.viewmodel.NotesListVM
import kotlinx.android.synthetic.main.fragment_note_list.*
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass


/**
 * Created by Richard Gross on 2020-01-13
 */
class NotesListFragment : BaseMvvmFragment<FragmentNoteListBinding, NotesListVM>() {

    override val viewModelClass: KClass<NotesListVM> = NotesListVM::class
    override val layoutId: Int = R.layout.fragment_note_list
    override val viewModel by viewModel<NotesListVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.loadNotes()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_add -> {
                    Navigation.findNavController(view)
                        .navigate(NotesListFragmentDirections.navigateNotesListToNoteDetail(mode = NoteDetailMode.CREATE))
                    true
                }

                R.id.action_refresh -> {
                    viewModel.loadNotes()
                    true
                }

                else -> super.onOptionsItemSelected(it)
            }
        }

        rv_notes.addItemDecoration(DividerItemDecoration(
            context,
            DividerItemDecoration.VERTICAL
        ).apply { setDrawable(UiUtils.getDrawable(R.drawable.item_list_divider)!!) })
    }
}