package com.levento.sfrapp.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.levento.sfrapp.interfaces.BenefitsRepository
import com.levento.sfrapp.models.Benefit
import com.levento.sfrapp.models.BenefitCategory
import com.levento.sfrapp.models.NetResponse
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class BenefitsRepositoryImpl @Inject constructor(private val store: FirebaseFirestore): BenefitsRepository {


    override suspend fun getBenefits(): NetResponse<List<Benefit>, Exception> {
        val dataOrException = NetResponse<List<Benefit>, Exception>()
        //TODO Implementera BenefitService och anropa getBenefits().
        try {
            dataOrException.data =
                store.collection("benefits").get().await()
                    .map { document ->
                        document.toObject(Benefit::class.java)
                    }
        } catch (e: FirebaseFirestoreException) {
            dataOrException.e = e
        }
        return dataOrException
    }

    override suspend fun getCategories(): NetResponse<List<BenefitCategory>, Exception> {
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
    }
}

