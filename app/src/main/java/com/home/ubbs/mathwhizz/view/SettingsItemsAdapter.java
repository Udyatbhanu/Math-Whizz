package com.home.ubbs.mathwhizz.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.home.ubbs.mathwhizz.R;
import com.home.ubbs.mathwhizz.activity.SettingsActivity;
import com.home.ubbs.mathwhizz.data.Operator;

/**
 * Created by udyatbhanu-mac on 4/3/16.
 */
public class SettingsItemsAdapter extends BaseAdapter {
    Context context;

    private int[] drawables;
    private String[] operators;


    public SettingsItemsAdapter(Context context) {
        this.context = context;
        this.drawables = new int[]{R.mipmap.add, R.mipmap.sub, R.mipmap.multiply, R.mipmap.divide};
        this.operators = new String[]{"Add", "Subtract", "Multiply", "Divide"};
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    public static class ViewHolder {
        public TextView textView;
        public ImageView operatorSymbol;
        public CheckBox selected;
        public ImageView optionLayout;
        public RelativeLayout parentLayout;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         ViewHolder viewHolder = new ViewHolder();
        LayoutInflater inflater = LayoutInflater.from(context);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.operator);
            viewHolder.operatorSymbol = (ImageView) convertView.findViewById(R.id.operatorIcon);
            viewHolder.selected = (CheckBox) convertView.findViewById(R.id.checkBox);
            viewHolder.optionLayout = (ImageView) convertView.findViewById(R.id.background);
            viewHolder.parentLayout = (RelativeLayout) convertView.findViewById(R.id.parentLayout);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(operators[position]);
        if (position == 0) {
            viewHolder.optionLayout.setImageResource(R.drawable.top);
        } else if (position == 3) {
            viewHolder.optionLayout.setImageResource(R.drawable.bottom);
        } else {
            viewHolder.optionLayout.setImageResource(R.drawable.mid);
        }

        viewHolder.textView.setText(Operator.values()[position].toString());
        viewHolder.operatorSymbol.setImageResource(drawables[position]);
        viewHolder.selected.setTag(Operator.values()[position]);
//        viewHolder.parentLayout.setTag(Operator.values()[position]);

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                CheckBox checkStateBox = (CheckBox)((ViewGroup)v).findViewById(R.id.checkBox);
               if(checkStateBox.isChecked()){
                   checkStateBox.setChecked(false);
               }else{
                   checkStateBox.setChecked(true);
               }

            }
        });

        viewHolder.selected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                           @Override
                                                           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                               ((SettingsActivity) context).onSelectStateChanged((Operator) buttonView.getTag(), isChecked);
                                                           }
                                                       }
        );

        return convertView;
    }

    public interface CheckStateCallback {
        void onSelectStateChanged(Operator operator, boolean isChecked);
    }
}
