package com.ticket.issued.ui.Fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cooltechworks.views.shimmer.ShimmerRecyclerView
import com.ticket.issued.Adapter.IssueAdapter
import com.ticket.issued.Model.Date_details
import com.ticket.issued.Model.GeneralItem_details
import com.ticket.issued.Model.Issues_details
import com.ticket.issued.Model.helper.ListItem
import com.ticket.issued.R
import com.ticket.issued.Util.RecyclerTouchListener
import com.ticket.issued.ui.Activity.IssiueViewActivity
import java.text.SimpleDateFormat
import java.util.*


private var mRecyclerView: ShimmerRecyclerView? = null
class IssuesFragment : Fragment() {
    private val myOptions: MutableList<Issues_details> = ArrayList<Issues_details>()
    var consolidatedList: MutableList<ListItem> = ArrayList()
    
    private var adapter: IssueAdapter? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root: View = inflater.inflate(R.layout.fragment_issues, container, false)
        setupadapter(root)
        return root
    }

    private fun setupadapter(view: View) {
        mRecyclerView = view.findViewById(R.id.issue_recyclerview)
        mRecyclerView!!.setHasFixedSize(true)
        val simpleDateFormat_date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        try {
            myOptions.add(Issues_details("HP Inkjet Not Responding", simpleDateFormat_date.parse("2021-09-08").time, "Lorem Ipsum is simply dummy text of the printing and typese!ing industry", "HKEW-342G", "Mark Miller", false))
            myOptions.add(Issues_details("HP Inkjet Not Responding", simpleDateFormat_date.parse("2021-09-06").time, "Lorem Ipsum is simply dummy text of the printing and typese!ing industry", "HKEW-342G", "Mark Miller", false))
            myOptions.add(Issues_details("HP Inkjet Not Responding", simpleDateFormat_date.parse("2021-06-05").time, "Lorem Ipsum is simply dummy text of the printing and typese!ing industry", "HKEW-342G", "Mark Miller", false))
            myOptions.add(Issues_details("HP Inkjet Not Responding", simpleDateFormat_date.parse("2021-05-17").time, "Lorem Ipsum is simply dummy text of the printing and typese!ing industry", "HKEW-342G", "Mark Miller", false))
            myOptions.add(Issues_details("HP Inkjet Not Responding", simpleDateFormat_date.parse("2021-05-17").time, "Lorem Ipsum is simply dummy text of the printing and typese!ing industry", "HKEW-342G", "Mark Miller", false))
            myOptions.add(Issues_details("HP Inkjet Not Responding", simpleDateFormat_date.parse("2021-05-17").time, "Lorem Ipsum is simply dummy text of the printing and typese!ing industry", "HKEW-342G", "Mark Miller", false))
            myOptions.add(Issues_details("HP Inkjet Not Responding", simpleDateFormat_date.parse("2021-05-17").time, "Lorem Ipsum is simply dummy text of the printing and typese!ing industry", "HKEW-342G", "Mark Miller", false))
            myOptions.add(Issues_details("HP Inkjet Not Responding", simpleDateFormat_date.parse("2021-06-05").time, "Lorem Ipsum is simply dummy text of the printing and typese!ing industry", "HKEW-342G", "Mark Miller", false))
            myOptions.add(Issues_details("HP Inkjet Not Responding", simpleDateFormat_date.parse("2021-05-17").time, "Lorem Ipsum is simply dummy text of the printing and typese!ing industry", "HKEW-342G", "Mark Miller", false))
        } catch (e: Exception) {
            Log.e("TAG", "setupadapter: $e")
        }
        val groupedHashMap: HashMap<Long, MutableList<Issues_details>> = groupDataIntoHashMap(myOptions)
        for (date in groupedHashMap.keys) {
            val dateItem = Date_details()
            dateItem.date = date
            consolidatedList.add(dateItem)
            for (issues_details in groupedHashMap[date]!!) {
                val generalItem = GeneralItem_details()
                generalItem.issues_details = issues_details
                consolidatedList.add(generalItem)
            }
        }
        adapter = activity?.let { IssueAdapter(it, consolidatedList) }
        val layoutManager = LinearLayoutManager(context)
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL)
        mRecyclerView!!.setLayoutManager(layoutManager)
        mRecyclerView!!.setAdapter(adapter)
        mRecyclerView!!.addOnItemTouchListener(RecyclerTouchListener(context, mRecyclerView!!, object : RecyclerTouchListener.ClickListener {
            override fun onClick(view: View?, position: Int) {
                startActivity(Intent(context, IssiueViewActivity::class.java))
            }

            override fun onLongClick(view: View?, position: Int) {}
        }))
    }

    private fun groupDataIntoHashMap(issues_detailsList: List<Issues_details>): HashMap<Long, MutableList<Issues_details>> {
        val valueComparator: Comparator<Issues_details> = Comparator<Issues_details> { e1, e2 ->

            val v1: String = e1.date.toString()
            val v2: String = e2.date.toString()
            v2.compareTo(v1)
        }
        Collections.sort(issues_detailsList, valueComparator)
        val sortedByDate: LinkedHashMap<Long, MutableList<Issues_details>> = LinkedHashMap<Long, MutableList<Issues_details>>(issues_detailsList.size)
        for (issues_details in issues_detailsList) {
            val hashMapKey: Long = issues_details.date
            if (sortedByDate.containsKey(hashMapKey)) {
                // The key is already in the HashMap; add the pojo object
                // against the existing key.
                sortedByDate[hashMapKey]!!.add(issues_details)
            } else {
                // The key is not there in the HashMap; create a new key-value pair
                val list: MutableList<Issues_details> = ArrayList<Issues_details>()
                list.add(issues_details)
                sortedByDate[hashMapKey] = list
            }
        }
        return sortedByDate
    }
}