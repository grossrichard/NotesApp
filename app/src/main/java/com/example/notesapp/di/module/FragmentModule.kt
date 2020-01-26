package com.example.notesapp.di.module

import androidx.lifecycle.ViewModel
import com.example.notesapp.di.ViewModelBuilder
import com.example.notesapp.di.ViewModelKey
import com.example.notesapp.view.NoteDetailFragment
import com.example.notesapp.view.NotesListFragment
import com.example.notesapp.viewmodel.NoteDetailVM
import com.example.notesapp.viewmodel.NotesListVM
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
    abstract fun recipeListFragment(): NotesListFragment

    @Binds
    @IntoMap
    @ViewModelKey(NotesListVM::class)
    abstract fun recipeListVM(vm: NotesListVM): ViewModel

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    abstract fun recipeDetailFragment(): NoteDetailFragment

    @Binds
    @IntoMap
    @ViewModelKey(NoteDetailVM::class)
    abstract fun recipeDetailVM(vm: NoteDetailVM): ViewModel
}