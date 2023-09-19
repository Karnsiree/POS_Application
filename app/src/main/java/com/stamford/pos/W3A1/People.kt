package com.stamford.pos.W3A1

abstract class People {
    var Name: String?=null
    var ID: Int?=null

    abstract fun getStaffName(): String?
    abstract fun getStaffID(): Int?
    abstract fun setStaffName(Name: String)
    abstract fun setStaffID(ID: Int)
    abstract fun login()
    abstract fun selectMenu()
}

class Admin: People(){

    override fun getStaffName(): String? {
        return Name
    }

    override fun getStaffID(): Int? {
        return ID
    }

    override fun setStaffName(Name: String) {
        this.Name=Name
    }

    override fun setStaffID(ID: Int) {
        this.ID=ID
    }

    override fun login() {
        TODO("Not yet implemented")
    }

    override fun selectMenu() {
        TODO("Not yet implemented")
    }

    fun changeUISetting(){
        TODO("Not yet implemented")
    }
}

class BranchManager: People(){

    override fun getStaffName(): String? {
        return Name
    }

    override fun getStaffID(): Int? {
        return ID
    }

    override fun setStaffName(Name: String) {
        this.Name=Name
    }

    override fun setStaffID(ID: Int) {
        this.ID=ID
    }

    override fun login() {
        TODO("Not yet implemented")
    }

    override fun selectMenu() {
        TODO("Not yet implemented")
    }

    fun setBranch(){
        TODO("Not yet implemented")
    }

    fun updateBranch(){
        TODO("Not yet implemented")
    }
}

class SalesStaff: People(){

    override fun getStaffName(): String? {
        return Name
    }

    override fun getStaffID(): Int? {
        return ID
    }

    override fun setStaffName(Name: String) {
        this.Name=Name
    }

    override fun setStaffID(ID: Int) {
        this.ID=ID
    }

    override fun login() {
        TODO("Not yet implemented")
    }

    override fun selectMenu() {
        TODO("Not yet implemented")
    }

    fun selectCategory(categoryName: String){
        TODO("Not yet implemented")
    }
}

class Distributer: People(){

    override fun getStaffName(): String? {
        return Name
    }

    override fun getStaffID(): Int? {
        return ID
    }

    override fun setStaffName(Name: String) {
        this.Name=Name
    }

    override fun setStaffID(ID: Int) {
        this.ID=ID
    }

    override fun login() {
        TODO("Not yet implemented")
    }

    override fun selectMenu() {
        TODO("Not yet implemented")
    }

    fun sellProduct(productName: String){
        TODO("Not yet implemented")
    }

}