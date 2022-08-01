package com.levento.sfrapp.screens.benefits

import TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levento.sfrapp.models.Benefit
import com.levento.sfrapp.models.BenefitCategory
import com.levento.sfrapp.models.PlaceHolders
import com.levento.sfrapp.repository.BenefitsRepository
import kotlinx.coroutines.launch

class BenefitsViewModel: ViewModel() {

    val benefitsRepository = BenefitsRepository()

    var populatedCategories = mutableStateOf(listOf(BenefitCategory()))
    var allBenefits = listOf<Benefit>()

    init {
        getBenefits()
    }

    fun getBenefits() {
        viewModelScope.launch {

            val benefitdata = benefitsRepository.getAllBenefitsFromFirestore()
            val categorydata = benefitsRepository.getCategoriesFromFirestore()

            if (benefitdata.data != null && categorydata.data != null) {
                Log.d(TAG, "Received " + benefitdata.data?.size + " benefits from repository")
                allBenefits = benefitdata.data!!
                populatedCategories.value = fillCategoriesWithBenefits(categorydata.data!!, benefitdata.data!!)
            } else {
                Log.d(TAG, "något gick fel")
                populatedCategories.value =
                    fillCategoriesWithBenefits(PlaceHolders.categories, PlaceHolders.benefits)
            }
        }
    }

    fun fillCategoriesWithBenefits(
        categoryList: List<BenefitCategory>,
        benefitList: List<Benefit>
    ): List<BenefitCategory> {
        val cateryListPopulated: MutableList<BenefitCategory> = categoryList.toMutableList()

        for (category in categoryList) {
            for (benefit in benefitList) {
                for (partOfCategory in benefit.category!!) {
                    if (partOfCategory == category.title) {
                        category.benefits.add(benefit)
                    }
                }
            }
        }
        return cateryListPopulated
    }
}