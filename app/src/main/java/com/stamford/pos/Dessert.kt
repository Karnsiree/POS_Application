package com.stamford.pos

class Dessert(Name: String, Price: Int, ID: Int): Product() {
    override val name : String = Name
    override val price : Int = Price
    override val id : Int = ID
    override var quantity=1

    companion object {
        fun createDessertsList(): ArrayList<Dessert>{
            val desserts = ArrayList<Dessert>()

            desserts.add(Dessert("Lemon Meringue Pie",45,3000))
            desserts.add(Dessert("Dark Chocolate Pudding",55,3001))
            desserts.add(Dessert("Chocolate Chip Cookie Sandwiches",35,3002))
            desserts.add(Dessert("Caramel Cake",45,3003))
            desserts.add(Dessert("Crumble Cake With Berries",50,3004))
            desserts.add(Dessert("Brownie",35,3005))
            desserts.add(Dessert("Pumpkin Pie",60,3006))

            return desserts
        }
    }
}