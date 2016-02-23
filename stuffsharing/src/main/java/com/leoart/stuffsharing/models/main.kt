package com.leoart.stuffsharing.models

import java.util.*

/**
 * Created by bogdan on 2/23/16.
 */

fun main(args: Array<String>){
    var owner = User("userName", "firstName", "lastName", "password", Date())
    var borrower = owner
    var order = Order(owner, borrower, Date())

    print(order)
}