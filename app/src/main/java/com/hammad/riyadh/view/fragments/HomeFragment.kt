package com.hammad.riyadh.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.hammad.riyadh.RiyadhApp
import com.hammad.riyadh.databinding.FragmentHomeBinding
import com.hammad.riyadh.di.ViewModelFactory
import com.hammad.riyadh.view.adapter.EventAdapter
import com.hammad.riyadh.view.adapter.TabAdapter
import com.hammad.riyadh.viewmodel.EventsViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var tabAdapter: TabAdapter
    private lateinit var eventAdapter: EventAdapter

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel by viewModels<EventsViewModel> { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RiyadhApp.get().appComponent.inject(this)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindView()
        bindListener()
        bindObserver()
    }

    private fun init() {
        tabAdapter = TabAdapter()
        eventAdapter = EventAdapter()
    }

    private fun bindView() {
        //binding tab recycler
        binding.recyclerTabs.layoutManager = StaggeredGridLayoutManager(1, RecyclerView.HORIZONTAL)
        binding.recyclerTabs.adapter = tabAdapter

        //binding event recycler
        binding.recycler.layoutManager = GridLayoutManager(context, 2)
        binding.recycler.adapter = eventAdapter

        lifecycleScope.launchWhenCreated {
            delay(1000)
            viewModel.loadEventsData("")
            viewModel.loadTabData()
        }

    }

    private fun bindListener() {

    }

    private fun bindObserver() {
        lifecycleScope.launchWhenCreated {
            viewModel.eventsShareFlow.collectLatest {
                Log.d("TAG", "bindObserver: $it")
                eventAdapter.submitList(it)
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.tabShareFlow.collectLatest {
                Log.d("TAG", "bindObserver: $it")
                tabAdapter.submitList(it)
            }
        }
    }

}