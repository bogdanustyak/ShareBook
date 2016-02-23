package com.leoart.stuffsharing.models
import java.util.*

/**
 * Created by bogdan on 2/23/16.
 * Data class for Order model
 */

data class Order (val owner: User, val borrowedBy: User, val date: Date)