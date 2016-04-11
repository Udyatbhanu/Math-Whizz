package com.home.ubbs.mathwhizz.data;

/**
 * Created by udyatbhanu-mac on 4/9/16.
 */
public class Result {


    private String expression;
    private String rightAnswer;



    private String selected;



    private boolean isCorrectAnswer;
    private Operator operator;

    public Result(String expression,String selected,  String rightAnswer, boolean isCorrectAnswer) {
        this.expression = expression;
        this.rightAnswer = rightAnswer;
        this.selected = selected;
        this.isCorrectAnswer = isCorrectAnswer;
    }


    public boolean isCorrectAnswer() {
        return isCorrectAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        isCorrectAnswer = correctAnswer;
    }
    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }



}
