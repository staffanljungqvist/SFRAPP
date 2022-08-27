package com.levento.sfrapp.common.utils

import androidx.compose.foundation.lazy.rememberLazyListState
import com.levento.sfrapp.feature_benefits.domain.model.Benefit
import com.levento.sfrapp.feature_benefits.domain.model.BenefitCategory

fun MutableList<BenefitCategory>.sortCategories(): List<BenefitCategory> {

    val topicalCategory = this.first() { it.title == "Aktuellt" }

    val newList = this.toMutableList()

    newList.remove(topicalCategory)

    newList.sortBy { it.title }

    return listOf(topicalCategory) + newList
}

fun MutableList<BenefitCategory>.populate(benefitList: List<Benefit>) {

    for (category in this) {
        for (benefit in benefitList) {
            for (partOfCategory in benefit.category!!) {
                if (partOfCategory == category.title) {
                    category.benefits.add(benefit)
                }
            }
        }
    }
}