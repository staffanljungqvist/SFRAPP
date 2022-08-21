package com.levento.sfrapp.interfaces

import com.levento.sfrapp.models.Benefit
import com.levento.sfrapp.models.BenefitCategory
import com.levento.sfrapp.models.NetResponse

interface BenefitsRepository {

    suspend fun getBenefits(): NetResponse<List<Benefit>, Exception>
    suspend fun getCategories(): NetResponse<List<BenefitCategory>, Exception>
}