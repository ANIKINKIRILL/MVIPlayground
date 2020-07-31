package com.anikinkirill.mviplayground.ui.main

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.anikinkirill.mviplayground.R
import com.anikinkirill.mviplayground.ui.main.state.MainStateEvent
import com.anikinkirill.mviplayground.ui.main.state.MainStateEvent.*

class MainFragment : Fragment() {

    companion object {
        private const val TAG = "MainFragment"
    }

    private lateinit var viewModel: MainViewModel

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
    }

    private fun subscribeObservers() {
        // datastate returns actual data from api
        // and then we set the data
        viewModel.dataState.observe(viewLifecycleOwner, Observer { datastate ->
            Log.d(TAG, "subscribeObservers: $datastate")

            datastate.blogs?.let { blogs ->
                viewModel.setBlogsListData(blogs)
            }

            datastate.user?.let {user ->
                viewModel.setUserData(user)
            }

        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewstate ->
            viewstate.blogs?.let { blogs ->
                Log.d(TAG, "subscribeObservers: BLOGS: ${blogs.size}")
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


}