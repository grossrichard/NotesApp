package com.example.notesapp.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentNoteDetailBinding
import com.example.notesapp.entity.NoteDetailMode
import com.example.notesapp.entity.NoteDetailMode.*
import com.example.notesapp.skeleton.mvvm.BaseMvvmFragment
import com.example.notesapp.viewmodel.NoteDetailVM
import kotlinx.android.synthetic.main.fragment_note_list.*
import kotlin.reflect.KClass


/**
 * Created by Richard Gross on 2020-01-13
 */
class NoteDetailFragment : BaseMvvmFragment<FragmentNoteDetailBinding, NoteDetailVM>() {

    override val viewModelClass: KClass<NoteDetailVM> = NoteDetailVM::class
    override val layoutId: Int = R.layout.fragment_note_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            viewModel.mode.value = NoteDetailFragmentArgs.fromBundle(it).mode
            viewModel.id = NoteDetailFragmentArgs.fromBundle(it).id
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_detail, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val visibility = viewModel.mode.value == READ
        menu.setGroupVisible(R.id.group_menu_detail_default, visibility)
        menu.findItem(R.id.action_confirm).isVisible = !visibility
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.apply {
            (activity as AppCompatActivity).setSupportActionBar(this)
            setNavigationOnClickListener { it.findNavController().navigateUp() }
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_edit -> {
                        updateMode(EDIT)
                        viewModel.editNote()
                        true
                    }

                    R.id.action_create -> {
                        updateMode(CREATE)
                        viewModel.editNote()
                        true
                    }

                    R.id.action_remove -> {
                        updateMode(DELETE)
                        viewModel.deleteNote()
                        true
                    }

                    R.id.action_confirm -> {
                        updateMode(EDIT)
                        viewModel.editNote()
                        true
                    }
                    else -> super.onOptionsItemSelected(it)
                }
            }
        }

        setHasOptionsMenu(true)
    }

    private fun updateMode(mode: NoteDetailMode) {
        viewModel.mode.value = mode
        (activity as AppCompatActivity).invalidateOptionsMenu()
    }

}
