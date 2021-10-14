package com.example.paytabs_demo_store_android.favourite.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.paytabs_demo_store_android.database.enities.FavouriteEntity
import com.example.paytabs_demo_store_android.databinding.FragmentFavoritesBinding
import com.example.paytabs_demo_store_android.favourite.view.adapter.FavAdapter
import com.example.paytabs_demo_store_android.favourite.viewmodel.FavViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class FavoritesFragment : Fragment() {


    private  val viewModel: FavViewModel by viewModels()
    private lateinit var binding: FragmentFavoritesBinding
    private val adapter = FavAdapter({deleteProduct(it)} )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.getFavProducts()
        binding = FragmentFavoritesBinding.inflate(inflater)
        initRV()
        observeViewModel()
        return binding.root
    }




    private fun observeViewModel() {
        viewModel.favProducts.observe(viewLifecycleOwner, {
            adapter.setProducts(it)
        })


    }

    private fun initRV() {
        binding.rvFav.adapter=adapter
    }

    private fun deleteProduct(product: FavouriteEntity) {
        viewModel.deleteFavProduct(product)
    }

}