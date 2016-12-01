package codewizards.com.ua.fuzzydistance.logic;

import codewizards.com.ua.fuzzydistance.logic.FuzzyNumber;

/**
 * Created by Интернет on 29.11.2016.
 */

public class TriangleNumber extends FuzzyNumber {
    private double maxValue;

    public TriangleNumber(double leftBorder, double rightBorder, double maxValue) {
        super(leftBorder, rightBorder);
        this.maxValue = maxValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public double getMu(double x) {
        double result = 0;
        if(x >= getLeftBorder() && x <= getMaxValue()) {
            result = (x - getLeftBorder()) / (getMaxValue() - getLeftBorder());
        } else if(x > getMaxValue() && x <= getRightBorder()) {
            result = (getRightBorder() - x) / (getRightBorder() - getMaxValue());
        }
        return result;
    }
}
