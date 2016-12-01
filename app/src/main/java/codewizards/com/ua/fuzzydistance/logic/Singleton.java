package codewizards.com.ua.fuzzydistance.logic;

/**
 * Created by Интернет on 29.11.2016.
 */

public class Singleton {
    private double x;
    private double mu;

    public Singleton(double x, double mu) {
        this.mu = mu;
        this.x = x;
    }

    public double getMu() {
        return mu;
    }

    public void setMu(double mu) {
        this.mu = mu;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }
}
