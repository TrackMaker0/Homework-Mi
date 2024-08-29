package com.example.homeworkday11

sealed class Item(open var like : Boolean){
    data class TextItem(var text : String, override var like : Boolean) : Item(like)
    data class ImageItem(var imageUrl : String, override var like: Boolean) : Item(like)
}