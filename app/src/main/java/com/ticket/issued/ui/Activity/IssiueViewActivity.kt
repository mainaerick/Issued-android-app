package com.ticket.issued.ui.Activity

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ticket.issued.Adapter.AttachmentIssueViewAdapter
import com.ticket.issued.R
import com.ticket.issued.Util.RecyclerTouchListener
import com.ticket.issued.Util.RecyclerTouchListener.ClickListener
import com.ticket.issued.ui.Activity.IssiueViewActivity
import java.util.*

class IssiueViewActivity : AppCompatActivity() {
    var attachmentIssueViewAdapter: AttachmentIssueViewAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issiue_view)
        val mRecyclerView = findViewById<RecyclerView>(R.id.recycler_attachment)
        var mLayoutManager: LinearLayoutManager
        attachmentIssueViewAdapter = AttachmentIssueViewAdapter(this, attachments())
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        mRecyclerView.layoutManager = layoutManager
        mRecyclerView.adapter = attachmentIssueViewAdapter
        mRecyclerView.addOnItemTouchListener(RecyclerTouchListener(this, mRecyclerView, object : ClickListener {
            override fun onClick(view: View?, position: Int) {
                startActivity(Intent(this@IssiueViewActivity, IssiueViewActivity::class.java))
            }

            override fun onLongClick(view: View?, position: Int) {}
        }))
    }

    private fun attachments(): List<String> {
        val animalNames = ArrayList<String>()
        animalNames.add("Attachment 1.png")
        animalNames.add("Attachment 2.png")
        animalNames.add("Attachment 3.png")
        animalNames.add("Attachment 4.png")
        animalNames.add("Attachment 5.png")
        return animalNames
    }
}