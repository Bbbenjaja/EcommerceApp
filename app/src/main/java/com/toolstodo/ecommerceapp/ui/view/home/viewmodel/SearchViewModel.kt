package com.toolstodo.ecommerceapp.ui.view.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toolstodo.ecommerceapp.domain.model.product.ProductResponse
import com.toolstodo.ecommerceapp.domain.usecases.GetProductsByNameUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getProductsByNameUsecase: GetProductsByNameUsecase,
) : ViewModel() {

    private val _productState = MutableLiveData<ProductResponse>()
    private val _errorState = MutableLiveData<String>()

    val productState get() = _productState
    val errorState get() = _errorState

    fun getProductsByName(skip: Int, limit: Int, query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _productState.postValue(getProductsByNameUsecase(skip, limit, query)!!)
            } catch (e: Exception) {
                _errorState.postValue("Error getting the products")
            }
        }
    }

}