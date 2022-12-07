package com.waleed.asteroidradarapp.main

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.waleed.asteroidradarapp.R
import com.waleed.asteroidradarapp.databinding.FragmentMainBinding
import com.waleed.asteroidradarapp.roomDB.RoomDB

class MainFragment : Fragment(), MenuProvider {

    private lateinit var viewModel: MainViewModel

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val application = requireNotNull(this.activity).application
        val dataSource = RoomDB.getRoomDB(application)

        val viewModelFactory = MainFragmentFactoryPattern(dataSource, application)
        viewModel = ViewModelProvider(
            this, viewModelFactory
        )[MainViewModel::class.java]
        binding.viewModel = viewModel

        requireActivity().addMenuProvider(this, viewLifecycleOwner)

        val adapter = AsteroidAdapter {
            findNavController().navigate(MainFragmentDirections.actionShowDetails(it))

        }
        binding.asteroidRecycler.adapter = adapter

        viewModel.asteroidList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return binding.root
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.main_overflow_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.show_all_menu -> {}
            R.id.show_rent_menu -> {}
            R.id.show_buy_menu -> {}

        }
        return true
    }
}