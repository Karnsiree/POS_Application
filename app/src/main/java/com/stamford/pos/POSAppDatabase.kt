package com.stamford.pos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [Order::class, OrderLine::class], version=2)
abstract class POSAppDatabase: RoomDatabase() {
    abstract fun orderDao(): OrderDao
    abstract fun orderLineDao(): OrderLineDao

    companion object {
        private var INSTANCE: POSAppDatabase? = null

        //Define Migration method every time you change the schema
        val MIGRATION_1_2 = object : Migration(1,2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE 'Fruit' + ('id' INTEGER, 'name' TEXT, " + "PRIMARY KEY('id'))")
            }
        }

        val MIGRATION_2_3 = object : Migration(1,2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Book ADD COLUMN pub_year INTEGER")            }
        }

        fun getInstance(context:Context): POSAppDatabase {
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context, POSAppDatabase::class.java,"pos_app.db").fallbackToDestructiveMigration().build()
            }
            return INSTANCE as POSAppDatabase
        }

    }
}

////From now on our Room database is ready to be used. Whenever we need to access the database we use:
//// Get database instance
//val db = POSAppDatabase . getInstance(applicationContext)
//// Perform a database related operation
//val orders = db.orderDao().getAll( )