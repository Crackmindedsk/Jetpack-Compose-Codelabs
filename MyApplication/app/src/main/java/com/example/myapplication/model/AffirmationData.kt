package com.example.myapplication.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class AffirmationData(
    @StringRes val stringResourceId:Int,
    @DrawableRes val imageResourceId:Int
)
