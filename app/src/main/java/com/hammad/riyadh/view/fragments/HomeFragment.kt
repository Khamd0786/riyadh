package com.hammad.riyadh.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.UiThread
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.hammad.riyadh.R
import com.hammad.riyadh.RiyadhApp
import com.hammad.riyadh.databinding.FragmentHomeBinding
import com.hammad.riyadh.di.ViewModelFactory
import com.hammad.riyadh.helper.LocaleHelper
import com.hammad.riyadh.models.TabItem
import com.hammad.riyadh.view.adapter.EventAdapter
import com.hammad.riyadh.view.adapter.TabAdapter
import com.hammad.riyadh.viewmodel.EventsViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class HomeFragment : Fragment(), TabAdapter.SelectListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var tabAdapter: TabAdapter
    private lateinit var eventAdapter: EventAdapter

    private lateinit var mBaseContext: Context


    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel by viewModels<EventsViewModel> { factory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mBaseContext = context
    }

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
        tabAdapter = TabAdapter(this)
        eventAdapter = EventAdapter(requireContext())
    }

    private fun bindView() {
        //binding tab recycler
        binding.recyclerTabs.layoutManager = StaggeredGridLayoutManager(1, RecyclerView.HORIZONTAL)
        binding.recyclerTabs.adapter = tabAdapter

        //binding event recycler
        binding.recycler.layoutManager = GridLayoutManager(context, 2)
        binding.recycler.adapter = eventAdapter

        lifecycleScope.launchWhenCreated {
            delay(200)
            viewModel.loadEventsData("")
            viewModel.loadTabData()
        }

    }

    private fun bindListener() {
        binding.header.back.setOnClickListener { activity?.finish() }
        binding.header.ivSettings.setOnClickListener {

//            val context = LocaleHelper.setLocale(
//                activity?.baseContext ?: return@setOnClickListener, "en"
//            )

            chooseLanguage()

//            updateLangChange(context)

        }
    }

    private fun chooseLanguage() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(mBaseContext.getString(R.string.choose_language))
            .setItems(
                mBaseContext.resources.getStringArray(R.array.language_array)
            ) { dialog, i ->
                mBaseContext = when (i) {
                    0 -> {
                        LocaleHelper.setLocale(activity?.baseContext ?: return@setItems, "en")
                    }
                    else -> {
                        LocaleHelper.setLocale(activity?.baseContext ?: return@setItems, "hi")
                    }
                }
                updateLangChange(mBaseContext)
                dialog.dismiss()
            }
        builder.create()
        builder.show()
    }

    @UiThread
    private fun scrollToTop() {
        lifecycleScope.launchWhenCreated {
            delay(500)
            binding.recycler.smoothScrollToPosition(0)
        }
    }

    @UiThread
    private fun updateLangChange(context: Context) {
        eventAdapter = EventAdapter(context)
        binding.recycler.adapter = eventAdapter

        viewModel.loadEventsData("") //load data again
        viewModel.loadTabData()

        binding.tvHeading.text = context.resources.getString(R.string.upcoming_events)
        binding.tvHeading2.text = context.resources.getString(R.string.today_events)

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

    override fun onTabSelected(item: TabItem, position: Int) {
        tabAdapter.selectedTopic(item.tab, position)
        viewModel.loadEventsData(if (item.tab.lowercase() == "All".lowercase()) "" else item.tab)
        scrollToTop()
    }

}