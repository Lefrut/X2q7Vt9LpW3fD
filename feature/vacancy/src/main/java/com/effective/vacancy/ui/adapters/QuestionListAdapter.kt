package com.effective.vacancy.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.effective.vacancy.R

class QuestionsListAdapter(
    context: Context,
    @LayoutRes
    private val resource: Int,
    private val questions: List<String>
) : ArrayAdapter<String>(context, resource, questions) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(resource, parent, false)
        val quesiton = questions[position]
        val textView = view.findViewById<TextView>(R.id.question)
        textView.text = quesiton
        return view
    }

}