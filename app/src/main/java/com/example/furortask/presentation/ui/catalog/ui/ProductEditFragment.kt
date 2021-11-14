package com.example.furortask.presentation.ui.catalog.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.furortask.business.data.remote.RetrofitClient
import com.example.furortask.databinding.FragmentSecondBinding
import com.example.furortask.databinding.ProductEditFragmentBinding
import com.example.furortask.framework.repo.MainRepo
import com.example.furortask.framework.viewmodel.ProductEditViewModel
import com.example.furortask.framework.viewmodel.ProductTypeViewModelFactory
import com.example.furortask.framework.viewmodel.ProductViewModel
import com.example.furortask.framework.viewmodel.PutProductFragmnetViewModel

class ProductEditFragment : Fragment() {
    private var _binding: ProductEditFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ProductEditViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*val api = RetrofitClient.instanceApi()
        viewModel = ViewModelProvider(
            requireActivity(),
            ProductTypeViewModelFactory(
                requireActivity().application,
                MainRepo(api)
            )
        )[PutProductFragmnetViewModel::class.java]*/
        _binding = ProductEditFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductEditViewModel::class.java)
        // TODO: Use the ViewModel
    }

}