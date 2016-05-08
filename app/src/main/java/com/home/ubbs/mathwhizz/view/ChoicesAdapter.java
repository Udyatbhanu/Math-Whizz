package com.home.ubbs.mathwhizz.view;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.home.ubbs.mathwhizz.R;
import com.home.ubbs.mathwhizz.activity.PlayActivity;
import com.home.ubbs.mathwhizz.data.MathFunction;
import com.home.ubbs.mathwhizz.data.Result;
import com.home.ubbs.mathwhizz.util.MathWhizzAppSession;

/**
 * Created by udyatbhanu-mac on 4/4/16.
 */
public class ChoicesAdapter extends BaseAdapter implements View.OnClickListener {

    /**
     * Duration of wait
     **/
    private final int DISPLAY_LENGTH = 1500;
    Context context;
    Button[] buttons;
    MathFunction mathFunction;
    private long mLastClickTime = 0;

    public ChoicesAdapter(Context context, MathFunction mathFunction) {
        this.context = context;
        this.mathFunction = mathFunction;
        buttons = new Button[]{
                new Button(context), new Button(context), new Button(context), new Button(context)
        };
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return buttons[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public void onClick(View v) {

        if (null != context) {

            if (SystemClock.elapsedRealtime() - mLastClickTime < 4000) {
                return;
            }

            mLastClickTime = SystemClock.elapsedRealtime();
            MathWhizzAppSession.SET_COUNTER--;
            RelativeLayout resultLayout = (RelativeLayout) ((PlayActivity) context).findViewById(R.id.resultPanel);
            View view = v;
            String selected = view.getTag().toString();
            TextView expression = (TextView) ((PlayActivity) context).findViewById(R.id.expression);
            TextView resultText = (TextView) ((PlayActivity) context).findViewById(R.id.resultText);
            ImageView icon = (ImageView) ((PlayActivity) context).findViewById(R.id.icon);

            setResult(expression.getText().toString(),selected,rightAnswerSelected(selected));

            if (!rightAnswerSelected(selected)) {



                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    icon.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_wrong, context.getApplicationContext().getTheme()));
                } else {
                    icon.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_wrong));
                }
                resultText.setText(context.getString(R.string.wrong_text));

//                resultText.setText("Wrong Answer..!!");
            }
            resultLayout.animate().translationXBy(3000f).setDuration(1000);

            Handler handler = new Handler();

            if(MathWhizzAppSession.SET_COUNTER==0){
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((PlayActivity) context).onFinishedSession();
                    }
                }, DISPLAY_LENGTH);
            }else{

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((PlayActivity) context).onNextQuestion();
                    }
                }, DISPLAY_LENGTH);
            }


        }
    }

    public static class ViewHolder {
        public Button button;

    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        LayoutInflater inflater = LayoutInflater.from(context);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.choices_item, parent, false);
            viewHolder.button = (Button) convertView.findViewById(R.id.choice);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String option = Integer.toString(mathFunction.getOptions()[position]);
        viewHolder.button.setText(option);
        viewHolder.button.setTag(option);
        viewHolder.button.getBackground().setAlpha(128);
        viewHolder.button.setOnClickListener(this);


        return convertView;
    }


    private void setResult(String selectionOption, String selected, boolean isRightAnswerSelected){
        Result resultOfSession = new Result(selectionOption,selected,Integer.toString(mathFunction.getResult()), isRightAnswerSelected );
        MathWhizzAppSession.addResult(resultOfSession);
    }

    /**
     * @param selectionOption
     * @return
     */
    private boolean rightAnswerSelected(String selectionOption) {


        if (Integer.toString(mathFunction.getResult()).equalsIgnoreCase(selectionOption)) {
            return true;
        }
        return false;
    }

    public interface nextPageListener {
        void onNextQuestion();
        void onFinishedSession();
    }

}
