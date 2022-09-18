package com.example.grolapp2.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.grolapp2.R

class ItemListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.shopping_list_fragment, container, false)
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val itemData = context?.let {}
    }*/


}