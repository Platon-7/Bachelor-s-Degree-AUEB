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
public class TeamRecyclerViewAdapter extends RecyclerView.Adapter<TeamRecyclerViewAdapter.ViewHolder> {

    private final List<Team> teams;
    private TeamFragment.OnInteractionListener listener;
    public TeamRecyclerViewAdapter(List<Team> items,TeamFragment.OnInteractionListener listener) {
        teams = items;
        this.listener=listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = teams.get(position);
        holder.req_text.setText(teams.get(position).getRequirements());
        holder.limit_txt.setText(teams.get(position).getMembers().size()+"/"+
                                 teams.get(position).getProject().getMaxNumber());
        holder.apply_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.TeamSelection(teams.get(position));
                removeAt(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView req_text;
        public final TextView limit_txt;
        public final Button apply_btn;
        public Team mItem;

        public ViewHolder(View view) {
            super(view);
            req_text = (TextView) view.findViewById(R.id.requirements);
            limit_txt = (TextView) view.findViewById(R.id.team_limit);
            apply_btn=  view.findViewById(R.id.apply_btn);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + req_text.getText() + "'";
        }
    }
    public void removeAt(int position) {
        teams.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, teams.size());
    }
}