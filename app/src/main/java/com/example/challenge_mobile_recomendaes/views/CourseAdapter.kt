package com.example.challenge_mobile_recomendaes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge_mobile_recomendaes.R
import com.example.challenge_mobile_recomendaes.models.Courses
import com.example.challenge_mobile_recomendaes.views.HomeActivity

class CourseAdapter(private val courses: List<Courses>, homeActivity: HomeActivity) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val courseTitle: TextView = itemView.findViewById(R.id.text_view_course_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_course, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = courses[position]
        holder.courseTitle.text = course.title.toString()
    }

    override fun getItemCount() = courses.size
}
