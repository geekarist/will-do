package me.cpele.willdo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _state = MutableLiveData<State>().apply {
        value = State(
            listOf(
                Goal("id1", "title1", "01/01/2021", "description1", "1 €"),
                Goal("id2", "title2", "02/02/2022", "description", "2 €"),
                Goal("id3", "title3", "03/03/2023", "description", "3 €")
            )
        )
    }
    val state: LiveData<State> get() = _state

    data class State(val goals: List<Goal>)

    data class Goal(
        val id: String,
        val title: String,
        val deadline: String,
        val description: String,
        val pledge: String
    )
}
