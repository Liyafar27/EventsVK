package ru.example.myfirstkotlinapp.model

interface ResponseEvent {
    val count:Int
    val items: List<ItemEvent>
}