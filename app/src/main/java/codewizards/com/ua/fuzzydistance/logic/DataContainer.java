package codewizards.com.ua.fuzzydistance.logic;

import java.util.List;

/**
 * Created by Интернет on 29.11.2016.
 */

public class DataContainer {
    private static DataContainer dataContainer = new DataContainer();
    private DataContainer() {}
    public static DataContainer getInstance() {
        return dataContainer;
    }

    private FuzzyNumber a;
    private FuzzyNumber b;
    private Double stepCount;
    private double euqlidDistance;
    private double hemmingDistance;
    private List<Singleton> hemmingSinletons;
    private List<Singleton> euqlidSinletons;


    public Double getStepCount() {
        return stepCount;
    }

    public void setStepCount(Double stepCount) {
        this.stepCount = stepCount;
    }

    public double getEuqlidDistance() {
        return euqlidDistance;
    }

    public void setEuqlidDistance(double euqlidDistance) {
        this.euqlidDistance = euqlidDistance;
    }

    public List<Singleton> getEuqlidSinletons() {
        return euqlidSinletons;
    }

    public void setEuqlidSinletons(List<Singleton> euqlidSinletons) {
        this.euqlidSinletons = euqlidSinletons;
    }

    public double getHemmingDistance() {
        return hemmingDistance;
    }

    public void setHemmingDistance(double hemmingDistance) {
        this.hemmingDistance = hemmingDistance;
    }

    public List<Singleton> getHemmingSinletons() {
        return hemmingSinletons;
    }

    public void setHemmingSinletons(List<Singleton> hemmingSinletons) {
        this.hemmingSinletons = hemmingSinletons;
    }

    public FuzzyNumber getB() {
        return b;
    }

    public void setB(FuzzyNumber b) {
        this.b = b;
    }

    public FuzzyNumber getA() {
        return a;
    }

    public void setA(FuzzyNumber a) {
        this.a = a;
    }
}
