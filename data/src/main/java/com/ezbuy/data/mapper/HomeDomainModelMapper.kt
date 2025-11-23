package com.ezbuy.data.mapper

import com.ezbuy.common.extensions.DataToDomainModelMapper
import com.ezbuy.data.model.HomeResponse
import com.ezbuy.data.model.HomeSection
import com.ezbuy.domain.model.home.BannerItem
import com.ezbuy.domain.model.home.CatalogItem
import com.ezbuy.domain.model.home.HomeModel
import com.ezbuy.domain.model.home.HomeSectionAdapterItem
import com.ezbuy.domain.model.home.ProductItem
import javax.inject.Inject

class HomeDomainModelMapper
    @Inject
    constructor() :
    DataToDomainModelMapper<HomeResponse, HomeModel> {
        override fun mapToDomainModel(responseModel: HomeResponse?): HomeModel {
            val homeSectionsAdapterItems = mutableListOf<HomeSectionAdapterItem>()

            for (section in responseModel?.sections!!) {
                val viewType =
                    when (section.type) {
                        0 -> HomeSectionAdapterItem.VIEW_TYPE_CATALOG
                        1 -> HomeSectionAdapterItem.VIEW_TYPE_BANNER
                        2 -> HomeSectionAdapterItem.VIEW_TYPE_SLIDABLE_PRODUCTS
                        3 -> HomeSectionAdapterItem.VIEW_TYPE_FLEX_BOX_PRODUCTS
                        4 -> HomeSectionAdapterItem.VIEW_TYPE_VERTICAL_PRODUCTS
                        else -> -1
                    }

                val sectionItem =
                    when (viewType) {
                        HomeSectionAdapterItem.VIEW_TYPE_CATALOG ->
                            HomeSectionAdapterItem.Catalog(
                                viewType = viewType,
                                catalogItem =
                                    section.sectionData.map { catalog ->
                                        mapHomeSectionToCatalogItem(catalog)
                                    },
                            )

                        HomeSectionAdapterItem.VIEW_TYPE_BANNER ->
                            HomeSectionAdapterItem.Banner(
                                viewType = viewType,
                                bannerItem =
                                    section.sectionData.map { banner ->
                                        mapHomeSectionToBannerItem(banner)
                                    },
                            )

                        HomeSectionAdapterItem.VIEW_TYPE_SLIDABLE_PRODUCTS ->
                            HomeSectionAdapterItem.SlidableProducts(
                                viewType = viewType,
                                productItem =
                                    section.sectionData.map { product ->
                                        mapToProductItem(product)
                                    },
                                sectionTitle = section.sectionTitle ?: "",
                            )

                        HomeSectionAdapterItem.VIEW_TYPE_FLEX_BOX_PRODUCTS ->
                            HomeSectionAdapterItem.FlexBoxProducts(
                                viewType = viewType,
                                productItem =
                                    section.sectionData.map { product ->
                                        mapToProductItem(product)
                                    },
                                sectionTitle = section.sectionTitle ?: "",
                            )

                        HomeSectionAdapterItem.VIEW_TYPE_VERTICAL_PRODUCTS ->
                            HomeSectionAdapterItem.VerticalProducts(
                                viewType = viewType,
                                productItem =
                                    section.sectionData.map { product ->
                                        mapToProductItem(product)
                                    },
                                sectionTitle = section.sectionTitle ?: "",
                            )

                        else -> null
                    }

                sectionItem?.let { homeSectionsAdapterItems.add(it) }
            }
            return HomeModel(sections = homeSectionsAdapterItems)
        }

        private fun mapHomeSectionToCatalogItem(homeSection: HomeSection): CatalogItem {
            return CatalogItem(
                icon = homeSection.icon,
                text = homeSection.text,
            )
        }

        private fun mapHomeSectionToBannerItem(homeSection: HomeSection): BannerItem {
            return BannerItem(
                image = homeSection.image,
                navigationData = homeSection.navigationData,
            )
        }

        private fun mapToProductItem(response: HomeSection): ProductItem {
            return ProductItem(
                productId = response.productId,
                productImage = response.productImage,
                text = response.text,
                subText = response.subText,
                review = response.review,
                questions = response.questions,
                rating = response.rating,
                share = response.share,
                piece = response.piece,
                soldOutText = response.soldOutText,
            )
        }
    }
