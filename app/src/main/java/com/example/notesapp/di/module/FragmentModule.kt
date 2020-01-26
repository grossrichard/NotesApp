package com.example.notesapp.di.module

import androidx.lifecycle.ViewModel
import com.example.notesapp.di.ViewModelBuilder
import com.example.notesapp.di.ViewModelKey
import com.example.notesapp.view.AddRecipeFragment
import com.example.notesapp.view.RecipeDetailFragment
import com.example.notesapp.view.RecipeListFragment
import com.example.notesapp.viewmodel.AddRecipeVM
import com.example.notesapp.viewmodel.RecipeDetailVM
import com.example.notesapp.viewmodel.RecipeListVM
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by Richard Gross on 2020-01-17
 */

@Module

abstract class FragmentModule {

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    abstract fun recipeListFragment(): RecipeListFragment

    @Binds
    @IntoMap
    @ViewModelKey(RecipeListVM::class)
    abstract fun recipeListVM(vm: RecipeListVM): ViewModel

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    abstract fun recipeDetailFragment(): RecipeDetailFragment

    @Binds
    @IntoMap
    @ViewModelKey(RecipeDetailVM::class)
    abstract fun recipeDetailVM(vm: RecipeDetailVM): ViewModel

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    abstract fun addRecipeFragment(): AddRecipeFragment

    @Binds
    @IntoMap
    @ViewModelKey(AddRecipeVM::class)
    abstract fun addRecipeVM(vm: AddRecipeVM): ViewModel
}