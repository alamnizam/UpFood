package com.codeturtle.upfood.theme.components

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "Day Preview",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Night Preview",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
annotation class UpFoodPreview