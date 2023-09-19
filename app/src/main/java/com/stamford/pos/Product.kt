package com.stamford.pos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

open class Product {
    open val name: String?= "PRODUCT_NAME"
    open val price: Int?=0
    open val id: Int?=0
    open val quantity: Int?=0
}

@Entity(tableName = "productTbl")
data class ProductDB (
    @PrimaryKey( autoGenerate = true ) var uid: Long?,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "price") var price: Int,
    @ColumnInfo(name = "quantity") var quantity: Int,
)
