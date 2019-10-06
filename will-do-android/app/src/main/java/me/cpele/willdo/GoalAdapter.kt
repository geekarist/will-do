package me.cpele.willdo

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class GoalAdapter: ListAdapter<MainViewModel.Goal, GoalViewHolder>(DiffCallback) {

    object DiffCallback: DiffUtil.ItemCallback<MainViewModel.Goal>() {
        override fun areItemsTheSame(
            oldItem: MainViewModel.Goal,
            newItem: MainViewModel.Goal
        ): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun areContentsTheSame(
            oldItem: MainViewModel.Goal,
            newItem: MainViewModel.Goal
        ): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
