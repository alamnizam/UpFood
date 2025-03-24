package com.codeturtle.upfood.theme.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.codeturtle.upfood.theme.UpFoodTheme

@Composable
fun AppBar(
    title:String,
    navIcon:ImageVector? = null,
    onNavClick:()->Unit = {},
){
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            navIcon?.let {
                IconButton(onClick = onNavClick) {
                    Icon(imageVector = it, contentDescription = null)
                }
            }
        },
    )
}

@UpFoodPreview
@Composable
private fun AppBarPreview() {
    UpFoodTheme {
        Surface {
            AppBar(
                title = "Preview",
                navIcon = Icons.AutoMirrored.Filled.ArrowBack,
                onNavClick = {}
            )
        }
    }
}