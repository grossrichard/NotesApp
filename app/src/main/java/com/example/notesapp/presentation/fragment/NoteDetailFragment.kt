package com.example.notesapp.presentation.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentNoteDetailBinding
import com.example.notesapp.entity.NoteDetailMode
import com.example.notesapp.entity.NoteDetailMode.*
import com.example.notesapp.skeleton.mvvm.BaseMvvmFragment
import com.example.notesapp.skeleton.mvvm.event.MessageEvent
import com.example.notesapp.presentation.util.AlertDialogCreator
import com.example.notesapp.presentation.viewmodel.NoteDetailVM
import kotlinx.android.synthetic.main.fragment_note_list.*
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass


/**
 * Created by Richard Gross on 2020-01-13
 */
class NoteDetailFragment : BaseMvvmFragment<FragmentNoteDetailBinding, NoteDetailVM>() {

    override val viewModelClass: KClass<NoteDetailVM> = NoteDetailVM::class
    override val layoutId: Int = R.layout.fragment_note_detail
    override val viewModel by viewModel<NoteDetailVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            viewModel.mode.value = NoteDetailFragmentArgs.fromBundle(it).mode
            viewModel.id = NoteDetailFragmentArgs.fromBundle(it).id
        }

        subscribe(MessageEvent::class, Observer {
            AlertDialogCreator.createMessageDialog(requireContext(), it.messageRes)
            Navigation.findNavController(requireView()).navigateUp()
        })
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
                        true
                    }

                    R.id.action_create -> {
                        updateMode(CREATE)
                        true
                    }

                    R.id.action_remove -> {
                        updateMode(DELETE)
                        true
                    }

                    R.id.action_confirm -> {
                        doOnConfirm()
                        true
                    }
                    else -> super.onOptionsItemSelected(it)
                }
            }
        }

        setHasOptionsMenu(true)
    }

    private fun doOnConfirm() {
        when (viewModel.mode.value) {
            EDIT -> viewModel.editNote()
            CREATE -> viewModel.createNote()
            DELETE -> viewModel.deleteNote()
        }
    }

    private fun updateMode(mode: NoteDetailMode) {
        viewModel.mode.value = mode
        (activity as AppCompatActivity).invalidateOptionsMenu()
    }

}
