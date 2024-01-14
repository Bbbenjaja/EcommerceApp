package com.toolstodo.ecommerceapp.ui.view.home.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toolstodo.ecommerceapp.domain.model.cart.Cart
import com.toolstodo.ecommerceapp.domain.model.cart.ProductCart
import com.toolstodo.ecommerceapp.domain.model.product.ProductResponse
import com.toolstodo.ecommerceapp.domain.usecases.AddToCartUseCase
import com.toolstodo.ecommerceapp.domain.usecases.ChangeFavoriteStatusUsecase
import com.toolstodo.ecommerceapp.domain.usecases.GetCartUseCase
import com.toolstodo.ecommerceapp.domain.usecases.GetProductsByCategoryUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsByCategoryUsecase: GetProductsByCategoryUsecase,
    private val changeFavoriteStatusUsecase: ChangeFavoriteStatusUsecase,
    private val addToCartUseCase: AddToCartUseCase,
) : ViewModel() {

    private val _similarProductState = MutableLiveData<ProductResponse>()
    private val _addToCartState = MutableLiveData<Boolean>()

    val similarProductState get() = _similarProductState
    val addToCartState get() = _addToCartState

    fun getSimilarProducts(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _similarProductState.postValue(getProductsByCategoryUsecase(category)!!)
            } catch (e: Exception) {
                Log.d("Error getting similar products", e.message.toString())
            }
        }
    }

    fun changeFavoriteStatus(isFav: Boolean, id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                changeFavoriteStatusUsecase(isFav, id)
            } catch (e: Exception) {
                Log.d("Error changing favorite status", e.message.toString())
            }
        }
    }

    fun addToCart(productCart: ProductCart){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _addToCartState.postValue(addToCartUseCase(productCart)!!)
            } catch (e: Exception) {
                Log.d("Error adding product to cart", e.message.toString())
            }
        }
    }

}