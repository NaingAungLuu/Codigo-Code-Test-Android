package me.naingaungluu.codetest.ui.helpers

import java.io.Serializable

data class StatefulData<T>(
    val state : DataState,
    val data : T?,
    val message : String?
) : Serializable {

    companion object {

        fun <T> success(data : T?) : StatefulData<T> {
            return StatefulData(state = DataState.SUCCESS , data = data , message = null)
        }

        fun <T> error(message : String?) : StatefulData<T> {
            return StatefulData(state = DataState.ERROR , data = null , message = message)
        }

        fun <T> loading() : StatefulData<T> {
            return StatefulData(state = DataState.LOADING , data = null , message = null)
        }

        fun <T> completed() : StatefulData<T> {
            return StatefulData(state = DataState.COMPLETED , null , null)
        }

    }

    enum class DataState {
        SUCCESS,
        ERROR,
        LOADING,
        COMPLETED
    }

}