package com.toolstodo.ecommerceapp.ui.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.toolstodo.ecommerceapp.databinding.FragmentHomeBinding
import com.toolstodo.ecommerceapp.domain.model.product.Product
import com.toolstodo.ecommerceapp.ui.view.home.recycleradapters.offers.OffersAdapter
import com.toolstodo.ecommerceapp.ui.view.home.recycleradapters.product.ProductAdapter
import com.toolstodo.ecommerceapp.ui.view.home.viewmodel.HomeViewmodel
import com.toolstodo.ecommerceapp.utils.ScreenCalculator
import dagger.hilt.android.AndroidEntryPoint

private const val UNDER_DISCOUNT = 5

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewmodel by viewModels()

    private lateinit var offersList: List<Product>
    private lateinit var offersAdapter: OffersAdapter
    private lateinit var productList: List<Product>
    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
    }

    private fun initComponents() {
        offersList = emptyList()
        offersAdapter = OffersAdapter(offersList, onOfferClick = { onProductClick(it) })

        productList = emptyList()
        productAdapter = ProductAdapter(productList, onProductClick = { onProductClick(it) })

        initUI()
        initListeners()
        initObservers()
    }

    private fun initUI() {

        with(binding) {
            rvOffers.adapter = offersAdapter
            rvOffers.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            rvProducts.adapter = productAdapter

            rvProducts.layoutManager =
                GridLayoutManager(
                    requireContext(),
                    ScreenCalculator.calculateNoOfColumns(
                        requireContext(),
                        ScreenCalculator.DP_COLUMNS
                    )
                )
        }

        viewModel.getProducts(0, 100)
    }

    private fun initListeners() {
        with(binding) {
            svSearcher.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    val goToSearch =
                        HomeFragmentDirections.actionHomeFragmentToSearchFragment(query.toString())
                    findNavController().navigate(goToSearch)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean = false
            })
        }
    }

    private fun initObservers() {
        viewModel.productState.observe(viewLifecycleOwner) { infoResponse ->
            productAdapter.updateList(infoResponse.products)
            if (infoResponse.products.isNotEmpty()) {
                val maxDiscount = infoResponse.products.maxOf { it.discountPercentage }
                offersAdapter.updateList(infoResponse.products.filter { product ->
                    product.discountPercentage >= maxDiscount - UNDER_DISCOUNT && product.discountPercentage <= maxDiscount
                })
            }
        }

        viewModel.errorState.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
    }

    private fun onProductClick(product: Product) {
        val goToProductView =
            HomeFragmentDirections.actionHomeFragmentToProductFragment(product)
        findNavController().navigate(goToProductView)
    }


}