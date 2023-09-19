package com.stamford.pos

class Macaron(Name: String, Price: Int, ID: Int, Color: String): Product() {
    override val name : String = Name
    override val price : Int = Price
    override val id : Int = ID
    val color : String = Color
    override var quantity=1

    companion object {
        fun createMacaronsList(): ArrayList<Macaron>{
            val macarons = ArrayList<Macaron>()

            macarons.add(Macaron("Black Macaron",45,1000,"Black"))
            macarons.add(Macaron("Blue Macaron",55,1001,"Blue"))
            macarons.add(Macaron("Green Macaron",35,1002,"Green"))
            macarons.add(Macaron("Navy Macaron",25,1003,"Navy"))
            macarons.add(Macaron("Pink Macaron",15,1004,"Pink"))
            macarons.add(Macaron("Red Macaron",50,1005,"Red"))
            macarons.add(Macaron("Yellow Macaron",60,1006,"Yellow"))

            return macarons
        }
    }
}