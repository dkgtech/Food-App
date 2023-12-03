package com.dkgtech.foodapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dkgtech.foodapp.databinding.FragmentHomeBinding
import com.dkgtech.foodapp.models.random_meal.Meal
import com.dkgtech.foodapp.viewmodels.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeMVVM: HomeViewModel

    companion object {
        val TAG = "Home Fragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        homeMVVM = ViewModelProvider(this)[HomeViewModel::class.java]


        homeMVVM.getRandomMeal()
        observeRandomMeal()


        return binding.root
    }

    private fun observeRandomMeal() {
        homeMVVM.observeRandomMealLiveData().observe(viewLifecycleOwner, object : Observer<Meal> {
            override fun onChanged(value: Meal) {
                Glide.with(this@HomeFragment).load(value.strMealThumb).into(binding.imgRandomImage)
            }

        })
    }

}