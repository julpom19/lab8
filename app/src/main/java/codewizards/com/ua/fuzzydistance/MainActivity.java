package codewizards.com.ua.fuzzydistance;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import lecho.lib.hellocharts.view.LineChartView;

public class MainActivity extends AppCompatActivity {
    private EditText aLeftBound;
    private EditText aMaxValue;
    private EditText aRightBound;
    private EditText bLeftBound;
    private EditText bMaxValue;
    private EditText bRightBound;
    private EditText stepCount;
    private LineChartView chartView;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        aLeftBound = (EditText) findViewById(R.id.a_left_border);
        aMaxValue = (EditText) findViewById(R.id.a_max_value);
        aRightBound = (EditText) findViewById(R.id.a_right_border);
        bLeftBound = (EditText) findViewById(R.id.b_left_border);
        bMaxValue = (EditText) findViewById(R.id.b_max_value);
        bRightBound = (EditText) findViewById(R.id.b_right_border);
        stepCount = (EditText) findViewById(R.id.step_count);
        chartView = (LineChartView) findViewById(R.id.chart);
        tvResult = (TextView) findViewById(R.id.result);
        MainActivityPresenter presenter = new MainActivityPresenter(this);

        ImageView clearA = (ImageView) findViewById(R.id.clearA);
        clearA.setOnClickListener(presenter);
        ImageView clearB = (ImageView) findViewById(R.id.clearB);
        clearB.setOnClickListener(presenter);
        ImageView clearStepCount = (ImageView) findViewById(R.id.clearStepCount);
        clearStepCount.setOnClickListener(presenter);
        Button btnCalc = (Button) findViewById(R.id.btn_calc);
        btnCalc.setOnClickListener(presenter);


    }

    public void showErrorDialog(final String message) {
        AlertDialog aDialog = new AlertDialog.Builder(this).setMessage(message).setTitle("Помилка даних").create();
        aDialog.show();
    }

    public EditText getaLeftBound() {
        return aLeftBound;
    }

    public void setaLeftBound(EditText aLeftBound) {
        this.aLeftBound = aLeftBound;
    }

    public EditText getaMaxValue() {
        return aMaxValue;
    }

    public void setaMaxValue(EditText aMaxValue) {
        this.aMaxValue = aMaxValue;
    }

    public EditText getaRightBound() {
        return aRightBound;
    }

    public void setaRightBound(EditText aRightBound) {
        this.aRightBound = aRightBound;
    }

    public EditText getbLeftBound() {
        return bLeftBound;
    }

    public void setbLeftBound(EditText bLeftBound) {
        this.bLeftBound = bLeftBound;
    }

    public EditText getbMaxValue() {
        return bMaxValue;
    }

    public void setbMaxValue(EditText bMaxValue) {
        this.bMaxValue = bMaxValue;
    }

    public EditText getbRightBound() {
        return bRightBound;
    }

    public void setbRightBound(EditText bRightBound) {
        this.bRightBound = bRightBound;
    }

    public LineChartView getChartView() {
        return chartView;
    }

    public void setChartView(LineChartView chartView) {
        this.chartView = chartView;
    }

    public EditText getStepCount() {
        return stepCount;
    }

    public void setStepCount(EditText stepCount) {
        this.stepCount = stepCount;
    }

    public TextView getTvResult() {
        return tvResult;
    }

    public void setTvResult(TextView tvResult) {
        this.tvResult = tvResult;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
