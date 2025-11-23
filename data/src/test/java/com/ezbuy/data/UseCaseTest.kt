package com.ezbuy.data

import com.ezbuy.common.utils.Resource
import com.ezbuy.data.mapper.ListDomainModelMapper
import com.ezbuy.data.model.ListResponse
import com.ezbuy.data.repository.ListRepository
import com.ezbuy.data.usecase.GetListUseCaseImpl
import com.ezbuy.domain.model.list.ListModel
import com.ezbuy.domain.model.list.ListRequestModel
import com.ezbuy.domain.usecase.GetListUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.IOException

class UseCaseTest {
    private val mockListRepository = mockk<ListRepository>()
    private val mockListMapper = mockk<ListDomainModelMapper>()

    // UseCase
    private lateinit var getListUseCase: GetListUseCase

    // Test data
    private val mockListResponseModel = mockk<ListResponse>()
    private val mockListModel = mockk<ListModel>()
    private val listRequestModel = ListRequestModel(page = GetListUseCaseImpl.PAGING_NUMBER_DEFAULT)

    @Before
    fun setUp() {
        getListUseCase =
            GetListUseCaseImpl(mockListRepository, mockListMapper, Dispatchers.Unconfined)
    }

    @Test
    fun `invoke returns Resource Success`() =
        runBlocking {
            coEvery { mockListRepository.getListFirst() } returns Resource.Success(mockListResponseModel)
            coEvery { mockListMapper.mapToDomainModel(mockListResponseModel) } returns mockListModel

            val result = getListUseCase.invoke(listRequestModel)

            assertTrue(result is Resource.Success)
            assertEquals(mockListModel, (result as Resource.Success).data)
        }

    @Test
    fun `invoke returns Resource Failure`() =
        runBlocking {
            val ioException = IOException("An error occurred")
            coEvery { mockListRepository.getListFirst() } returns Resource.Failure(ioException)

            val result = getListUseCase.invoke(listRequestModel)

            assertTrue(result is Resource.Failure)
            assertEquals(ioException, (result as Resource.Failure).error)
        }
}
