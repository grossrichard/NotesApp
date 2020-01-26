package com.example.notesapp.view

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentRecipeDetailBinding
import com.example.notesapp.skeleton.mvvm.BaseMvvmFragment
import com.example.notesapp.util.UiUtils
import com.example.notesapp.viewmodel.RecipeDetailVM
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_recipe_detail.*
import kotlin.math.abs
import kotlin.reflect.KClass

/**
 * Created by Richard Gross on 2020-01-13
 */
class RecipeDetailFragment : BaseMvvmFragment<FragmentRecipeDetailBinding, RecipeDetailVM>() {

    override val viewModelClass: KClass<RecipeDetailVM> = RecipeDetailVM::class
    override val layoutId: Int = R.layout.fragment_recipe_detail

    private var appBarCollapsed: Boolean = false
    private var menuAddRecipe: MenuItem? = null
    private val colorFilter =
        PorterDuffColorFilter(UiUtils.getColor(R.color.blue), PorterDuff.Mode.SRC_ATOP)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            RecipeDetailFragmentArgs.fromBundle(it).recipeId?.let(viewModel::loadRecipeDetail)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_recipe_detail, menu)
        menuAddRecipe = menu.findItem(R.id.action_add_recipe)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
    }

    private fun initToolbar() {
        collapsing_toolbar.apply {
            (activity as AppCompatActivity).setSupportActionBar(this)
            title = ""
            setNavigationOnClickListener { it.findNavController().navigateUp() }
            setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_add_recipe -> {
                        Navigation.findNavController(view!!)
                            .navigate(RecipeDetailFragmentDirections.navigateRecipeDetailToAddRecipe())
                        true
                    }
                    else -> super.onOptionsItemSelected(item)
                }
            }
        }

        app_bar.addOnOffsetChangedListener(
            AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                val totalScrolled = abs(verticalOffset) == appBarLayout.totalScrollRange

                if (appBarCollapsed != totalScrolled) {
                    appBarCollapsed = totalScrolled

                    if (appBarCollapsed) {
                        menuAddRecipe?.icon?.colorFilter = colorFilter
                        collapsing_toolbar.navigationIcon?.colorFilter = colorFilter
                        toolbar_layout.title = viewModel.name.value
                    } else {
                        menuAddRecipe?.icon?.clearColorFilter()
                        collapsing_toolbar.navigationIcon?.clearColorFilter()
                        toolbar_layout.title = ""
                    }
                }
            })

        setHasOptionsMenu(true)
    }
}
