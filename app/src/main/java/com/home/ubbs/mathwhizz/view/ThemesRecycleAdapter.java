package com.home.ubbs.mathwhizz.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.home.ubbs.lib.theme.ThemeSettings;
import com.home.ubbs.mathwhizz.R;

/**
 * Created by udyatbhanu-mac on 5/4/16.
 */
public class ThemesRecycleAdapter extends RecyclerView.Adapter<ThemesRecycleAdapter.ViewHolder> {

    String []themes = {"Red","Pink","Purple","Blue", "Orange","Indigo"};

    int selectedPosition = 0;
    public ThemesRecycleAdapter() {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView themeTextView;
        public RelativeLayout themeLayout;
        public RadioButton choice;



        public ViewHolder(View view) {
            super(view);
            themeTextView = (TextView) view.findViewById(R.id.theme_text_view);
            themeLayout = (RelativeLayout) view.findViewById(R.id.theme_item_layout);
            choice = (RadioButton) view.findViewById(R.id.radio_button);

        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ThemesRecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_theme_item, parent, false);


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Context context = holder.itemView.getContext();
        holder.themeTextView.setText(themes[position]);
        String colorStr = "colorAccent"+themes[position];
        final String themeStr = "AppTheme"+themes[position];
        final int theme = getResourceId(context,themeStr,"style", context.getPackageName());



        holder.themeLayout.setBackgroundColor(context.getResources().getColor(getResourceId(context,colorStr,"color", context.getPackageName())));

        holder.choice.setChecked(position == selectedPosition);
        holder.choice.setTag(new Integer(position));

        holder.choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThemeSettings.style = theme;
                selectedPosition = (Integer)view.getTag();
                notifyDataSetChanged();
            }
        });


    }


    public static int getResourceId(Context context, String pVariableName, String pResourcename, String pPackageName)
    {
        try {
            return context.getResources().getIdentifier(pVariableName, pResourcename, pPackageName);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }


    @Override
    public int getItemCount() {
        return themes.length;
    }





}
