package com.home.ubbs.mathwhizz.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.home.ubbs.mathwhizz.util.MathWhizzAppSession;

/**
 * Created by udyatbhanu-mac on 4/5/16.
 */
public class MathFunction implements Parcelable {

    private int firstNumber = -1;
    private int secondNumber = -1;


    private int result = -1;
    private Operator operator;

    private int option1;
    private int option2;
    private int option3;
    private int option4;


    private int[] options;


    public MathFunction() {


    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
        setOptions();
    }

    private void setOptions() {
        if (MathWhizzAppSession.getRandom() != null) {
            option1 = MathWhizzAppSession.getRandom().nextInt(50) + 1;
            option2 = MathWhizzAppSession.getRandom().nextInt(50) + 1;
            option3 = MathWhizzAppSession.getRandom().nextInt(50) + 1;
            option4 = result;

            int options[] = {option1, option2, option3, option4};

            for (int i = options.length - 1; i > 0; i--) {
                int index = MathWhizzAppSession.getRandom().nextInt(i + 1);
                // Simple swap
                int a = options[index];
                options[index] = options[i];
                options[i] = a;
            }
            setOptions(options);
        }


        //this will just pick up random value from array
//        List optionList = Collections.unmodifiableList(Arrays.asList(options);
//        int size = optionList.size();
//        optionList.get(MathWhizzAppSession.getRandom().nextInt(size));


    }

    public int[] getOptions() {
        return options;
    }

    public void setOptions(int[] options) {
        this.options = options;
    }

    @Override
    public String toString() {
        if (firstNumber > -1) {
            StringBuilder expressionStr = new StringBuilder();
            expressionStr.append(Integer.toString(firstNumber));
            switch (operator) {
                case ADDITION:
                    expressionStr.append(" + ");
                    setResult(getFirstNumber() + getSecondNumber());
                    break;
                case SUBTRACTION:
                    expressionStr.append(" - ");
                    setResult(getFirstNumber() - getSecondNumber());
                    break;
                case MULTIPLICATION:
                    expressionStr.append(" x ");
                    setResult(getFirstNumber() * getSecondNumber());
                    break;
                case DIVISION:
                    expressionStr.append(" ÷ ");
                    setResult(getFirstNumber() / getSecondNumber());
                    break;
            }
            expressionStr.append(Integer.toString(secondNumber));
            expressionStr.append(" = ");
            expressionStr.append(" ? ");
            setOptions();
            return expressionStr.toString();

        }

        return "";
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(firstNumber);
        dest.writeInt(secondNumber);
        dest.writeIntArray(options);
        dest.writeValue(operator);

    }

    private MathFunction(Parcel in) {
        this.firstNumber = in.readInt();
        this.secondNumber = in.readInt();
        this.options = in.createIntArray();
        this.operator = (Operator) in.readValue(MathFunction.class.getClassLoader());
    }

    public static final Parcelable.Creator<MathFunction> CREATOR = new Parcelable.Creator<MathFunction>() {

        @Override
        public MathFunction createFromParcel(Parcel source) {
            return new MathFunction(source);
        }

        @Override
        public MathFunction[] newArray(int size) {
            return new MathFunction[size];
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    };

}
