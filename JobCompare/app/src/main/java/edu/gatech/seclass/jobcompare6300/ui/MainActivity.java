package edu.gatech.seclass.jobcompare6300.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import edu.gatech.seclass.jobcompare6300.R;


public class MainActivity extends AppCompatActivity {

    private Button buttonAdjCompSetting;

    //jake
    private Button mBtncurrentjob;
    private Button mBtnjoboffer;
    private Button mBtnadjust;
    private Button mBtncompare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Amy
        buttonAdjCompSetting = (Button) findViewById(R.id.buttonComparisonSetting);

        //jake
        mBtncurrentjob = findViewById(R.id.mm_btncurrentjob);
        mBtnjoboffer = findViewById(R.id.mm_btnjoboffer);
        mBtncompare = findViewById(R.id.mm_btncomparejob);
    }


    public void handleClick(View view){
        Intent intent = null;
        switch (view.getId()){
            case R.id.buttonComparisonSetting:
                intent = new Intent(MainActivity.this, ComparisonSetting.class);
                break;
            case R.id.mm_btncurrentjob:
                intent = new Intent(MainActivity.this, CurrentjobActivity.class);
                break;
            case R.id.mm_btnjoboffer:
                intent = new Intent(MainActivity.this, JobofferActivity.class);
                break;
            case R.id.mm_btncomparejob:
                intent = new Intent(MainActivity.this, JobComparerActivity.class);
                break;
        }
        startActivity(intent);

    }




}