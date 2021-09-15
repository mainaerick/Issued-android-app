package com.ticket.issued.Model

import com.ticket.issued.Model.helper.ListItem

class Date_details : ListItem() {
    var date: Long? = null
    override val type: Int
        get() = ListItem.Companion.TYPE_DATE
}