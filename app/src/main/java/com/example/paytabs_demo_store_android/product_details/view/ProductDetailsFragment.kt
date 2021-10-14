package com.example.paytabs_demo_store_android.product_details.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.paytabs_demo_store_android.R
import com.example.paytabs_demo_store_android.database.enities.toFavouriteEntity
import com.example.paytabs_demo_store_android.databinding.FragmentProductDetailsBinding
import com.example.paytabs_demo_store_android.di.DataBaseProvider
import com.example.paytabs_demo_store_android.onboarding.view.shouldShow
import com.example.paytabs_demo_store_android.product_details.model.remote.ProductDetailsService
import com.example.paytabs_demo_store_android.product_details.model.repo.ProductDetailsRepository
import com.example.paytabs_demo_store_android.product_details.viewmodel.ProductDetailsViewModel
import com.example.paytabs_demo_store_android.products.model.response.Product
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class ProductDetailsFragment : Fragment() {

    private  val viewModel: ProductDetailsViewModel by viewModels()
    private lateinit var binding: FragmentProductDetailsBinding

    var productdetails :Product?= null

    fun setProducts(products: Product) {
        this.productdetails = products
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailsBinding.inflate(inflater)
        var id=arguments?.getString("Details")
        if (id != null) {
            viewModel.getProductDetails(id)
            viewModel.productExists(id.toInt())
            viewModel.productExistsFav(id.toInt())
        }
        onClickActions()
        observeViewModel()
        return binding.root
    }

    private fun onClickActions() {

        binding.bnAddToCard.setOnClickListener {
            if(viewModel.isExist){
                productdetails?.let { it1 -> viewModel.increaseCount(it1.id) }
            }
            else{
                viewModel.addToBag()
                viewModel.isExist=true
            }

        }

        binding.ivFav.setOnClickListener{

            if(viewModel.isExistFav){
                productdetails?.let { it -> viewModel.deleteFavProduct(it)
                    viewModel.isExistFav=false
                    binding.ivFav.setImageResource(R.drawable.ic_heart)}
            }
            else{
                viewModel.addToFav()
                viewModel.isExistFav=true
                binding.ivFav.setImageResource(R.drawable.ic_red_heart)
            }

        }


    }

    private fun observeViewModel() {
        viewModel.productDetails.observe(viewLifecycleOwner, {
            setProducts(it)
            Glide.with(this).load(productdetails?.image).into(binding.ivProduct)
            binding.tvProductTitle.text= productdetails?.title
            binding.tvProductPrice.text= productdetails?.price.toString() + "$"
            binding.tvProductDescription.text= productdetails?.description
            if(viewModel.isExistFav){
                binding.ivFav.setImageResource(R.drawable.ic_red_heart)
            }
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
            binding.progressBar.shouldShow(it)
        })
    }



}

