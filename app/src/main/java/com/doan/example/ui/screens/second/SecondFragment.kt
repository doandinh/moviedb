package com.doan.example.ui.screens.second

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.doan.example.R
import com.doan.example.databinding.FragmentSecondBinding
import com.doan.example.extensions.provideNavArgs
import com.doan.example.extensions.provideViewModels
import com.doan.example.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondFragment : BaseFragment<FragmentSecondBinding>() {

    private val viewModel: SecondViewModel by provideViewModels()
    private val args: SecondFragmentArgs by provideNavArgs()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSecondBinding
        get() = { inflater, container, attachToParent ->
            FragmentSecondBinding.inflate(inflater, container, attachToParent)
        }

    override fun setupView() {
        // Hide navigation button on toolbar
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
            setHomeButtonEnabled(false)
        }
    }

    override fun initViewModel() {
        viewModel.initViewModel(args.uiModel)
    }

    override fun bindViewModel() {
        viewModel.id bindTo ::displayId
    }

    private fun displayId(id: Long?) {
        binding.tvSecondId.text = getString(R.string.second_id_title, id?.toString())
    }
}
