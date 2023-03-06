package com.example.myapplication.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class BuildGridData(
    @StringRes val TitleResourceId:Int,
    @DrawableRes val ImageResourceId:Int,
    @StringRes val ValueResourceId:Int
)
