package com.example.stockpro

import androidx.annotation.DrawableRes

data class Producto(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val stockActual: Int,
    @DrawableRes val imagenResId: Int
)