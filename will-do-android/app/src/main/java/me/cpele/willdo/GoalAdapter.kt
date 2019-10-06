package me.cpele.willdo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class GoalAdapter : ListAdapter<MainViewModel.Goal, GoalViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<MainViewModel.Goal>() {
        override fun areItemsTheSame(
            oldItem: MainViewModel.Goal,
            newItem: MainViewModel.Goal
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: MainViewModel.Goal,
            newItem: MainViewModel.Goal
        ): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_goal, parent, false)
        return GoalViewHolder(view)
    }

    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}
