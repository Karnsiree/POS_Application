package com.stamford.pos.W3A1

abstract class Products {
    var productName: String?=null
    var productCategory: String?=null
    var productID: Int?=null
    var productPrice: Double?=null
    var productQuantity: Int?=null

    abstract fun totalPrice(): Double?
    abstract fun getPrice(): Double?
    abstract fun getQuantity(): Int?
    abstract fun addToOrder(productName: String, productPrice: Double)
    abstract fun addQuantity(productQuantity: Int)
    abstract fun removeQuantity(productQuantity: Int)
}

class Drink: Products(){
    override fun totalPrice(): Double? {
        TODO("Not yet implemented")
    }

    override fun getPrice(): Double? {
        return productPrice
    }

    override fun getQuantity(): Int? {
        return productQuantity
    }

    override fun addToOrder(productName: String, productPrice: Double) {
        TODO("Not yet implemented")
    }

    override fun addQuantity(productQuantity: Int) {
        TODO("Not yet implemented")
    }

    override fun removeQuantity(productQuantity: Int) {
        TODO("Not yet implemented")
    }
}

class Drinks: Products(){
    override fun totalPrice(): Double? {
        TODO("Not yet implemented")
    }

    override fun getPrice(): Double? {
        return productPrice
    }

    override fun getQuantity(): Int? {
        return productQuantity
    }

    override fun addToOrder(productName: String, productPrice: Double) {
        TODO("Not yet implemented")
    }

    override fun addQuantity(productQuantity: Int) {
        TODO("Not yet implemented")
    }

    override fun removeQuantity(productQuantity: Int) {
        TODO("Not yet implemented")
    }
}

class Macarons: Products(){
    override fun totalPrice(): Double? {
        TODO("Not yet implemented")
    }

    override fun getPrice(): Double? {
        return productPrice
    }

    override fun getQuantity(): Int? {
        return productQuantity
    }

    override fun addToOrder(productName: String, productPrice: Double) {
        TODO("Not yet implemented")
    }

    override fun addQuantity(productQuantity: Int) {
        TODO("Not yet implemented")
    }

    override fun removeQuantity(productQuantity: Int) {
        TODO("Not yet implemented")
    }
}

class Cakes: Products(){
    override fun totalPrice(): Double? {
        TODO("Not yet implemented")
    }

    override fun getPrice(): Double? {
        return productPrice
    }

    override fun getQuantity(): Int? {
        return productQuantity
    }

    override fun addToOrder(productName: String, productPrice: Double) {
        TODO("Not yet implemented")
    }

    override fun addQuantity(productQuantity: Int) {
        TODO("Not yet implemented")
    }

    override fun removeQuantity(productQuantity: Int) {
        TODO("Not yet implemented")
    }
}


