package com.levento.sfrapp.interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.levento.sfrapp.models.BenefitEntity

@Dao
interface BenefitDao {

    @Insert
    fun insertBenefit(benefit: BenefitEntity)

    @Query("SELECT * FROM benefits")
    fun getAllBenefits(): LiveData<List<BenefitEntity>>
}