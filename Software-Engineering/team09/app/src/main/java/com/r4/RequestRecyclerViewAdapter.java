package com.r4;

import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Request}.
 * TODO: Replace the implementation with code for your data type.
 */
public class RequestRecyclerViewAdapter extends RecyclerView.Adapter<RequestRecyclerViewAdapter.ViewHolder>{

    private final List<Request> requests;
    private RequestFragment.OnRequestListener listener;

    public RequestRecyclerViewAdapter(List<Request> items, RequestFragment.OnRequestListener listener) {
        this.listener = listener;
        requests = items;
    }

    @Override
    public RequestRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item2,parent,false);
        return new RequestRecyclerViewAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = requests.get(position);
        holder.skillsText.setText(requests.get(position).getSender().getSkills());
        holder.timelineText.setText(requests.get(position).getSender().getTimeline());
        holder.rating.setText(requests.get(position).getSender().getTotalEvaluation() +"/5.0");
        holder.accept_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.AcceptSelection(requests.get(position));
                removeAt(position);
            }
        });
        holder.decline_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.DeclineSelection(requests.get(position));
                removeAt(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return requests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView skillsText;
        public final TextView timelineText;
        public final TextView rating;
        public Request mItem;
        public final Button accept_button;
        public final Button decline_button;


        public ViewHolder(View view) {
            super(view);

            skillsText = (TextView) view.findViewById(R.id.skills);
            timelineText = (TextView) view.findViewById(R.id.timeline);
            rating=(TextView) view.findViewById(R.id.student_rating);
            accept_button=  view.findViewById(R.id.accept_button);
            decline_button = view.findViewById(R.id.decline_button);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + skillsText.getText() + "'";
        }
    }
    public void removeAt(int position) {
        requests.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, requests.size());
    }
}