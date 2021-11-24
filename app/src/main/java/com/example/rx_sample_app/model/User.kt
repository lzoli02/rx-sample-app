package com.example.rx_sample_app.model

/**
 * Author:  Zoltan Lorinczi
 * Date:    12/8/2020
 */
class User(
    private var id: Int,
    private var firstName: String,
    private var lastName: String,
    private var age: Int,
    var loveFootball: Boolean,
    var loveHandball: Boolean
) {

    override fun toString(): String {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", loveFootball=" + loveFootball +
                ", loveHandball=" + loveHandball +
                '}'
    }
}