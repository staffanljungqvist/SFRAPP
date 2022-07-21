package com.levento.sfrapp.repository

import TAG
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.levento.sfrapp.domain.Article
import com.levento.sfrapp.domain.Benefit
import com.levento.sfrapp.domain.BenefitCategory
import com.levento.sfrapp.domain.DataOrException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BenefitsRepository @Inject constructor(
) {
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

    suspend fun getBenefitFromFirestore(fUid: String): Benefit? {

        var benefit: Benefit? = Benefit()

        Log.d(TAG, "Försöker hämta förmåner från firebase")
        try {
            val data = FirebaseFirestore.getInstance().collection("benefits").document(fUid).get().await()

            benefit = data.toObject(Benefit::class.java)

            Log.d("parser", "Den nerladdade förmånen är: " + benefit?.title)

        } catch (e: FirebaseFirestoreException) {
            Log.d(TAG, "Kunde inte hämta förmåner, " + e.message)
        }
        return benefit
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