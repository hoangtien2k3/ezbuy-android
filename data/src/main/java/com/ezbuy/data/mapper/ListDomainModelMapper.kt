package com.ezbuy.data.mapper

import com.ezbuy.common.extensions.DataToDomainModelMapper
import com.ezbuy.data.model.ListProducts
import com.ezbuy.data.model.ListResponse
import com.ezbuy.domain.model.list.ListModel
import com.ezbuy.domain.model.list.ListProductsModel
import javax.inject.Inject

class ListDomainModelMapper
    @Inject
    constructor() :
    DataToDomainModelMapper<ListResponse, ListModel> {
        override fun mapToDomainModel(responseModel: ListResponse?): ListModel {
            val domainList = responseModel?.listResponse?.map { responseToDomainProduct(it) }
            return ListModel(
                productList = domainList,
                productLimit = responseModel?.productLimit,
                totalCount = responseModel?.totalCount,
            )
        }

        private fun responseToDomainProduct(response: ListProducts): ListProductsModel {
            return ListProductsModel(
                productId = response.productId,
                productImage = response.productImage,
                text = response.text,
                subText = response.subText,
                review = response.review,
                questions = response.questions,
                rating = response.rating,
            )
        }
    }
