package com.example.paytabs_demo_store_android.home.view

import android.net.sip.SipSession
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.paytabs_demo_store_android.R
import com.example.paytabs_demo_store_android.databinding.FragmentHomeBinding
import com.example.paytabs_demo_store_android.onboarding.view.hide
import com.example.paytabs_demo_store_android.onboarding.view.shouldShow
import com.example.paytabs_demo_store_android.products.model.remote.ProductsService
import com.example.paytabs_demo_store_android.products.model.repo.ProductsRepository
import com.example.paytabs_demo_store_android.products.model.response.Product
import com.example.paytabs_demo_store_android.products.view.adapter.ProductAdapter
import com.example.paytabs_demo_store_android.products.viewmodel.ProductsViewModel
import com.example.paytabs_demo_store_android.products.viewmodel.ProductsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlin.random.Random
@AndroidEntryPoint

class HomeFragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding

    private lateinit var homeViewModel: ProductsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initViewModel()
        binding = FragmentHomeBinding.inflate(inflater)
        homeViewModel.getAllProducts()
        observeViewModel()
        return binding.root
    }


    private fun initViewModel() {
        val retrofitService = ProductsService.getInstance()
        val mainRepository = ProductsRepository(retrofitService)


        homeViewModel = ViewModelProvider(this, ProductsViewModelFactory(mainRepository)).get(
            ProductsViewModel::class.java
        )
    }


    private fun observeViewModel() {
        homeViewModel.productList.observe(viewLifecycleOwner, {
            renderRv(binding.rvBestSelling, getRandomProducts(5, it))

            renderRv(binding.rvOffers, getRandomProducts(4, it))

            renderRv(binding.rvFeatured, getRandomProducts(8, it))

            renderRv(binding.rvForYou, getRandomProducts(6, it))

        })

        homeViewModel.errorMessage.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
        homeViewModel.networkErrorLD.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), "Network Error", Toast.LENGTH_SHORT).show()
        })
        homeViewModel.serverErrorLD.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), "Server Error", Toast.LENGTH_SHORT).show()
        })

        homeViewModel.loading.observe(viewLifecycleOwner, {
            binding.progressDialog.shouldShow(it)
        })
    }

    private fun getRandomProducts(size: Int, it: List<Product>): List<Product> {
        val startIndex1 = Random.nextInt(0, it.size - size)
        val list1 = it.subList(startIndex1, startIndex1 + size)
        return list1
    }

    private fun renderRv(rv: RecyclerView, list: List<Product>) {
        val adapter = ProductAdapter { navigateToDetails(it) }
        adapter.setProducts(list)
        rv.adapter = adapter
    }

    private fun navigateToDetails(Details: String) {

        findNavController().navigate(
            R.id.action_navigation_home_to_product_details_fragment,
            bundleOf("Details" to Details)
        )
    }
}