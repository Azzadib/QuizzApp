package com.adib.eurekatest

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView

class ExpandableAdapter internal constructor(
        private val context: Context,
        private val titleList: List<String>,
        private val itemList: HashMap<String, List<String>>
        ): BaseExpandableListAdapter() {

    override fun getGroupCount(): Int {
        return this.titleList.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return this.itemList[this.titleList[groupPosition]]!!.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return this.titleList[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return this.itemList[this.titleList[groupPosition]]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
       var convertViews = convertView
        val listTitle = getGroup(groupPosition) as String
        if (convertView == null) {
            val layoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertViews = layoutInflater.inflate(R.layout.list_title, null)
        }
        val listTitleTextView: TextView = convertViews!!.findViewById(R.id.expandable_title)
        listTitleTextView.setTypeface(null, Typeface.BOLD)
        listTitleTextView.text = listTitle
        return convertViews
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertViews: View?, parent: ViewGroup?): View {
        var convertView = convertViews
        val listText = getChild(groupPosition, childPosition) as String
        if (convertView == null) {
            val layoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.list_item, null)
        }
        val listTextView: TextView = convertView!!.findViewById(R.id.expanded_item)
        listTextView.text = listText
        return convertView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}