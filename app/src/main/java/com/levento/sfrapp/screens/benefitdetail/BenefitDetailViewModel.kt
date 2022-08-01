package com.levento.sfrapp.screens.benefitdetail

import TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levento.sfrapp.models.Benefit
import com.levento.sfrapp.repository.BenefitsRepository
import kotlinx.coroutines.launch

class BenefitDetailViewModel: ViewModel() {

    val benefitsRepository = BenefitsRepository()

    var benefit = mutableStateOf(Benefit())

    fun getBenefit(fUid: String) {
        viewModelScope.launch {
            val benefitdata = benefitsRepository.getBenefitFromFirestore(fUid)

            if (benefitdata != null ) {
                Log.d(TAG, "Received benefit " + benefitdata + " from repository")
                benefit.value = benefitdata!!
            } else {
                Log.d(TAG, "något gick fel")
            }
        }
    }
}