package com.ticket.issued.ui.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.ticket.issued.R
import com.ticket.issued.ui.Activity.LoginActivity

class AccountFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_account, container, false)
        val changepass_button = root.findViewById<TextView>(R.id.changepass_button)
        changepass_button.setOnClickListener { activity!!.startActivity(Intent(activity, LoginActivity::class.java)) }
        return root
    }
}