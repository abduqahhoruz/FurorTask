package com.example.furortask.presentation.ui.catalog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.furortask.business.data.remote.RetrofitClient
import com.example.furortask.business.data.remote.model.PutProductRequstModel
import com.example.furortask.databinding.ProductPutFramgnetFragmentBinding
import com.example.furortask.framework.repo.MainRepo
import com.example.furortask.framework.viewmodel.PutProductFragmnetViewModel
import com.example.furortask.framework.viewmodel.PutProductViewModelFactory

class PutProductFragment : Fragment() {

    private var _binding: ProductPutFramgnetFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: PutProductFragmnetViewModel

    private lateinit var viewModelProduct: PutProductFragmnetViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val api = RetrofitClient.instanceApi()
        viewModel = ViewModelProvider(
            requireActivity(),
            PutProductViewModelFactory(
                requireActivity().application,
                MainRepo(api)
            )
        )[PutProductFragmnetViewModel::class.java]


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProductPutFramgnetFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addProduct.setOnClickListener{
            val mymodel = PutProductRequstModel(
                binding.etAddress.text.toString(),
                System.currentTimeMillis().toInt(),
                binding.etCreatedDate.text.toString(),
                System.currentTimeMillis().toInt(),
                binding.etAddress.text.toString(),
                productTypeId = 0

            )
            viewModel.putProduct(
               mymodel
            )
        }
    }

}