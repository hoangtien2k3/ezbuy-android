package com.ezbuy.data.extensions

import com.ezbuy.common.model.ResponseModel
import com.ezbuy.common.utils.Resource
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException

suspend fun <T : ResponseModel> handleAPICall(
    apiCall: suspend () -> Response<T>
): Resource<T> {
    return try {
        apiCall.invoke().handleAPIResponse()
    } catch (e: Exception) {
        e.printStackTrace()
        return when (e) {
            is UnknownHostException -> Resource.Failure(IOException())
            else -> Resource.Failure(IOException())
        }
    }
}

private fun <T : ResponseModel> Response<T>.handleAPIResponse(): Resource<T> {
    if (isSuccessful) {
        val responseBody = body()
        return if (responseBody != null) {
            Resource.Success(responseBody)
        } else {
            Resource.Failure(IOException("Response body is null"))
        }
    }
    
    // Handle error responses
    val errorMessage = try {
        errorBody()?.string() ?: "Unknown error occurred"
    } catch (e: Exception) {
        "Error reading error body: ${e.message}"
    }
    
    return Resource.Failure(IOException("HTTP ${code()}: $errorMessage"))
}