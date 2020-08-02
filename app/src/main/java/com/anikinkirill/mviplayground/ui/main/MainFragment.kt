package com.anikinkirill.mviplayground.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anikinkirill.mviplayground.R
import com.anikinkirill.mviplayground.model.Blog
import com.anikinkirill.mviplayground.ui.DataStateListener
import com.anikinkirill.mviplayground.ui.main.state.MainStateEvent.GetBlogsEvent
import com.anikinkirill.mviplayground.ui.main.state.MainStateEvent.GetUserEvent
import com.anikinkirill.mviplayground.util.TopSpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(), BlogRecyclerAdapter.Interaction {

    companion object {
        private const val TAG = "MainFragment"
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var dataStateListener: DataStateListener
    private lateinit var blogAdapter: BlogRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        viewModel = activity?.let {
            ViewModelProvider(it).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid activity")

        subscribeObservers()

        initRecyclerView()
    }

    private fun initRecyclerView() {
        blogAdapter = BlogRecyclerAdapter(this)
        recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = blogAdapter
            addItemDecoration(TopSpacingItemDecoration(30))
        }
    }

    private fun subscribeObservers() {
        // datastate returns actual data from api
        // and then we set the data
        viewModel.dataState.observe(viewLifecycleOwner, Observer { datastate ->
            Log.d(TAG, "subscribeObservers: $datastate")

            // Handle loading and error message
            dataStateListener.onDataStateChange(datastate)

            // Handle data
            datastate.data?.let { mainViewState ->
                mainViewState.getContentIfNotHandled()?.let {
                    it.blogs?.let { blogs ->
                        viewModel.setBlogsListData(blogs)
                    }
                    it.user?.let { user ->
                        viewModel.setUserData(user)
                    }
                }
            }
        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewstate ->
            viewstate.blogs?.let { blogs ->
                blogAdapter.submitList(blogs)
            }

            viewstate.user?.let { user ->
                Log.d(TAG, "subscribeObservers: USER: ${user.username}")
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_get_blogs -> triggerGetBlogsEvent()
            R.id.action_get_user -> triggerGetUserEvent()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun triggerGetUserEvent() {
        viewModel.setStateEvent(GetUserEvent("1"))
    }

    private fun triggerGetBlogsEvent() {
        viewModel.setStateEvent(GetBlogsEvent())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            dataStateListener = context as DataStateListener
        }catch (e: ClassCastException) {
            Log.d(TAG, "onAttach: $context must implement DataStateListener interface")
        }
    }

    override fun onItemSelected(position: Int, item: Blog) {
        Log.d(TAG, "onItemSelected: ${item.title}")
    }


}