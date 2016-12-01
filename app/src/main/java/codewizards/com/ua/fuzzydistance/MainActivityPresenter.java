package codewizards.com.ua.fuzzydistance;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import codewizards.com.ua.fuzzydistance.logic.Calculator;
import codewizards.com.ua.fuzzydistance.logic.DataContainer;
import codewizards.com.ua.fuzzydistance.logic.FuzzyNumber;
import codewizards.com.ua.fuzzydistance.logic.Singleton;
import codewizards.com.ua.fuzzydistance.logic.TriangleNumber;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by Интернет on 29.11.2016.
 */

public class MainActivityPresenter implements View.OnClickListener {
    MainActivity view;
    EditText aLeftBound;
    EditText aMaxValue;
    EditText aRightBound;
    EditText bLeftBound;
    EditText bMaxValue;
    EditText bRightBound;
    EditText stepCount;
    DataContainer dataContainer;
    LineChartView chart;

    public MainActivityPresenter(MainActivity view) {
        this.view = view;
        aLeftBound = view.getaLeftBound();
        aMaxValue = view.getaMaxValue();
        aRightBound = view.getaRightBound();
        bLeftBound = view.getbLeftBound();
        bMaxValue = view.getbMaxValue();
        bRightBound = view.getbRightBound();
        stepCount = view.getStepCount();
        chart = view.getChartView();
        dataContainer = DataContainer.getInstance();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clearA:
                clearA();
                break;
            case R.id.clearB:
                clearB();
                break;
            case R.id.clearStepCount:
                clearX();
                break;
            case R.id.btn_calc:
                if(checkEmpty()) {
                    makeCalculation();
                }
                break;
        }
    }

    public void clearA() {
        aLeftBound.setText("");
        aMaxValue.setText("");
        aRightBound.setText("");
    }

    public void clearB() {
        bLeftBound.setText("");
        bMaxValue.setText("");
        bRightBound.setText("");
    }

    public void clearX() {
        stepCount.setText("");
    }

    public boolean checkEmpty() {
        boolean isOk = true;
        if(aLeftBound.getText().toString().equals("") || aMaxValue.getText().toString().equals("") ||
                aRightBound.getText().toString().equals("")) {
            isOk = false;
        }
        if(bLeftBound.getText().toString().equals("") || bMaxValue.getText().toString().equals("") ||
                bRightBound.getText().toString().equals("")) {
            isOk = false;
        }
        if(stepCount.getText().toString().equals("")) {
            isOk = false;
        }
        if(!isOk) {
            view.showErrorDialog("Введіть всі значення");
        }
        return isOk;
    }

    public void makeCalculation() {
        if(checkEmpty()) {
            boolean error = false;
            double aLeft = 0;
            double aMax = 0;
            double aRight = 0;
            double bLeft = 0;
            double bMax = 0;
            double bRight = 0;
            double steps = 0;
            try {
                aLeft = Double.valueOf(aLeftBound.getText().toString());
                aMax = Double.valueOf(aMaxValue.getText().toString());
                aRight = Double.valueOf(aRightBound.getText().toString());
                if(!(aLeft <= aMax && aMax <= aRight)) {
                    view.showErrorDialog("Помилка у визначені нечіткого числа А. Ліва " +
                            "границя має бути меншою або дорівнювати моді, а мода має бути меншою або дорівнювати " +
                            "правій границі.");
                    error = true;
                }
            } catch (NumberFormatException ex) {
                view.showErrorDialog("Помилка у визначені нечіткого числа А. Неправильний формат даних");
                error = true;
            }
            try {
                bLeft = Double.valueOf(bLeftBound.getText().toString());
                bMax = Double.valueOf(bMaxValue.getText().toString());
                bRight = Double.valueOf(bRightBound.getText().toString());
                if(!(bLeft <= bMax && bMax <= bRight)) {
                    view.showErrorDialog("Помилка у визначені нечіткого числа В. Ліва " +
                            "границя має бути меншою або дорівнювати моді, а мода має бути меншою або дорівнювати " +
                            "правій границі.");

                    error = true;
                }
            } catch (NumberFormatException ex) {
                view.showErrorDialog("Помилка у визначені нечіткого числа B. Неправильний формат даних");
                error = true;
            }
            try {
                steps = Double.valueOf(stepCount.getText().toString());
            } catch (NumberFormatException ex) {
                view.showErrorDialog("Помилка у визначені числа x. Неправильний формат даних");
                error = true;
            }
            if(error) {
                return;
            }
            TriangleNumber a = new TriangleNumber(aLeft, aRight, aMax);
            TriangleNumber b = new TriangleNumber(bLeft, bRight, bMax);
            dataContainer.setA(a);
            dataContainer.setB(b);
            dataContainer.setStepCount(steps);
            ///////////
            Calculator calculator = new Calculator();
            double hemmingDistance = calculator.getHemmingDistance(a, b, (int) steps);
            double euqlidDistance = calculator.getEuqlidDistance(a, b, (int) steps);
            List<Singleton> hemmingSingletones = calculator.getHemmingSingletons(a, b, (int) steps);
            List<Singleton> euqlidSingletones = calculator.getEuqlidSingletons(a, b, (int) steps);
            dataContainer.setHemmingDistance(hemmingDistance);
            dataContainer.setEuqlidDistance(euqlidDistance);
            dataContainer.setEuqlidSinletons(euqlidSingletones);
            dataContainer.setHemmingSinletons(hemmingSingletones);
            ///////////
            showResults();
        }
    }

    private void showResults() {
        drawChart();
        showC();
        //view.onChangedResult();
    }

    private void showC() {
        String result = "Лінійна Хеммінгова відстань: " + roundUp(dataContainer.getHemmingDistance(),3) +
                "\n" + "Квадратична Евклідова відстань: " + roundUp(dataContainer.getEuqlidDistance(),3);
        view.getTvResult().setText(result);
    }

    public BigDecimal roundUp(double value, int digits){
        return new BigDecimal(""+value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }

    private void drawChart() {

        List<Line> lines = new ArrayList();

        lines.add(getLine(dataContainer.getA(), Color.parseColor("#ff5340")));
        lines.add(getLine(dataContainer.getB(), Color.parseColor("#9340ff")));
        lines.add(getLine(dataContainer.getHemmingSinletons(), Color.parseColor("#208139")));
        lines.add(getLine(dataContainer.getEuqlidSinletons(), Color.parseColor("#ff40e2")));
        LineChartData data = new LineChartData(lines);

//        Axis axisX = new Axis().setHasLines(true);
//        List<AxisValue> axisXValueList = new ArrayList<>();
//        axisXValueList.addAll(getAxisXValueList(dataContainer.getA()));
//        axisXValueList.addAll(getAxisXValueList(dataContainer.getB()));
//        axisXValueList.addAll(getAxisXValueList(dataContainer.getC()));
//        double x = dataContainer.getX();
//        axisXValueList.add(new AxisValue((float) x));
//        axisX.setValues(axisXValueList);
//
//        for(AxisValue axisValue : axisXValueList) {
//            Log.d("myTag", axisValue.getValue() + " ");
//        }
//        axisX.setAutoGenerated(false);
//
//        Log.d("myTag", "max = " + axisX.getMaxLabelChars());


        Axis axisY = new Axis().setHasLines(true);
        List<AxisValue> axisYValueList = new ArrayList<>();
        axisYValueList.add(new AxisValue(0));
        axisYValueList.add(new AxisValue(1));
        axisY.setValues(axisYValueList);

       // data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);

//        data.setBaseValue(Float.NEGATIVE_INFINITY);
        chart.setLineChartData(data);

    }

    private Line getLine(FuzzyNumber number, int color) {
        List<PointValue> values = new ArrayList<>();
        values.add(new PointValue((float) number.getLeftBorder(), 0));
        values.add(new PointValue((float) ((TriangleNumber) number).getMaxValue(), 1));
        values.add(new PointValue((float) number.getRightBorder(), 0));
        Line line = new Line(values);
        line.setColor(color);
        return line;
    }

    private Line getLine(double number, int color) {
        List<PointValue> values = new ArrayList<>();
        values.add(new PointValue((float) number, 0));
        values.add(new PointValue((float) number, 1));
        Line line = new Line(values);
        line.setColor(color);
        return line;
    }

    private Line getLine(List<Singleton> singletons, int color) {
        List<PointValue> values = new ArrayList<>();
        for(int i = 0; i < singletons.size(); i++) {
            values.add(new PointValue((float) singletons.get(i).getX(), (float) singletons.get(i).getMu()));
        }
        Line line = new Line(values);
        line.setHasPoints(false);
        line.setColor(color);
        return line;
    }

//    private List<AxisValue> getAxisXValueList(TriangleNumber number) {
//        List<AxisValue> axisXValueList = new ArrayList<>();
//        axisXValueList.add(new AxisValue((float) number.getLeftBorder()));
//        axisXValueList.add(new AxisValue((float) number.getMaxXValue()));
//        axisXValueList.add(new AxisValue((float) number.getRightBorder()));
//        return axisXValueList;
//    }


}
