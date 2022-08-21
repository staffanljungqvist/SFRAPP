package com.levento.sfrapp.feature_benefits.domain.repository

import com.levento.sfrapp.feature_benefits.domain.model.Benefit
import com.levento.sfrapp.common.domain.model.NetworkResponse

interface BenefitsRepository {
    suspend fun getAllBenefits(): NetworkResponse<List<Benefit>, Exception>
    suspend fun getBenefit(id: String): NetworkResponse<Benefit, Exception>
}