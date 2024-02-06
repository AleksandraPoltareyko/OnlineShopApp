package com.example.onlineshopapp.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.onlineshopapp.R
import com.example.onlineshopapp.databinding.FragmentCatalogBinding
import com.example.onlineshopapp.domain.Item
import com.example.onlineshopapp.domain.ListOfShopItems
import com.google.android.material.bottomnavigation.BottomNavigationView


class CatalogFragment:Fragment() {

    private var _binding: FragmentCatalogBinding? = null
    private val binding: FragmentCatalogBinding
        get() = _binding?: throw RuntimeException("FragmentCatalogBinding == null")

    private lateinit var viewModel: CatalogViewModel
    private lateinit var adapter : ShopItemAdapter

    private lateinit var changeUserListener: Postman

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Postman){
            changeUserListener = context
        }else{
            throw RuntimeException("activity must implement Postman")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCatalogBinding.inflate(inflater, container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = TITLE
        requireActivity().findViewById<BottomNavigationView>(R.id.bottom).visibility = View.VISIBLE
        viewModel = ViewModelProvider(this)[CatalogViewModel::class.java]
        viewModel.getShopList()
        observeViewModel()
        spinnerListener()
    }

    private fun spinnerListener() {
        binding.spinnerSort.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?,
                position: Int, id: Long
            ) {
                viewModel.sortShopList(position)
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {}
        }
    }


    private fun observeViewModel() {
        viewModel.shopList.observe(viewLifecycleOwner){ listOfShopItems ->
            adapter = ShopItemAdapter(requireActivity().application,listOfShopItems.items, this)
            binding.gridShopList.adapter = adapter

            if (listOfShopItems!=null) {
                changeUserListener.fragmentMail(null, listOfShopItems.items!!,)
            }
        }
        viewModel.needUpdateShopList.observe(viewLifecycleOwner){
            viewModel.shopList.value?.let { it1 -> loadTable(it1)
                if (it1!=null) {
                    changeUserListener.fragmentMail(null, it1.items!!,)
            }

            }
        }
    }

    private fun loadTable(listOfShopItems: ListOfShopItems){
        adapter.listOfShopItems = listOfShopItems.items
        adapter.notifyDataSetChanged()
    }

    private fun createTable(){
        viewModel.getShopList()
    }

    fun launchItemFragment(item: Item){
        findNavController().navigate(CatalogFragmentDirections.actionCatalogFragmentToItemFragment(item))
    }

    companion object{
        const val TITLE = "Каталог"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}