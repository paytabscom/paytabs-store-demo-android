package com.example.paytabs_demo_store_android.orders.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.paytabs_demo_store_android.databinding.FragmentOrdersBinding
import com.example.paytabs_demo_store_android.orders.view.adapter.OrdersAdapter
import com.example.paytabs_demo_store_android.orders.viewmodel.OrdersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class OrdersFragment : Fragment() {


    private val viewModel: OrdersViewModel by viewModels()
    private lateinit var binding: FragmentOrdersBinding
    private val adapter = OrdersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.getOrders()
        binding = FragmentOrdersBinding.inflate(inflater)
        initRV()
        observeViewModel()
        return binding.root
    }


    private fun observeViewModel() {
        viewModel.orders.observe(viewLifecycleOwner, {
            adapter.setOrders(it)
        })


    }

    private fun initRV() {
        binding.rvOrders.adapter=adapter
    }

}