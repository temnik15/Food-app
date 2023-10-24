package ru.rosystem.food_app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform