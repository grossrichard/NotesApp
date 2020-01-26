package com.example.notesapp.skeleton.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.notesapp.BR
import com.example.notesapp.skeleton.mvvm.event.*
import com.example.notesapp.util.AlertDialogCreator
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import kotlin.reflect.KClass

/**
 * Created by Richard Gross on 2020-01-13
 */
abstract class BaseMvvmFragment<B : ViewDataBinding, VM : BaseViewModel> : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    protected lateinit var binding: B
    protected lateinit var viewModel: VM
    protected abstract val viewModelClass: KClass<VM>?

    protected abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModelClass?.let {
            viewModel = ViewModelProviders.of(this, viewModelFactory).get(it.java)
            lifecycle.addObserver(viewModel)
        }

        subscribeEvents()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        initBinding(binding)
        return binding.root
    }

    protected open fun initBinding(binding: B) {
        binding.setVariable(BR.vm, viewModel)
    }

    override fun onDestroy() {
        lifecycle.removeObserver(viewModel)
        super.onDestroy()
    }

    protected fun <T : LiveEvent> subscribe(eventClass: KClass<T>, eventObserver: Observer<T>) {
        viewModel.subscribe(this, eventClass, eventObserver)
    }

    protected open fun subscribeEvents() {
        subscribe(NavigateEvent::class, Observer { onNavigateEvent(it) })
        subscribe(GenericErrorEvent::class, Observer {
            AlertDialogCreator.createDefaultErrorDialog(context!!)
        })
        subscribe(NoInternetAvailableEvent::class, Observer {
            AlertDialogCreator.createNoInternetDialog(context!!)
        })
    }

    protected fun onNavigateEvent(evt: NavigateEvent) {
        Navigation.findNavController(view!!).navigate(evt.direction)
    }
}