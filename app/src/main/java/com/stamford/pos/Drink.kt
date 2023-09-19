package com.stamford.pos

class Drink (Name: String, Price: Int, ID: Int, Alcoholic: Boolean): Product() {
    override val name : String = Name
    override val price : Int = Price
    override val id : Int = ID
    val alcoholic : Boolean = Alcoholic
    override var quantity=1

    companion object {
        fun createDrinksList(): ArrayList<Drink> {
            val drinks = ArrayList<Drink>()

            drinks.add(Drink("Mineral Water", 10, 2001, false))
            drinks.add(Drink("Coke", 25, 2002, false))
            drinks.add(Drink("Coffee", 35, 2003, false))
            drinks.add(Drink("Hot Chocolate", 50, 2004, false))
            drinks.add(Drink("Orange Juice", 80, 2005, false))
            drinks.add(Drink("Beer", 100, 2006, true))
            drinks.add(Drink("Wine", 250, 2007, true))

            return drinks
        }
    }
}