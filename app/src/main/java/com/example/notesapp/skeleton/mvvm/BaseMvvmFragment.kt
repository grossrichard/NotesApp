package com.example.notesapp.skeleton.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.notesapp.BR
import com.example.notesapp.presentation.util.AlertDialogCreator
import com.example.notesapp.skeleton.mvvm.event.GenericErrorEvent
import com.example.notesapp.skeleton.mvvm.event.LiveEvent
import com.example.notesapp.skeleton.mvvm.event.NavigateEvent
import com.example.notesapp.skeleton.mvvm.event.NoInternetAvailableEvent
import kotlin.reflect.KClass

/**
 * Created by Richard Gross on 2020-01-13
 */
abstract class BaseMvvmFragment<B : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    protected lateinit var binding: B
    abstract val viewModel: VM
    protected abstract val viewModelClass: KClass<VM>
    protected abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(viewModel)
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
            AlertDialogCreator.createDefaultErrorDialog(requireContext())
        })
        subscribe(NoInternetAvailableEvent::class, Observer {
            AlertDialogCreator.createNoInternetDialog(requireContext())
        })
    }

    protected fun onNavigateEvent(evt: NavigateEvent) {
        Navigation.findNavController(requireView()).navigate(evt.direction)
    }
}