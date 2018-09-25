package space.chekanov.accountcalculators;

import android.util.Log;

import java.math.BigDecimal;

public class PercentCalc {

    private static String TAG = "AccLogger";
    private static BigDecimal BigDecHundred = new BigDecimal(100);
    static int moneyRound = BigDecimal.ROUND_UP;

    static protected BigDecimal getPercentValue(BigDecimal sumWithPercent, BigDecimal sumWithoutPercent) {

        if (BuildConfig.DEBUG) {
            Log.i(TAG, "getPercentValue: sumWithPercent " + sumWithPercent.toString() + ", sumWithoutPercent " + sumWithoutPercent.toString());
        }

        return sumWithPercent.subtract(sumWithoutPercent);

    }

    static BigDecimal getPercentValueFromSumWithoutPercent(BigDecimal sumWithoutPercent, BigDecimal percent) {

        BigDecimal sumWP = new BigDecimal(sumWithoutPercent.floatValue());
        sumWP.setScale(2, moneyRound);

        if (BuildConfig.DEBUG) {
            Log.i(TAG, "getPercentValueFromSumWithoutPercent: sumWithoutPercent " + sumWithoutPercent.toString() + ", percent " + percent.toString() + ", sumWP, " + sumWP.toString());
        }

        return sumWP.multiply(percent).divide(BigDecHundred, moneyRound);
    }

    static BigDecimal getSumWithPercent(BigDecimal sumWithoutPercent, BigDecimal percent) {

        BigDecimal percentValue = getPercentValueFromSumWithoutPercent(sumWithoutPercent, percent);
        percentValue.setScale(2, moneyRound);

        if (BuildConfig.DEBUG) {
            Log.i(TAG, "getSumWithPercent: sumWithoutPercent " + sumWithoutPercent.toString() + ", percent " + percent.toString() + ", percentValue, " + percentValue.toString());
        }

        return sumWithoutPercent.add(percentValue);

    }

    static BigDecimal getSumWithoutPercent(BigDecimal sumWithPercent, BigDecimal percent) {

        if (BuildConfig.DEBUG) {
            Log.i(TAG, "getSumWithoutPercent: sumWithPercent " + sumWithPercent.toString() + ", percent " + percent.toString());
        }

        return (sumWithPercent.multiply(BigDecHundred)).divide(percent.add(BigDecHundred), moneyRound);

    }

    static BigDecimal getSumWithoutPercentFromPercentValue(BigDecimal value, BigDecimal percent) {

        BigDecimal val = new BigDecimal(value.floatValue());
        val.setScale(2, moneyRound);

        if (BuildConfig.DEBUG) {
            Log.i(TAG, "\n getSumWithoutPercentFromPercentValue: value " + value.toString() + ", percent " + percent.toString() + ", val " + val.toString());
        }

        BigDecimal answer = new BigDecimal(val.multiply(BigDecHundred).floatValue());

        if (BuildConfig.DEBUG) {
            Log.i(TAG, "answer " + answer.toString());
        }

        answer = answer.divide(percent, moneyRound);

        if (BuildConfig.DEBUG) {
            Log.i(TAG, "answer " + answer.toString());
        }

        return answer;

    }

    static BigDecimal getSumWithPercentFromPercentValue(BigDecimal value, BigDecimal percent) {

        if (BuildConfig.DEBUG) {
            Log.i(TAG, "\n getSumWithPercentFromPercentValue: value " + value.toString() + ", percent " + percent.toString());
        }

        BigDecimal divider = new BigDecimal(percent.floatValue());
        divider.setScale(2, moneyRound);

        if (BuildConfig.DEBUG) {
            Log.i(TAG, "percentForComp " + divider.toString());
        }

        BigDecimal multiplier = new BigDecimal(percent.add(BigDecHundred).floatValue());
        multiplier.setScale(2, moneyRound);

        if (BuildConfig.DEBUG) {
            Log.i(TAG, "multiplier " + multiplier.toString());
        }

        value = value.multiply(multiplier);

        if (BuildConfig.DEBUG) {
            Log.i(TAG, "value " + value.toString());
        }

        return value.divide(divider, moneyRound);

    }
}