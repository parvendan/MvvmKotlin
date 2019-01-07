package com.paru.mvvmKotlin.ui.Fragment.FragmentCreateTrip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.paru.mvvmKotlin.base.BaseFragment
import com.paru.mvvmkotlin.R


class FragmentCreateTrip : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_trip, container, false)
    }

}
