package com.toolstodo.ecommerceapp.domain.usecases

import com.toolstodo.ecommerceapp.data.repository.StoreRepositoryImpl
import javax.inject.Inject

class ChangeFavoriteStatusUsecase @Inject constructor(
    private val storeRepositoryImpl: StoreRepositoryImpl,
) {

    suspend operator fun invoke(isFav: Boolean, id: Int) {
        storeRepositoryImpl.changeFavoriteStatus(isFav, id)
    }

}