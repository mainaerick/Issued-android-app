package com.ticket.issued.Model

class Issues_details(var name: String, var date: Long, detail: String?, ticketnumber: String?, accountname: String?, urgency: Boolean) {
    var detail: String? = null
    var ticketnumber: String? = null
    var accountname: String? = null
    var isUrgency = false

}