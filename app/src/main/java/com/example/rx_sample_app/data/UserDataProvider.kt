package com.example.rx_sample_app.data

import com.example.rx_sample_app.model.User
import java.util.ArrayList

/**
 * Author:  Zoltan Lorinczi
 * Date:    12/8/2020
 */
class UserDataProvider {

    lateinit var users: MutableList<User>

    init {
        populateUsersList()
    }

    private fun populateUsersList() {
        users = ArrayList()
        users.add(User(1, "First Name 1", "Last Name 1", 10, true, false))
        users.add(User(2, "First Name 2", "Last Name 2", 20, true, false))
        users.add(User(3, "First Name 3", "Last Name 3", 15, true, false))
        users.add(User(4, "First Name 4", "Last Name 4", 40, false, true))
        users.add(User(5, "First Name 5", "Last Name 5", 18, false, true))
        users.add(User(6, "First Name 5", "Last Name 6", 9, false, true))
        users.add(User(7, "First Name 7", "Last Name 7", 21, true, true))
        users.add(User(8, "First Name 8", "Last Name 8", 55, true, true))
        users.add(User(9, "First Name 9", "Last Name 9", 80, false, false))
        users.add(User(10, "First Name 10", "Last Name 10", 2, false, false))
    }
}