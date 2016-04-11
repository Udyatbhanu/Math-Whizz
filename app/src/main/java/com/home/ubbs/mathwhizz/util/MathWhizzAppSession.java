package com.home.ubbs.mathwhizz.util;

import com.home.ubbs.mathwhizz.data.Operator;
import com.home.ubbs.mathwhizz.data.Result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by udyatbhanu-mac on 4/5/16.
 */
public class MathWhizzAppSession {

    public static List<Operator> VALUES;

    public static int SET = -1;
    public static int SET_COUNTER = -1;
    public static int SIZE;
    public static Random RANDOM;
    private static boolean isSessionActive = false;


    private static List<Result> results;
    private MathWhizzAppSession() {


    }


    public static void startSession(List<Operator> operatorsSelected, int set) {
        initSession(operatorsSelected, set);
        setSessionActive(true);
    }

    public static void endSession(boolean endSession) {
        VALUES = null;
        SIZE = -1;
        RANDOM = null;
        SET_COUNTER = -1;
        SET = -1;
        isSessionActive = false;
    }

    private static void initSession(List<Operator> operatorsSelected, int set) {

//        VALUES = Collections.unmodifiableList(Arrays.asList(Operator.values()));
        SET = set;
        SET_COUNTER = set;
        VALUES = Collections.unmodifiableList(operatorsSelected);
        SIZE = VALUES.size();
        RANDOM = new Random();
        results = new ArrayList<>();
    }

    public static boolean isSessionActive() {
        return isSessionActive;
    }

    private static void setSessionActive(boolean sessionActive) {
        isSessionActive = sessionActive;
    }


    public static Random getRandom() {

        if (null != RANDOM) {
            return RANDOM;
        } else {
            RANDOM = new Random();
        }
        return null;
    }

    public static List<Result> getResults() {
        return results;
    }



    public static void addResult(Result result) {
        MathWhizzAppSession.results.add(result);
    }




}
