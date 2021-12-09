package com.example.paytabs_demo_store_android.bag.view

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.paytabs_demo_store_android.R
import com.example.paytabs_demo_store_android.bag.view.adapter.BagProductsAdapter
import com.example.paytabs_demo_store_android.bag.viewmodel.BagViewModel
import com.example.paytabs_demo_store_android.database.dao.BagDao
import com.example.paytabs_demo_store_android.database.dao.OrdersDao
import com.example.paytabs_demo_store_android.database.enities.OrdersEntity
import com.example.paytabs_demo_store_android.databinding.FragmentBagBinding
import com.example.paytabs_demo_store_android.products.model.response.Product
import com.example.paytabs_demo_store_android.utils.GoToHomeListener
import com.payment.paymentsdk.PaymentSdkActivity
import com.payment.paymentsdk.PaymentSdkConfigBuilder
import com.payment.paymentsdk.integrationmodels.*
import com.payment.paymentsdk.sharedclasses.interfaces.CallbackPaymentInterface
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


private const val TAG = "BagFragment"

@AndroidEntryPoint
class BagFragment : Fragment(R.layout.fragment_bag), CallbackPaymentInterface, GoToHomeListener {

    private var token: String? = null
    private var transRef: String? = null

    @Inject
    lateinit var bagDao: BagDao

    @Inject
    lateinit var ordersDao: OrdersDao
    var totalPrice: Double = 0.0
    private val viewModel: BagViewModel by viewModels()
    private var _binding: FragmentBagBinding? = null
    private val binding
        get() = _binding!!
    private val adapter = BagProductsAdapter(
        { deleteProduct(it) },
        { productId -> increaseItemCount(productId) },
        { productId -> decreaseItemCount(productId) }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBagBinding.bind(view)
        viewModel.getBagProducts()
        initRV()
        observeViewModel()
        onClickActions()
    }

    private fun onClickActions() {
        binding.btnCheckOut.setOnClickListener {
            val configData = generatePaytabsConfigurationDetails()
            PaymentSdkActivity.startCardPayment(requireActivity(), configData, this)
        }
    }


    private fun observeViewModel() {
        viewModel.bagProducts.observe(viewLifecycleOwner, {
            adapter.setProducts(it)
            totalPrice = adapter.getTotalPrice(it).toDouble()
            binding.tvTotalPrice.text = "Total price $totalPrice$"
        })
    }

    private fun initRV() {
        binding.rvBag.adapter = adapter
    }

    private fun deleteProduct(product: Product) {
        viewModel.deleteBagProduct(product)
    }

    private fun increaseItemCount(productId: Int) {
        viewModel.increaseCount(productId)
    }

    private fun decreaseItemCount(productId: Int) {
        viewModel.decreaseCount(productId)
    }


    private fun generatePaytabsConfigurationDetails(): PaymentSdkConfigurationDetails {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        //Place your own merchant data in the settings screen
        val profileId = prefs.getString("profile_id", "") ?: ""
        val serverKey: String = prefs.getString("server_key", "") ?: ""
        val clientKey = prefs.getString("client_key", "") ?: ""
        val currencyCode = prefs.getString("currency_code", "") ?: ""
        val countryCode = prefs.getString("country_code", "") ?: ""
        val billingData = PaymentSdkBillingDetails(
            "City",
            countryCode,
            "email1@domain.com",
            "name ",
            "01210656660", "state",
            "address street", "11223"
        )

        val shippingData = PaymentSdkShippingDetails(
            "City",
            countryCode,
            "email1@domain.com",
            "Khaled",
            "01210656670", "Cairo",
            "address street", "12345"
        )
        val configData = PaymentSdkConfigBuilder(
            profileId,
            serverKey,
            clientKey,
            totalPrice,
            currencyCode
        )
            .setCartDescription("cartDesc")
            .setLanguageCode(PaymentSdkLanguageCode.EN)
            .setMerchantCountryCode(countryCode)
            .setTransactionType(PaymentSdkTransactionType.SALE)
            .setTransactionClass(PaymentSdkTransactionClass.ECOM)
            .setCartId("12")
            .setBillingData(billingData)
            .setShippingData(shippingData)
            .setScreenTitle("Pay with Card")


        return configData.build()
    }


    override fun onError(error: PaymentSdkError) {
        Toast.makeText(requireActivity(), "${error.msg}", Toast.LENGTH_SHORT).show()
    }

    override fun onPaymentCancel() {
        Toast.makeText(requireActivity(), "onPaymentCancel", Toast.LENGTH_SHORT).show()

    }

    override fun onPaymentFinish(paymentSdkTransactionDetails: PaymentSdkTransactionDetails) {
        token = paymentSdkTransactionDetails.token
        transRef = paymentSdkTransactionDetails.transactionReference
        val currentTime: Date = Calendar.getInstance().time
        val order = OrdersEntity("John", totalPrice, currentTime.toString(), "Authorized")
        Toast.makeText(
            requireActivity(),
            "${paymentSdkTransactionDetails.paymentResult?.responseMessage ?: "PaymentFinish"}",
            Toast.LENGTH_SHORT
        ).show()
        viewModel.deleteAllBagProducts()
        CoroutineScope(Dispatchers.IO).launch {
            ordersDao.addOrder(order)
        }
        showSuccessDialog()
    }

    private fun showSuccessDialog() {
        PaymentSuccessDialog(requireContext(), this).show()
    }

    override fun onClick() {
        findNavController().popBackStack()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}