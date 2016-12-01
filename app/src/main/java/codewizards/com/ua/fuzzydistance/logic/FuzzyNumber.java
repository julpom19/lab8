package codewizards.com.ua.fuzzydistance.logic;

/**
 * Created by Интернет on 29.11.2016.
 */

public abstract class FuzzyNumber {
    protected double leftBorder;
    protected double rightBorder;

    public FuzzyNumber(double leftBorder, double rightBorder) {
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
    }

    public abstract double getMu(double x);

    public double getLeftBorder() {
        return leftBorder;
    }

    public void setLeftBorder(double leftBorder) {
        this.leftBorder = leftBorder;
    }

    public double getRightBorder() {
        return rightBorder;
    }

    public void setRightBorder(double rightBorder) {
        this.rightBorder = rightBorder;
    }
}
