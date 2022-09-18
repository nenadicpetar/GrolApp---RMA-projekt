package com.example.grolapp2.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grolapp2.R
import com.example.grolapp2.database.AppDatabase
import com.example.grolapp2.fragment.adapters.ItemAdapter
import com.example.grolapp2.fragment.adapters.ItemClickType
import com.example.grolapp2.fragment.adapters.ViewPagerAdapter
import com.example.grolapp2.presenter.ItemPresenter
import com.example.grolapp2.repository.ItemsRepository


class KauflandFragment : Fragment() {
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var rvItems: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        itemAdapter = ItemAdapter()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kaufland, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

        val appDatabase = AppDatabase.getDatabase(requireContext())
        val repository = ItemsRepository(appDatabase.itemsDao())
        val presenter = ItemPresenter(repository)

        presenter.get("Kaufland")

        presenter.items.observe(viewLifecycleOwner) {
            itemAdapter.setItems(it)
        }

    }

    private fun initRecyclerView() {
        rvItems = requireView().findViewById(R.id.rvItems)
        val layoutManager = LinearLayoutManager(requireContext())
        rvItems.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayout.VERTICAL))
        rvItems.layoutManager = layoutManager
        rvItems.adapter = itemAdapter
    }


}