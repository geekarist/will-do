package me.cpele.willdo

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_goal.view.*

class GoalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: MainViewModel.Goal?) {
        itemView.apply {
            goal_item_title.text = item?.title
            goal_item_desc.text = item?.description
            goal_item_pledge.text = item?.pledge
            goal_item_deadline.text = item?.deadline
        }
    }
}
