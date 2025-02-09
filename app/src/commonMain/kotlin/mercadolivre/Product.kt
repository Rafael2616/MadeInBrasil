package com.erafael.mercadolivre.mercadolivre

import com.erafael.mercadolivre.Res
import com.erafael.mercadolivre.n1
import com.erafael.mercadolivre.n2
import com.erafael.mercadolivre.n3
import com.erafael.mercadolivre.n4
import com.erafael.mercadolivre.n5
import com.erafael.mercadolivre.n7
import com.erafael.mercadolivre.nd1
import com.erafael.mercadolivre.nd2
import com.erafael.mercadolivre.nd3
import com.erafael.mercadolivre.nd4
import com.erafael.mercadolivre.nd6
import com.erafael.mercadolivre.nd8
import com.erafael.mercadolivre.nd9
import org.jetbrains.compose.resources.DrawableResource

data class Product(
    val name: String,
    val price: Float,
    val description: String,
    val image: DrawableResource,
    val nomeVendedor: String,
    val descontoPercent: Float? = null
) {
    companion object {
        fun getNordeste() = listOf(
            Product(
                name = "Frasco decorativo com Areia de Marujá",
                price = 14.49f,
                description = "",
                image = Res.drawable.nd3,
                nomeVendedor = ""
            ),
            Product(
                name = "Taça decor. Fortaleza",
                price = 16.99f,
                description = "",
                image = Res.drawable.nd4,
                nomeVendedor = ""
            ),
            Product(
                name = "Frasco com areia da praia de Fortaleza",
                price = 17.99f,
                description = "",
                image = Res.drawable.nd6,
                nomeVendedor = ""
            ),
            Product(
                name = "Cajuína 500g",
                price = 7.99f,
                description = "",
                image = Res.drawable.nd8,
                nomeVendedor = ""
            ),
            Product(
                name = "Castanha de caju 1Kg",
                price = 19.99f,
                description = "",
                image = Res.drawable.nd9,
                nomeVendedor = ""
            ),
        )

        fun getNorte() = listOf(
            Product(
                name = "Bombom Nossa Senhora",
                price = 2.99f,
                description = "Bombom tipico de Xique-Xique feito com chocolate",
                image = Res.drawable.nd1,
                nomeVendedor = "Ruan Abreu Marques"
            ),
            Product(
                name = "Cordâo Nossa Senhora",
                price = 8.99f,
                description = "",
                image = Res.drawable.nd2,
                nomeVendedor = ""
            ),
            Product(
                name = "Tucupi - Vale do amazonas",
                price = 7.49f,
                description = "",
                image = Res.drawable.n1,
                nomeVendedor = ""
            ),
            Product(
                name = "Lembrancinhas bonec.",
                price = 11.39f,
                description = "",
                image = Res.drawable.n2,
                nomeVendedor = ""
            ),
            Product(
                name = "Tacacá da hora - 1 porção",
                price = 13.99f,
                description = "",
                image = Res.drawable.n3,
                nomeVendedor = ""
            ),
            Product(
                name = "Fita Círio - 5 uni",
                price = 14.99f,
                description = "",
                image = Res.drawable.n4,
                nomeVendedor = ""
            ),
            Product(
                name = "Tapete Artesanal - Carijó",
                price = 2.99f,
                description = "",
                image = Res.drawable.n5,
                nomeVendedor = ""
            ),
            Product(
                name = "Vaso de barrro - tupiniquin",
                price = 22.99f,
                description = "",
                image = Res.drawable.n7,
                nomeVendedor = ""
            ),
        )

        fun getAll() = (getNordeste() + getNorte()).shuffled()

    }
}