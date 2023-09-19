package com.stamford.pos

class Fruit(Name: String, Price: Int, ID: Int): Product() {
    override val name : String = Name
    override val price : Int = Price
    override val id : Int = ID
    override var quantity=1

    companion object {
        fun createFruitsList(): ArrayList<Fruit>{
            val fruits = ArrayList<Fruit>()

            fruits.add(Fruit("Mango",35,6000))
            fruits.add(Fruit("Mangosteen",35,6001))
            fruits.add(Fruit("Banana",35,6002))
            fruits.add(Fruit("Dragonfruit",35,6003))
            fruits.add(Fruit("Guava",35,6004))
            fruits.add(Fruit("Coconut",35,6005))
            fruits.add(Fruit("Watermelon",35,6006))

            return fruits
        }
    }
}