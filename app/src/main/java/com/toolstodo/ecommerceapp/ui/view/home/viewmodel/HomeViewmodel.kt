package com.toolstodo.ecommerceapp.ui.view.home.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toolstodo.ecommerceapp.domain.model.product.ProductResponse
import com.toolstodo.ecommerceapp.domain.usecases.GetProductsUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewmodel @Inject constructor(
    private val getProductsUsecase: GetProductsUsecase,
) : ViewModel() {

    private val _productState = MutableLiveData<ProductResponse>()
    private val _errorState = MutableLiveData<String>()

    val productState get() = _productState
    val errorState get() = _errorState

    fun getProducts(skip: Int, limit: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _productState.postValue(getProductsUsecase(skip, limit)!!)
            } catch (e: Exception) {
                Log.d("Error getting the products", e.message.toString())
            }
        }
    }

}