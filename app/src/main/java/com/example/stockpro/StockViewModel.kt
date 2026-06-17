package com.example.stockpro

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class StockViewModel : ViewModel() {

    private val _productos = mutableStateListOf(
        Producto(
            id = 1,
            nombre = "Teclado Mecánico",
            descripcion = "Teclado para estaciones de trabajo.",
            precio = 45.50,
            stockActual = 8,
            imagenResId = R.drawable.teclado
        ),
        Producto(
            id = 2,
            nombre = "Mouse Inalámbrico",
            descripcion = "Mouse ergonómico con conexión USB.",
            precio = 18.75,
            stockActual = 3,
            imagenResId = R.drawable.mouse
        ),
        Producto(
            id = 3,
            nombre = "Monitor 24 pulgadas",
            descripcion = "Monitor LED Full HD para oficina.",
            precio = 160.00,
            stockActual = 5,
            imagenResId = R.drawable.monitor
        ),
        Producto(
            id = 4,
            nombre = "Cable HDMI",
            descripcion = "Cable HDMI de 2 metros.",
            precio = 7.25,
            stockActual = 0,
            imagenResId = R.drawable.hdmi
        ),
        Producto(
            id = 5,
            nombre = "Disco SSD 500GB",
            descripcion = "Unidad de almacenamiento sólido.",
            precio = 58.90,
            stockActual = 4,
            imagenResId = R.drawable.ssd
        ),
        Producto(
            id = 6,
            nombre = "Router WiFi",
            descripcion = "Router inalámbrico para red de oficina.",
            precio = 72.00,
            stockActual = 10,
            imagenResId = R.drawable.router
        )
    )

    val productos: List<Producto> = _productos

    fun obtenerProducto(id: Int): Producto? {
        return _productos.find { producto ->
            producto.id == id
        }
    }

    fun actualizarStock(id: Int, nuevaCantidad: Int) {
        val producto = obtenerProducto(id)

        if (producto != null && nuevaCantidad >= 0) {
            val index = _productos.indexOf(producto)

            _productos[index] = producto.copy(
                stockActual = nuevaCantidad
            )
        }
    }

    fun calcularValorTotalInventario(): Double {
        return _productos.sumOf { producto ->
            producto.precio * producto.stockActual
        }
    }

    fun obtenerProductosEnRiesgo(): List<Producto> {
        return _productos.filter { producto ->
            producto.stockActual < 5
        }
    }

    fun contarProductosEnCero(): Int {
        return _productos.count { producto ->
            producto.stockActual == 0
        }
    }
}