package com.example.notesapp.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentAddRecipeBinding
import com.example.notesapp.skeleton.mvvm.BaseMvvmFragment
import com.example.notesapp.skeleton.mvvm.event.RecipeSuccessfullyAddedEvent
import com.example.notesapp.util.AlertDialogCreator
import com.example.notesapp.viewmodel.AddRecipeVM
import kotlinx.android.synthetic.main.fragment_note_list.*
import kotlin.reflect.KClass


/**
 * Created by Richard Gross on 2020-01-13
 */
class AddRecipeFragment : BaseMvvmFragment<FragmentAddRecipeBinding, AddRecipeVM>() {

    override val viewModelClass: KClass<AddRecipeVM> = AddRecipeVM::class
    override val layoutId: Int = R.layout.fragment_add_recipe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subscribe(RecipeSuccessfullyAddedEvent::class, Observer {
            AlertDialogCreator.createDefaultErrorDialog(context!!)
        })

        subscribe(RecipeSuccessfullyAddedEvent::class, Observer {
            AlertDialogCreator.createRecipeSuccessfullyAddedDialog(context!!)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.apply {
            setNavigationOnClickListener { it.findNavController().navigateUp() }
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_add_recipe -> {
                        viewModel.addRecipe()
                        true
                    }
                    else -> super.onOptionsItemSelected(it)
                }
            }
        }

    }
}