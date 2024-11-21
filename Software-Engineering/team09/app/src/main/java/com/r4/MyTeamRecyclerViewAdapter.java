package com.r4;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Team}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyTeamRecyclerViewAdapter extends RecyclerView.Adapter<MyTeamRecyclerViewAdapter.ViewHolder> {

    private final List<Team> teams;
    private MyTeamFragment.OnMyTeamListener listener;
    public MyTeamRecyclerViewAdapter(List<Team> items,MyTeamFragment.OnMyTeamListener listener) {
        teams = items;
        this.listener=listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item3,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = teams.get(position);
        holder.course_text.setText(teams.get(position).getProject().getCourse().getTitle());
        holder.limit_txt.setText(teams.get(position).getMembers().size()+"/"+
                teams.get(position).getProject().getMaxNumber());
        holder.deadline_text.setText(teams.get(position).getProject().getDeadline());
        holder.view_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.TeamSelection(teams.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView course_text;
        public final TextView deadline_text;
        public final TextView limit_txt;
        public final Button view_btn;
        public Team mItem;

        public ViewHolder(View view) {
            super(view);
            course_text = (TextView) view.findViewById(R.id.course_name);
            limit_txt = (TextView) view.findViewById(R.id.project_limit);
            deadline_text=(TextView) view.findViewById(R.id.deadline);
            view_btn=  view.findViewById(R.id.view_btn);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + course_text.getText() + "'";
        }
    }
}