package com.example.paytabs_demo_store_android.database.enities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OrdersEntity(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "amount") val amount: Double,
    @ColumnInfo(name = "time") val time: String,
    @ColumnInfo(name = "state") val state: String
        ) {
    @PrimaryKey(autoGenerate = true)
    var id: Long?=null
}