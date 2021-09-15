package com.ticket.issued.Model

import com.ticket.issued.Model.helper.ListItem

class GeneralItem_details : ListItem() {
    var issues_details: Issues_details? = null
    override val type: Int
        get() = ListItem.Companion.TYPE_GENERAL
}