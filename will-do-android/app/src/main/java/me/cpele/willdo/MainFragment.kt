package me.cpele.willdo

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var adapter: GoalAdapter
    private lateinit var viewModel: MainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.main_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = GoalAdapter()
        main_recycler.adapter = adapter

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.state.observe(this, Observer(::render))
    }

    private fun render(state: MainViewModel.State) = adapter.submitList(state.goals)

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) =
        inflater.inflate(R.menu.menu_options, menu)
}
