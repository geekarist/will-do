package me.cpele.willdo

import androidx.annotation.DrawableRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> get() = _state

    data class State(val goals: List<Goal>)

    data class Goal(
        val title: String,
        @DrawableRes val currency: Int,
        val deadline: String,
        val description: String,
        val pledge: String
    )
}
