package com.stamford.pos
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {
    @Query("SELECT * FROM productTbl")
    fun getAll(): List<OrderLine>

    @Query("SELECT * FROM productTbl WHERE uid LIKE :id")
    fun loadAllByIds (id: IntArray): List<ProductDB>

    @Query("SELECT * FROM productTbl WHERE uid LIKE :id LIMIT 1")
    fun findByID (id: Long): ProductDB

    @Insert
    fun insertAll(vararg products: ProductDB)

    @Delete
    fun delete(products: ProductDB)
}