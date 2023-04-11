package com.disheecompose.models

import androidx.annotation.DrawableRes

data class Comment(
     @DrawableRes val userImage: Int,
     val userName: String,
     val comment: String
 )