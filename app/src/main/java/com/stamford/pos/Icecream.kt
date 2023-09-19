package com.stamford.pos

class Icecream(Name: String, Price: Int, ID: Int): Product() {
    override val name : String = Name
    override val price : Int = Price
    override val id : Int = ID
    override var quantity=1

    companion object {
        fun createIcecreamsList(): ArrayList<Icecream>{
            val icecreams = ArrayList<Icecream>()

            icecreams.add(Icecream("Vanilla",35,4000))
            icecreams.add(Icecream("Chocolate",35,4001))
            icecreams.add(Icecream("Matcha",35,4002))
            icecreams.add(Icecream("Strawberry",35,4003))
            icecreams.add(Icecream("Cookies ‘N Cream",35,4004))
            icecreams.add(Icecream("Mint Chocolate Chip",35,4005))
            icecreams.add(Icecream("Cotton Candy",35,4006))

            return icecreams
        }
    }
}