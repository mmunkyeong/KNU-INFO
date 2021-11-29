package com.example.knu_info;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private ArrayList<Chat> mDataset;
    String stMyEmail="";
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textView = (TextView) view.findViewById(R.id.name);
        }

        public TextView getTextView() {
            return textView;
        }
    }
    public int getItemViewType(int position){
        if(mDataset.get(position).email.equals(stMyEmail)){
            return 1;
        }
        else{
            return 2;
        }
    }
    /**
     * Initialize the dataset of the Adapter.
     */

    public MyAdapter(ArrayList<Chat> dataSet,String stEmail) {
        mDataset= dataSet;
        this.stMyEmail=stEmail;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_recyclerview, viewGroup, false);
        if(viewType ==1){
            view=LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.right_item_recyclerview, viewGroup, false);
        }
        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTextView().setText(mDataset.get(position).getText());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
