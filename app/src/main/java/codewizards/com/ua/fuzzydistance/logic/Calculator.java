package codewizards.com.ua.fuzzydistance.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Интернет on 29.11.2016.
 */

public class Calculator {
    public double getHemmingDistance(FuzzyNumber a, FuzzyNumber b, int stepCount) {
        List<Singleton> hemmingSingletons = getHemmingSingletons(a, b, stepCount);
        double result = 0;
        for(int i = 0; i < hemmingSingletons.size(); i++) {
            result += hemmingSingletons.get(i).getMu();
        }
        return result;
    }

    public List<Singleton> getListOfSingletons(FuzzyNumber a, double[] x) {
        List<Singleton> singletons = new ArrayList<>();
        for(int i = 0; i < x.length; i++) {
            double mu = a.getMu(x[i]);
            Singleton singleton = new Singleton(x[i], mu);
            singletons.add(singleton);
        }
        return singletons;
    }

    public List<Singleton> getHemmingSingletons(FuzzyNumber a, FuzzyNumber b, int stepCount) {
        double[] x = calculateXValues(a, b, stepCount);
        List<Singleton> aSingletons = getListOfSingletons(a, x);
        List<Singleton> bSingletons = getListOfSingletons(b, x);
        List<Singleton> hemmingSingletons = new ArrayList<>();
        for(int i = 0; i < x.length; i++) {
            double mu = Math.abs(aSingletons.get(i).getMu() - bSingletons.get(i).getMu());
            Singleton singleton = new Singleton(x[i], mu);
            hemmingSingletons.add(singleton);
        }
        return hemmingSingletons;
    }

    public double[] calculateXValues(FuzzyNumber a, FuzzyNumber b, int stepCount) {
        double minLeft = a.getLeftBorder() < b.getLeftBorder() ? a.getLeftBorder() : b.getLeftBorder();
        double maxRight = a.getRightBorder() > b.getRightBorder() ? a.getRightBorder() : b.getRightBorder();
        double step = (maxRight - minLeft) / stepCount;
        double [] x = new double[stepCount + 1];
        double temp = minLeft;
        for(int i = 0; i < x.length; i++) {
            x[i] = temp;
            temp += step;
        }
        return x;
    }

    public List<Singleton> getEuqlidSingletons(FuzzyNumber a, FuzzyNumber b, int stepCount) {
        List<Singleton> euqlidSingletons = getHemmingSingletons(a, b, stepCount);
        for(int i = 0; i < euqlidSingletons.size(); i++) {
            double mu = euqlidSingletons.get(i).getMu() * euqlidSingletons.get(i).getMu();
            euqlidSingletons.get(i).setMu(mu);
        }
        return euqlidSingletons;
    }

    public double getEuqlidDistance(FuzzyNumber a, FuzzyNumber b, int stepCount) {
        List<Singleton> euqlidSingletons = getEuqlidSingletons(a, b, stepCount);
        double result = 0;
        for(int i = 0; i < euqlidSingletons.size(); i++) {
            result += euqlidSingletons.get(i).getMu();
        }
        result = Math.sqrt(result);
        return result;
    }

}
