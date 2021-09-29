package com.acutisbits.asosspacex.coreui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.acutisbits.asosspacex.navigation.BackPropagatingFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BaseFragment<ViewState : Any, Binding : ViewBinding>(
    private val bindingInflater: (LayoutInflater) -> Binding
) : Fragment(), BackPropagatingFragment {

    abstract val model: BaseViewModel<ViewState>

    private var _binding: Binding? = null

    protected val binding: Binding
        get() = _binding!!

    protected abstract fun Binding.render(viewState: ViewState)

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = bindingInflater(layoutInflater)
        return binding.root
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root.isClickable = true // To avoid clicks passing through

        binding.initialiseView(view, savedInstanceState)
    }

    /** Override to initialise view */
    protected open fun Binding.initialiseView(view: View, savedInstanceState: Bundle?) {
        // Template
    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        model.viewStates().forEach { viewBoundJob { it.collect { binding.render(it) } } }
    }

    override fun back() = false

    protected fun viewBoundJob(job: suspend () -> Unit) = viewLifecycleOwner.lifecycleScope.launch { job() }

    protected fun setStatusBarColor(@ColorRes color: Int) {
        context?.let {
            activity?.window?.statusBarColor = ContextCompat.getColor(it, color)
        }
    }
}
