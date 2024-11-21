package com.r4;

import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Student}.
 * TODO: Replace the implementation with code for your data type.
 */
public class StudentRecyclerViewAdapter extends RecyclerView.Adapter<StudentRecyclerViewAdapter.ViewHolder> {

    private final List<Student> Students;
    private StudentFragment.OnStudentListener listener;
    public StudentRecyclerViewAdapter(List<Student> items,StudentFragment.OnStudentListener listener) {
        Students = items;
        this.listener=listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item4,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = Students.get(position);
        holder.name_text.setText(Students.get(position).getName());
        holder.surname_txt.setText(Students.get(position).getSurname());
        holder.email_txt.setText(Students.get(position).getEmail());
        holder.skills_txt.setText(Students.get(position).getSkills());
        holder.time_txt.setText(Students.get(position).getTimeline());
        holder.evaluate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.EvaluateStudent(Students.get(position),Integer.valueOf(holder.rating_txt.getText().toString()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return Students.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView name_text;
        public final TextView surname_txt;
        public final TextView email_txt;
        public final TextView skills_txt;
        public final TextView time_txt;
        public final TextView rating_txt;
        public final Button evaluate_btn;
        public Student mItem;

        public ViewHolder(View view) {
            super(view);
            name_text = (TextView) view.findViewById(R.id.name);
            surname_txt = (TextView) view.findViewById(R.id.surname);
            email_txt = (TextView) view.findViewById(R.id.email);
            skills_txt = (TextView) view.findViewById(R.id.skills_overview);
            time_txt = (TextView) view.findViewById(R.id.timeline_overview);
            rating_txt= (TextView) view.findViewById(R.id.rating);
            evaluate_btn=  view.findViewById(R.id.evaluate);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + name_text.getText() + "'";
        }
    }
}