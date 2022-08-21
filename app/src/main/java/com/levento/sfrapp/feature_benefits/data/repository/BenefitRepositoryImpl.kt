package com.levento.sfrapp.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.levento.sfrapp.TAG
import com.levento.sfrapp.common.domain.model.NetworkResponse
import com.levento.sfrapp.feature_benefits.domain.model.Benefit
import com.levento.sfrapp.feature_benefits.domain.repository.BenefitsRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class BenefitsRepositoryImpl @Inject constructor(private val store: FirebaseFirestore) :
    BenefitsRepository {

    private var benefitList: List<Benefit> = emptyList()


    override suspend fun getAllBenefits(): NetworkResponse<List<Benefit>, Exception> {

        if (benefitList.isNotEmpty()) {
            Log.d(TAG, "förmånslistan är cachead")
            return NetworkResponse(data = benefitList)
        } else {
            val dataOrException = NetworkResponse<List<Benefit>, Exception>()

            try {
                dataOrException.data =
                    store.collection("benefits").get().await()
                        .map { document ->
                            document.toObject(Benefit::class.java)
                        }
                dataOrException.data?.let { benefitList = it }
            } catch (e: FirebaseFirestoreException) {
                dataOrException.e = e
            }
            return dataOrException
        }
    }

    override suspend fun getBenefit(id: String): NetworkResponse<Benefit, Exception> {
        val dataOrException = NetworkResponse<Benefit, Exception>()
        dataOrException.data = benefitList.first { it.id == id }
        return dataOrException
    }



/*    override suspend fun getCategories(): NetResponse<List<BenefitCategory>, Exception> {
        val dataOrException = NetResponse<List<BenefitCategory>, Exception>()
        try {
            dataOrException.data =
                store.collection("categories").get().await()
                    .map { document ->
                        document.toObject(BenefitCategory::class.java)
                    }
        } catch (e: FirebaseFirestoreException) {
            dataOrException.e = e
        }
        return dataOrException
    }*/
}

