package com.example.myapplication.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class SuperHeroData(
    @StringRes val nameRes:Int,
    @StringRes val descriptionRes:Int,
    @DrawableRes val imageRes:Int

)
