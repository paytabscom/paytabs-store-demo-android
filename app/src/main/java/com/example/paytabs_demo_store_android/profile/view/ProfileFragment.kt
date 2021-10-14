package com.example.paytabs_demo_store_android.profile.view



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.paytabs_demo_store_android.R
import com.example.paytabs_demo_store_android.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater)
        binding.tvOrders.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_orders_bag)
        }
        return binding.root
    }
}