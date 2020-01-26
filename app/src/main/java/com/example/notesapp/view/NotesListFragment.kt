package com.example.notesapp.view

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentNoteListBinding
import com.example.notesapp.skeleton.mvvm.BaseMvvmFragment
import com.example.notesapp.util.UiUtils
import com.example.notesapp.viewmodel.NotesListVM
import kotlinx.android.synthetic.main.fragment_note_list.*
import kotlin.reflect.KClass

/**
 * Created by Richard Gross on 2020-01-13
 */
class NotesListFragment : BaseMvvmFragment<FragmentNoteListBinding, NotesListVM>() {

    override val viewModelClass: KClass<NotesListVM> = NotesListVM::class
    override val layoutId: Int = R.layout.fragment_note_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.loadRecipes()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_add -> {
                    Navigation.findNavController(view)
                        .navigate(NotesListFragmentDirections.navigateNotesListToNoteDetail("someId"))
                    true
                }

                R.id.action_remove -> {
                    Navigation.findNavController(view)
                        .navigate(NotesListFragmentDirections.navigateNotesListToNoteDetail("someId"))
                    true
                }

                R.id.action_edit -> {
                    Navigation.findNavController(view)
                        .navigate(NotesListFragmentDirections.navigateNotesListToNoteDetail("someId"))
                    true
                }

                else -> super.onOptionsItemSelected(it)
            }
        }

        rv_recipes.addItemDecoration(DividerItemDecoration(
            context,
            DividerItemDecoration.VERTICAL
        ).apply { setDrawable(UiUtils.getDrawable(R.drawable.item_list_divider)!!) })
    }
}