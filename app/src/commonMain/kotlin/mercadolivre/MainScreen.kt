package com.erafael.mercadolivre

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erafael.mercadolivre.mercadolivre.Product
import kotlinx.coroutines.delay
import mercadolivre.Platform
import mercadolivre.platform
import org.jetbrains.compose.resources.painterResource
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var images by remember {
        mutableStateOf(
            listOf(
                Res.drawable.banner,
                Res.drawable.images,
                Res.drawable.banner3,
            )
        )
    }
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    var selectedRegionCategory by remember {
        mutableStateOf<Int?>(0)
    }
    var selectedCategory by remember {
        mutableStateOf<Int?>(null)
    }
    var products by remember {
        mutableStateOf<List<Product>>(Product.getAll())
    }


    LaunchedEffect(true) {
        delay(3.seconds)
        while (true) {
            if (selectedIndex == images.size - 1) {
                selectedIndex = 0
                println("images size:${images.size}")
                println("decrementing $selectedIndex")
            } else {
                selectedIndex++
                println("incrementing $selectedIndex")
            }
            delay(3.seconds)
        }
    }
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(if (platform == Platform.Android) 12.dp else 8.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal =  16.dp)
    ) {
        item {
            SearchBar(
                inputField = {
                    SearchBarDefaults.InputField(
                        query = "",
                        onSearch = { },
                        onQueryChange = { },
                        onExpandedChange = { },
                        placeholder = {
                            Text(
                                text = "Pesquise no Made in Brasil",
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        },
                        enabled = true,
                        expanded = false,
                        leadingIcon = {
                            IconButton(
                                onClick = { }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = null,
                                )
                            }
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = { }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.AddShoppingCart,
                                    contentDescription = null,
                                )
                            }
                        },
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(if (platform == Platform.Android) 1f else 0.6f),
                expanded = false,
                onExpandedChange = {

                },
                windowInsets = WindowInsets(0),
            ) { }
        }
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth(if (platform == Platform.Android) 1f else 0.7f)
                    .height(if (platform == Platform.Android) 200.dp else 280.dp)

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = "Ofertas do dia".uppercase())
                    images.getOrNull(selectedIndex)?.let {
                        Image(
                            painter = painterResource(it),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.Start)
                                .clip(RoundedCornerShape(16.dp))
                                .animateContentSize(
                                    animationSpec = spring(
                                        stiffness = 2f
                                    )
                                )
                        )
                    }
                }
            }
        }
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Category.getRegions().forEachIndexed { index, category ->
                    item {
                        AssistChip(
                            onClick = {
                                selectedRegionCategory = index
                                if (index == 0) {
                                    products = Product.getAll()
                                } else {
                                    products = Category.getRegions()[index].products ?: listOf()
                                }
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = category.icon,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onBackground
                                )
                            },
                            colors = AssistChipDefaults.assistChipColors(
                                leadingIconContentColor = MaterialTheme.colorScheme.onBackground,
                                containerColor = if (selectedRegionCategory == index) MaterialTheme.colorScheme.primary.copy(
                                    alpha = 0.7f
                                ) else AssistChipDefaults.assistChipColors().containerColor
                            ),
                            modifier = Modifier
                                .height(45.dp),
                            label = { Text(text = category.name) }
                        )
                    }
                }
            }
        }
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(
                    12.dp,
                    if (platform == Platform.Android) Alignment.CenterHorizontally else Alignment.Start
                ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Category.get().forEachIndexed { index, category ->
                    item {
                        AssistChip(
                            onClick = {
                                selectedCategory = if (selectedCategory == index) null else index
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = category.icon,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onBackground
                                )
                            },
                            colors = AssistChipDefaults.assistChipColors(
                                leadingIconContentColor = MaterialTheme.colorScheme.onBackground,
                                containerColor = if (selectedCategory == index) MaterialTheme.colorScheme.primary.copy(
                                    alpha = 0.7f
                                ) else AssistChipDefaults.assistChipColors().containerColor
                            ),
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier
                                .height(45.dp),
                            label = { Text(text = category.name) }
                        )
                    }
                }
            }
        }
        item {
            LazyColumn(
                modifier = Modifier.height(620.dp),
            ) {
                item {
                    LazyHorizontalStaggeredGrid(
                        rows = StaggeredGridCells.Fixed(2),
                        modifier = Modifier.height(620.dp),
                    ) {
                        products.forEach { product ->
                            item {
                                Card(
                                    modifier = Modifier
                                        .padding(6.dp)
                                        .height(250.dp)
                                        .width(180.dp),
                                    shape = RoundedCornerShape(16.dp),
                                ) {
                                    Column {
                                        Box(
                                            modifier = Modifier
                                                .height(180.dp)
                                                .width(180.dp)

                                        ) {
                                            Image(
                                                painter = painterResource(product.image),
                                                contentDescription = null,
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier.fillMaxSize()
                                            )
                                        }
                                        Text(
                                            text = product.name,
                                            maxLines = 2,
                                            style = MaterialTheme.typography.bodyLarge.copy(
                                                lineHeight = 16.sp,
                                                fontWeight = FontWeight.W500
                                            ),
                                            modifier = Modifier
                                                .padding(top = 8.dp)
                                                .padding(horizontal = 8.dp)
                                        )
                                        Spacer(modifier = Modifier.weight(1f))
                                        //https://youtrack.jetbrains.com/issue/KT-69107/wasm-Seemingly-incorrect-rounding
                                        Text(
                                            text = "R$ " + product.price.toString(),
                                            modifier = Modifier.padding(horizontal = 8.dp)
                                        )
                                        Spacer(modifier = Modifier.weight(1f))
                                        OutlinedButton(
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                                .padding(bottom = 8.dp),
                                            onClick = { },
                                        ) {
                                            Text(text = "Add ao carrinho")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
