package com.leoart.sharebook.models


class User {
    var name: String? = null
    var email: String? = null
    var picture: ByteArray? = null
    var ageRange: String? = null
    var gender: String? = null
    var id: String? = null

    constructor(name: String?, email: String?, picture: ByteArray?, ageRange: String?, gender: String?, id: String?) {
        this.name = name
        this.email = email
        this.picture = picture
        this.ageRange = ageRange
        this.gender = gender
        this.id = id
    }

    constructor(name: String?, email: String?) {
        this.name = name
        this.email = email
    }
}
