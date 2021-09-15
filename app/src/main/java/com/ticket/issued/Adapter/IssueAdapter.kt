package com.ticket.issued.Adapter

import android.content.Context
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ticket.issued.Model.Date_details
import com.ticket.issued.Model.GeneralItem_details
import com.ticket.issued.Model.helper.ListItem
import com.ticket.issued.R
import java.text.SimpleDateFormat
import java.util.*

class IssueAdapter(context: Context, consolidatedList: List<ListItem>?) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
    private val mContext: Context
    var consolidatedList: List<ListItem>? = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        val inflater = LayoutInflater.from(parent.context)
        when (viewType) {
            ListItem.Companion.TYPE_GENERAL -> {
                val v1 = inflater.inflate(R.layout.list_issues, parent,
                        false)
                viewHolder = GeneralViewHolder(v1)
            }
            ListItem.Companion.TYPE_DATE -> {
                val v2 = inflater.inflate(R.layout.issue_header, parent, false)
                viewHolder = DateViewHolder(v2)
            }
        }
        return viewHolder!!
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder!!.itemViewType) {
            ListItem.Companion.TYPE_GENERAL -> {
                val generalItem = consolidatedList!![position] as GeneralItem_details
                val generalViewHolder = holder as GeneralViewHolder?
                generalViewHolder!!.txtTitle.text = generalItem.issues_details!!.name
            }
            ListItem.Companion.TYPE_DATE -> {
                val dateItem = consolidatedList!![position] as Date_details
                val dateViewHolder = holder as DateViewHolder?
                var date_show: String? = null
                val simpleDateFormat_date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val simpleDateFormat_month = SimpleDateFormat("MMMM", Locale.getDefault())
                val simpleDateFormat_month_year = SimpleDateFormat("MMMM,yyyy", Locale.getDefault())
                val dbcalendar = Calendar.getInstance()
                dbcalendar.timeInMillis = dateItem.date!!
                val now = Calendar.getInstance()
                date_show = if (dbcalendar[Calendar.DAY_OF_YEAR] == now[Calendar.DAY_OF_YEAR]) "Today" else if (dbcalendar[Calendar.MONTH] == now[Calendar.MONDAY]) "This Month - " + simpleDateFormat_month.format(now.time) else {
                    simpleDateFormat_month_year.format(now.time)
                }
                dateViewHolder!!.txtTitle.text = date_show
            }
        }
    }

    // ViewHolder for date row item
    internal inner class DateViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var txtTitle: TextView

        init {
            txtTitle = v.findViewById(R.id.txtHeader)
        }
    }

    // View holder for general row item
    internal inner class GeneralViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var txtTitle: TextView
        protected var txtDetail: TextView
        protected var txtTickenumber: TextView? = null
        protected var txtaccount_name: TextView? = null

        init {
            txtTitle = v.findViewById(R.id.ticket_title)
            txtDetail = v.findViewById(R.id.ticket_detail)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return consolidatedList!![position].type
    }

    override fun getItemCount(): Int {
        return if (consolidatedList != null) consolidatedList!!.size else 0
    }

    init {
        this.consolidatedList = consolidatedList
        mContext = context
    }
}