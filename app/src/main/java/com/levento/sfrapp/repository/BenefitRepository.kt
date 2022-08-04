package com.levento.sfrapp.repository

import TAG
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.levento.sfrapp.models.Benefit
import com.levento.sfrapp.models.BenefitCategory
import com.levento.sfrapp.models.DataOrException
import kotlinx.coroutines.tasks.await

class BenefitsRepository {

    suspend fun getAllBenefitsFromFirestore(): DataOrException<List<Benefit>, Exception> {
        val dataOrException = DataOrException<List<Benefit>, Exception>()
        Log.d(TAG, "Försöker hämta förmåner från firebase")
        try {
            val benefitList = mutableListOf<Benefit>()
            val documentSnap = FirebaseFirestore.getInstance().collection("benefits").get().await()
            for (document in documentSnap) {
                val tempBenefit = document.toObject(Benefit::class.java)
                tempBenefit.id = document.id
                benefitList.add(tempBenefit)
            }
            dataOrException.data = benefitList
        } catch (e: FirebaseFirestoreException) {
            dataOrException.e = e
            Log.d(TAG, "Kunde inte hämta förmåner, " + e.message)
        }
        return dataOrException
    }

    suspend fun getCategoriesFromFirestore(): DataOrException<List<BenefitCategory>, Exception> {
        val dataOrException = DataOrException<List<BenefitCategory>, Exception>()
        try {
            dataOrException.data =
                FirebaseFirestore.getInstance().collection("categories").get().await()
                    .map { document ->
                        document.toObject(BenefitCategory::class.java)
                    }
        } catch (e: FirebaseFirestoreException) {
            dataOrException.e = e
        }
        return dataOrException
    }
}