package com.toolstodo.ecommerceapp.ui.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.toolstodo.ecommerceapp.databinding.FragmentSearchBinding
import com.toolstodo.ecommerceapp.domain.model.product.Product
import com.toolstodo.ecommerceapp.ui.view.home.recycleradapters.searcher.ProductSearchAdapter
import com.toolstodo.ecommerceapp.ui.view.home.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var productList: List<Product>
    private lateinit var productAdapter: ProductSearchAdapter
    private lateinit var querySearch: String

    private val viewModel: SearchViewModel by viewModels()
    private val fragmentArgs: SearchFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
    }

    private fun initComponents() {

        if (!::querySearch.isInitialized) {
            querySearch = fragmentArgs.queryProduct
        }

        productList = emptyList()
        productAdapter = ProductSearchAdapter(productList) {
            onProductClick(it)
        }

        initUI()
        initListeners()
        initObservers()
    }

    private fun initUI() {
        with(binding) {

            svSearcher.setQuery(querySearch, true)
            viewModel.getProductsByName(0, 100, querySearch)

            rvProducts.adapter = productAdapter
            rvProducts.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun initListeners() {
        with(binding) {
            svSearcher.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    viewModel.getProductsByName(0, 100, query.toString())
                    querySearch = query.toString()
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }

            })
        }
    }

    private fun initObservers() {
        viewModel.productState.observe(viewLifecycleOwner) {
            productList = it.products
            productAdapter.updateList(it.products)
        }
    }

    private fun onProductClick(product: Product) {
        val goToProduct = SearchFragmentDirections.actionSearchFragmentToProductFragment(product)
        findNavController().navigate(goToProduct)
    }

}