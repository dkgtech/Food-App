package com.dkgtech.foodapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dkgtech.foodapp.activities.RandomMealActivity
import com.dkgtech.foodapp.databinding.FragmentHomeBinding
import com.dkgtech.foodapp.models.random_meal.Meal
import com.dkgtech.foodapp.viewmodels.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeMVVM: HomeViewModel
    private lateinit var randomMeal: Meal

    companion object {
        val TAG = "Home Fragment"
        const val MEAL_ID = "com.dkgtech.foodapp.fragments.idMeal"
        const val MEAL_NAME = "com.dkgtech.foodapp.fragments.nameMeal"
        const val MEAL_THUMB = "com.dkgtech.foodapp.fragments.thumbMeal"
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
        onRandomMealClick()


        return binding.root
    }

    private fun onRandomMealClick() {
        binding.cardViewRandomImage.setOnClickListener {
            val intent = Intent(requireContext(), RandomMealActivity::class.java)
            intent.putExtra(MEAL_ID, randomMeal.idMeal)
            intent.putExtra(MEAL_NAME, randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB, randomMeal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun observeRandomMeal() {
        homeMVVM.observeRandomMealLiveData().observe(viewLifecycleOwner
        ) { value ->
            Glide.with(this@HomeFragment).load(value.strMealThumb).into(binding.imgRandomImage)

            this@HomeFragment.randomMeal = value
        }
    }

}