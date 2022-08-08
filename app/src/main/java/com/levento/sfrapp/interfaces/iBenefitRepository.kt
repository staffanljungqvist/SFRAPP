package com.levento.sfrapp.interfaces

import com.levento.sfrapp.models.Benefit
import com.levento.sfrapp.models.BenefitCategory
import com.levento.sfrapp.models.DataOrException

interface iBenefitRepository {

    suspend fun getBenefits(): DataOrException<List<Benefit>, Exception>

    suspend fun getCategories(): DataOrException<List<BenefitCategory>, Exception>

}