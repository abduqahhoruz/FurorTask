package com.example.furortask.presentation.ui.catalog.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.furortask.R
import com.example.furortask.business.data.remote.RetrofitClient
import com.example.furortask.business.util.GridSpacingItemDecoration
import com.example.furortask.business.util.NetworkStatus
import com.example.furortask.business.util.progressDialog
import com.example.furortask.databinding.FragmentFirstBinding
import com.example.furortask.framework.repo.MainRepo
import com.example.furortask.framework.viewmodel.ProductTypeViewModel
import com.example.furortask.framework.viewmodel.ProductTypeViewModelFactory
import com.example.furortask.presentation.adapter.CategoryAdapter
import com.example.furortask.presentation.adapter.SpecialityClickListener
import com.example.furortask.presentation.ui.catalog.model.GetProductTypeModelLocal


class CatalogFragment : Fragment(), SpecialityClickListener {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private var name: String? = ""
    private lateinit var viewModel: ProductTypeViewModel
    private val pAdapter: CategoryAdapter by lazy { CategoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val api = RetrofitClient.instance
        viewModel = ViewModelProvider(
            requireActivity(),
            ProductTypeViewModelFactory(
                requireActivity().application,
                MainRepo(api)
            )
        )[ProductTypeViewModel::class.java]
        viewModel.getSpeciality()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setUpObserver()
    }

    private val specialityObserver = Observer<NetworkStatus<List<GetProductTypeModelLocal>>> {
        when (it) {
            is NetworkStatus.LOADING -> {

                 progressDialog?.show()
                Log.i("NetworkStatus", "Loading: ${it}")
            }
            is NetworkStatus.SUCCESS -> {
                progressDialog?.hide()
                Log.i("NetworkStatus", "Succes: ${it.data}")
                 pAdapter.submitList(it.data)
            }
            is NetworkStatus.ERROR -> {
                progressDialog?.hide()
                Log.i("NetworkStatus", "Error: ${it.error}")
            }
        }
    }

    private fun setUpObserver() {
        viewModel.liveSpecialityState.observe(viewLifecycleOwner, specialityObserver)
    }

    private fun setupView() {
        pAdapter.itemClickListener(this)
        binding.rvCategory.adapter = pAdapter
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvCategory.layoutManager = layoutManager
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.cardview_default_elevation)
        binding.rvCategory.addItemDecoration(
            GridSpacingItemDecoration(
                2,
                spacingInPixels,
                true,
                0
            )
        )

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClicked(model: GetProductTypeModelLocal) {
        Toast.makeText(requireContext(), model.nameUz, Toast.LENGTH_SHORT).show()
        // val bundle = bundleOf("id" to model.id, "title" to model.name)
        //findNavController().navigate(R.id.action_shifokorFragment_to_doctorsFragment, bundle)
    }
}