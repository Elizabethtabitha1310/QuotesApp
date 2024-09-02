package com.example.quotesapp.data

class FakeDatabase private constructor(){
    var quoteDao = FakeQuoteDao()
    companion object{
        @Volatile private var instance:FakeDatabase? = null
        fun  getInstance() =
            instance ?: synchronized(lock = this) {
                instance ?: FakeDatabase().also { instance = it }
            }
    }
}