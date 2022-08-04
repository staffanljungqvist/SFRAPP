package com.levento.sfrapp.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "benefits")
class BenefitEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "benefitId")
    var id: Int = 0

    @ColumnInfo(name = "benefitName")
    var benefitName: String = ""


    constructor() {}

    constructor(id: Int, benefitName: String) {
        this.id = id
        this.benefitName = benefitName
    }
}