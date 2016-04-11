package com.home.ubbs.mathwhizz.util;

import com.home.ubbs.mathwhizz.data.MathFunction;

import java.util.Random;

/**
 * Created by udyatbhanu-mac on 4/5/16.
 */
public class MathWhizzUtil {

    public static MathFunction generateMathFunction() {
        MathFunction mathFunction = new MathFunction();
        Random random = MathWhizzAppSession.getRandom();


        mathFunction.setFirstNumber(random.nextInt(10) + 1);
        mathFunction.setSecondNumber(random.nextInt(10) + 1);

//        int pick = random.nextInt(Operator.values().length);
//        mathFunction.setOperator(Operator.values()[pick]);

        mathFunction.setOperator(MathWhizzAppSession.VALUES.get(MathWhizzAppSession.RANDOM.
                nextInt(MathWhizzAppSession.SIZE)));

        return mathFunction;
    }


}
