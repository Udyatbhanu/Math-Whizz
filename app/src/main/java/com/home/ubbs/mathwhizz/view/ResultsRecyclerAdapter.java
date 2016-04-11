package com.home.ubbs.mathwhizz.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.home.ubbs.mathwhizz.R;
import com.home.ubbs.mathwhizz.activity.ResultsActivity;
import com.home.ubbs.mathwhizz.data.Result;

import java.util.List;

/**
 * Created by udyatbhanu-mac on 4/9/16.
 */
public class ResultsRecyclerAdapter extends RecyclerView.Adapter<ResultsRecyclerAdapter.ViewHolder> {

    private List<Result> mDataset;
    private static int VIEW_TYPE_FOOTER = 0;
    private static int VIEW_TYPE_CELL = 1;
    ResultsActivity activity;

    public ResultsRecyclerAdapter(List<Result> myDataset, ResultsActivity activity) {
        mDataset = myDataset;
        this.activity = activity;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView serialNo;
        public TextView expression;
        public TextView rightAnswer;
        public ImageView operatorIcon;
        public Button finishButton;


        public ViewHolder(View view) {
            super(view);
            serialNo = (TextView) view.findViewById(R.id.serial_no);
            expression = (TextView) view.findViewById(R.id.expression);
            rightAnswer = (TextView) view.findViewById(R.id.right_answer);
            operatorIcon = (ImageView) view.findViewById(R.id.operatorIcon);
            finishButton = (Button) view.findViewById(R.id.finishButton);
        }
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ResultsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
        View v = null;
        if (viewType == VIEW_TYPE_CELL) {
            // create a new view
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_result_item, parent, false);
            // set the view's size, margins, paddings and layout parameters
        } else {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_recycle_button, parent, false);
        }


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        if (position == mDataset.size()) {


            holder.finishButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.finishButtonClicked();
                }
            });

            return;
        }


        holder.serialNo.setText(String.format(activity.getString(R.string.serial_no_text), String.valueOf(position + 1)));


        holder.expression.setText(String.format(activity.getString(R.string.expression_text),
                mDataset.get(position).getExpression(),
                mDataset.get(position).getSelected()));


        if (!mDataset.get(position).isCorrectAnswer()) {
            holder.rightAnswer.setVisibility(View.VISIBLE);
            holder.rightAnswer.setText(String.format(activity.getString(R.string.right_answer_text), mDataset.get(position).getRightAnswer()));
            holder.operatorIcon.setImageResource(R.mipmap.ic_wrong);
        } else {
            holder.operatorIcon.setImageResource(R.mipmap.ic_right);
            holder.rightAnswer.setVisibility(View.INVISIBLE);
        }


    }


    @Override
    public int getItemViewType(int position) {
        return (position == mDataset.size()) ? VIEW_TYPE_FOOTER : VIEW_TYPE_CELL;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return mDataset.size() + 1;
    }


    public interface finishButtonListener {
        void finishButtonClicked();

    }
}
