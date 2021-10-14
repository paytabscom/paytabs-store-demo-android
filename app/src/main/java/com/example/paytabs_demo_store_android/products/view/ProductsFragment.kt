package com.example.paytabs_demo_store_android.products.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.paytabs_demo_store_android.R
import com.example.paytabs_demo_store_android.categories.view.adapter.CategoriesAdapter
import com.example.paytabs_demo_store_android.products.view.adapter.ProductAdapter
import com.example.paytabs_demo_store_android.databinding.FragmentProductBinding
import com.example.paytabs_demo_store_android.onboarding.view.shouldShow
import com.example.paytabs_demo_store_android.products.model.remote.ProductsService
import com.example.paytabs_demo_store_android.products.model.repo.ProductsRepository
import com.example.paytabs_demo_store_android.products.viewmodel.ProductsViewModel
import com.example.paytabs_demo_store_android.products.viewmodel.ProductsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : Fragment() {


    private val viewModel: ProductsViewModel by viewModels()
    private val adapter = ProductAdapter {
        navigateToDetails(it)
    }

    lateinit var binding: FragmentProductBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductBinding.inflate(inflater)
        var category=arguments?.getString("category")
        if (category != null) {
            viewModel.getProductsByCategory(category)
        } else
            viewModel.getAllProducts()

        observeViewModel()
        return binding.root
    }


    private fun observeViewModel() {
        viewModel.productList.observe(viewLifecycleOwner, {
            adapter.setProducts(it)
            binding.recyclerview.adapter = adapter

        })

        viewModel.errorMessage.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
        viewModel.networkErrorLD.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), "Network Error", Toast.LENGTH_SHORT).show()
        })
        viewModel.serverErrorLD.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), "Server Error", Toast.LENGTH_SHORT).show()
        })

        viewModel.loading.observe(viewLifecycleOwner, {
            binding.progressDialog.shouldShow(it)
        })
    }



    private fun navigateToDetails(Details: String) {

        findNavController().navigate(
            R.id.action_navigation_Products_to_product_details_fragment,
            bundleOf("Details" to Details)
        )
    }

}