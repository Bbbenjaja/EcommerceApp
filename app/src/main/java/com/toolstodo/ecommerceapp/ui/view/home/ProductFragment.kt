package com.toolstodo.ecommerceapp.ui.view.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.toolstodo.ecommerceapp.R
import com.toolstodo.ecommerceapp.databinding.FragmentProductBinding
import com.toolstodo.ecommerceapp.domain.model.product.Product
import com.toolstodo.ecommerceapp.ui.view.components.dialogs.CartDialog
import com.toolstodo.ecommerceapp.ui.view.home.recycleradapters.images.ImageAdapter
import com.toolstodo.ecommerceapp.ui.view.home.recycleradapters.product.ProductAdapter
import com.toolstodo.ecommerceapp.ui.view.home.viewmodel.ProductViewModel
import com.toolstodo.ecommerceapp.utils.ScreenCalculator
import dagger.hilt.android.AndroidEntryPoint
import java.util.Arrays

@AndroidEntryPoint
class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    private lateinit var similarProducts: List<Product>
    private lateinit var productAdapter: ProductAdapter
    private lateinit var product: Product

    private lateinit var imagesAdapter: ImageAdapter

    private val viewModel: ProductViewModel by viewModels()
    private val fragmentArgs: ProductFragmentArgs by navArgs()

    // Init methods
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
    }

    private fun initComponents() {

        product = fragmentArgs.ProductInfo
        similarProducts = emptyList()
        productAdapter = ProductAdapter(similarProducts, onProductClick = { onProductClick(it) })

        imagesAdapter = ImageAdapter()

        initUI()
        initListeners()
        initObservers()

        viewModel.getSimilarProducts(product.category)
    }

    private fun initUI() {

        with(binding) {
            initRecyclerViews()
            changeFavIcon(product.isFavorite)

            txtRate.text = "${product.rating}"
            txtCategory.text = product.category
            txtDescription.text = product.description

            val priceText = getString(R.string.dollar_sign_label) + product.price
            txtPrice.text = priceText

            val discountText =
                getString(R.string.minus_sign_label) + product.discountPercentage + getString(R.string.percent_sign_label)
            txtDiscount.text = discountText

            txtProductName.text = product.title
        }
    }

    private fun initListeners() {
        with(binding) {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }

            btnFavorite.setOnClickListener {
                product.isFavorite = !product.isFavorite
                viewModel.changeFavoriteStatus(product.isFavorite, product.id)
                changeFavIcon(product.isFavorite)
            }

            btnAddToCart.setOnClickListener {
                showCartDialog()
            }
        }
    }

    private fun initObservers() {
        viewModel.similarProductState.observe(viewLifecycleOwner) {
            val productList = it.products.minus(product)
            similarProducts = productList
            productAdapter.updateList(productList)
        }

        viewModel.addToCartState.observe(viewLifecycleOwner){
            if (it){
                SweetAlertDialog(requireContext(), SweetAlertDialog.SUCCESS_TYPE).apply {
                    titleText = getString(R.string.added)
                    confirmText = getString(R.string.go_to_cart)
                    showContentText(false)
                    showCancelButton(false)
                    setConfirmClickListener {
                        findNavController().navigate(R.id.action_productFragment_to_cart_nav_graph)
                        this.dismissWithAnimation()
                    }
                }.show()
            }else{
                SweetAlertDialog(requireContext(), SweetAlertDialog.ERROR_TYPE).apply {
                    titleText = getString(R.string.error)
                    contentText = getString(R.string.error_adding_to_cart_check_stock)
                    confirmText = getString(R.string.ok)
                    showContentText(false)
                    showCancelButton(false)
                    setConfirmClickListener {
                        this.dismissWithAnimation()
                    }
                }.show()
            }
        }

    }

    // Fragment methods
    private fun onProductClick(product: Product) {
        val goToProductView = ProductFragmentDirections.actionProductFragmentSelf(product)
        findNavController().navigate(goToProductView)
    }

    private fun changeFavIcon(isFav: Boolean) {
        val imgIcon = if (isFav) {
            R.drawable.ic_fav_selected
        } else {
            R.drawable.ic_fav_unselected
        }

        binding.btnFavorite.setImageResource(imgIcon)
    }

    private fun initRecyclerViews() {
        with(binding) {
            rvSimilarProducts.adapter = productAdapter
            rvSimilarProducts.layoutManager =
                GridLayoutManager(
                    requireContext(),
                    ScreenCalculator.calculateNoOfColumns(
                        requireContext(),
                        ScreenCalculator.DP_COLUMNS
                    )
                )

            imagesAdapter.updateList(product.images)

            vpImages.adapter = imagesAdapter
        }
    }

    private fun showCartDialog() {
        val cartDialog = CartDialog()
        cartDialog.createDialog(requireContext(), product, onAccept = {productCart ->
            viewModel.addToCart(productCart)
        }).show()
    }


}