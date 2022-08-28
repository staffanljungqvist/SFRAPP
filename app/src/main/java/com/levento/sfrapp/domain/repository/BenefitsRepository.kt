package com.levento.sfrapp.domain.repository

import com.levento.sfrapp.domain.model.Benefit
import com.levento.sfrapp.domain.model.NetworkResponse

interface BenefitsRepository {
    suspend fun getAllBenefits(): NetworkResponse<List<Benefit>, Exception>
    suspend fun getBenefit(id: String): NetworkResponse<Benefit, Exception>
}