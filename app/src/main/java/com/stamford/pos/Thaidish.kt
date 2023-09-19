package com.stamford.pos

class Thaidish(Name: String, Price: Int, ID: Int): Product() {
    override val name : String = Name
    override val price : Int = Price
    override val id : Int = ID
    override var quantity=1

    companion object {
        fun createThaidishesList(): ArrayList<Thaidish>{
            val thaidishes = ArrayList<Thaidish>()

            thaidishes.add(Thaidish("Tom Yum Goong",55,5000))
            thaidishes.add(Thaidish("Tom Kha Gai",55,5001))
            thaidishes.add(Thaidish("Pad Thai",45,5002))
            thaidishes.add(Thaidish("Khao Pad",45,5003))
            thaidishes.add(Thaidish("Pad Krapow",50,5004))
            thaidishes.add(Thaidish("Khao Soi",50,5005))
            thaidishes.add(Thaidish("Kai Jeow",40,5006))

            return thaidishes
        }
    }
}