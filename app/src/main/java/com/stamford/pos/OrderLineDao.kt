package com.stamford.pos
import androidx.room.*

@Dao
interface OrderLineDao {
    @Query("SELECT * FROM orderLineTbl")
    fun getAll(): List<OrderLine>

    @Query("SELECT * FROM orderLineTbl WHERE uid LIKE :id LIMIT 1")
    fun findByID (id: Long): OrderLine

    @Insert
    fun insertAll(vararg orderLine: OrderLine)

    @Delete
    fun delete(orderLine: OrderLine)

    @Update
    fun update(orderLine: OrderLine)

//    @Update
//    fun update(orderID: Long,productID : Long, price: Int, quantity: Int)
//                (id: Long, price: Int, quantity: Int): List<OrderLine>

//    @Update
//fun update(orderLine: OrderLine)
//    suspend fun update(orderLine: OrderLine)
}