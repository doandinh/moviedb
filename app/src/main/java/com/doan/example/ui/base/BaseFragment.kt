package com.doan.example.ui.base

import android.os.Bundle
import android.view.*
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
import com.doan.example.extensions.hideSoftKeyboard
import com.doan.example.ui.common.Toaster
import com.doan.example.ui.userReadableMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseFragment<VB : ViewBinding> : Fragment(), BaseFragmentCallbacks {

    @Inject
    lateinit var toaster: Toaster

    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    private var _binding: ViewBinding? = null

    @Suppress("UNCHECKED_CAST")
    val binding: VB?
        get() = _binding as? VB

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (this as? BaseFragmentCallbacks)?.let { initViewModel() }
    }

    override fun initViewModel() {}

    override fun setupView() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return bindingInflater.invoke(inflater, container, false).apply {
            _binding = this
        }.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (this as? BaseFragmentCallbacks)?.let {
            setupView()
            bindViewEvents()
            bindViewModel()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    @CallSuper
    override fun bindViewEvents() {
        requireNotNull(view).setOnClickListener {
            requireActivity().hideSoftKeyboard()
        }
    }

    open fun displayError(error: Throwable) {
        val message = error.userReadableMessage(requireContext())
        toaster.display(message)
    }

    protected inline infix fun <T> Flow<T>.bindTo(crossinline action: (T) -> Unit) {
        with(viewLifecycleOwner) {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    collect { action(it) }
                }
            }
        }
    }
}
