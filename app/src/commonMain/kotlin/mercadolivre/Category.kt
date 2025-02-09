package com.erafael.mercadolivre

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ProductionQuantityLimits
import androidx.compose.material.icons.outlined.CloudQueue
import androidx.compose.material.icons.outlined.Forest
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material.icons.outlined.SelectAll
import androidx.compose.material.icons.outlined.South
import androidx.compose.ui.graphics.vector.ImageVector
import com.erafael.mercadolivre.mercadolivre.Product

data class Category(
    val icon: ImageVector,
    val name: String,
    val products: List<Product>? = null
) {
    companion object {
        fun getRegions(): List<Category> {
            return listOf(
                Category(
                    icon = Icons.Outlined.SelectAll,
                    name = "Todos"
                ),
                Category(
                    icon = Icons.Outlined.Forest,
                    name = "Norte",
                    products = Product.getNorte()
                ),
                Category(
                    icon = Icons.Outlined.CloudQueue,
                    name = "Nordeste",
                    products = Product.getNordeste()
                ),
                Category(
                    icon = Icons.Outlined.South,
                    name = "Sul"
                ),
                Category(
                    icon = Icons.Outlined.Forest,
                    name = "Sudeste"
                ),
                Category(
                    icon = Icons.Outlined.CloudQueue,
                    name = "Centro-Oeste"
                )
            )
        }

        fun get(): List<Category> {
            return listOf(
                Category(
                    icon = Icons.Outlined.LocalOffer,
                    name = "Ofertas"
                ),
                Category(
                    icon = Icons.Default.ProductionQuantityLimits,
                    name = "Novos"
                ),
            )
        }

    }
}
