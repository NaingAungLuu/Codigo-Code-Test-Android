package me.naingaungluu.codetest.data.sources.remote

import me.naingaungluu.codetest.ui.helpers.StatefulData
import me.naingaungluu.codetest.utils.BASE_IMAGE_URL
import retrofit2.Response

abstract class RemoteDataSource {

    companion object {
        fun getImageUrl(path : String) = BASE_IMAGE_URL.plus(path)
    }

    protected suspend fun <T> getResponse(errorMessage : String , request : suspend () -> Response<T>) : StatefulData<T> {
        return try {
            with(request.invoke()) {
                if (isSuccessful) {
                    StatefulData.success(body())
                } else {
                    StatefulData.error(errorMessage)
                }
            }
        } catch (error : Throwable) {
            StatefulData.error("A Network Error Occurred")
        }
    }

}