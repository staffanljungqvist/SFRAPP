package com.levento.sfrapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.levento.sfrapp.domain.BenefitCategory
import com.levento.sfrapp.domain.BenefitEntity

@Dao
interface BenefitDao {

    @Insert
    fun insertBenefit(benefit: BenefitEntity)

    @Query("SELECT * FROM benefits")
    fun getAllBenefits(): LiveData<List<BenefitEntity>>
}