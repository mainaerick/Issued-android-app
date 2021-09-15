package com.ticket.issued.ui.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ticket.issued.Adapter.AttachmentsSubmitAdapter
import com.ticket.issued.R
import java.util.*

class SubmitFragment : Fragment(), AttachmentsSubmitAdapter.ItemClickListener {
    var attachmentsAdapter: AttachmentsSubmitAdapter? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_submit, container, false)
        setupAdapter(root)
        return root
    }

    fun setupAdapter(view: View) {
        // set up the RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.attachment_recycler)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        attachmentsAdapter = AttachmentsSubmitAdapter(activity, attachments())
        attachmentsAdapter!!.setClickListener(this)
        recyclerView.adapter = attachmentsAdapter
    }

    private fun attachments(): List<String> {
        val animalNames = ArrayList<String>()
        animalNames.add("Attachment 1.png")
        animalNames.add("Attachment 2.png")
        return animalNames
    }

    override fun onItemClick(view: View?, position: Int) {}
}