package edu.gatech.seclass.jobcompare6300.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.backend.JobCompareDBHandler;
import edu.gatech.seclass.jobcompare6300.model.ComparisonSettingModel;

public class ComparisonSetting extends AppCompatActivity {

    private Button buttonWtSave;
    private Button buttonWtCancel;
    private Button buttonWtReset;

    private EditText wtYearlySalary;
    private EditText wtYearlyBonus;
    private EditText wtLeave;
    private EditText wtMatLeave;
    private EditText wtLifeInsurance;

    private EditText wtYearlySalaryTitle;
    private EditText wtYearlyBonusTitle;
    private EditText wtLeaveTitle;
    private EditText wtMatLeaveTitle;
    private EditText wtLifInsuranceTitle;
    private EditText activityTitle;

    private ComparisonSettingModel comparisonSettingModel;

    private JobCompareDBHandler jobCompareDBHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparison_setting);

        buttonWtSave = (Button) findViewById(R.id.buttonWtSave);
        buttonWtCancel = (Button) findViewById(R.id.buttonWtCancel);
        buttonWtReset = (Button) findViewById(R.id.buttonWtReset);

        wtYearlySalary = (EditText) findViewById(R.id.editTextWtYearlySalary);
        wtYearlyBonus = (EditText) findViewById(R.id.editTextWtYearlyBonus);
        wtLeave = (EditText) findViewById(R.id.editTextWtLeave);
        wtMatLeave = (EditText) findViewById(R.id.editTextWtMatLeave);
        wtLifeInsurance = (EditText) findViewById(R.id.editTextWtLifeInsurance);

        wtYearlySalaryTitle = (EditText) findViewById(R.id.editTextWtYearlySalaryTitle);
        wtYearlyBonusTitle = (EditText) findViewById(R.id.editTextWtYearlyBonusTitle);
        wtLeaveTitle = (EditText) findViewById(R.id.editTextWtLeaveTitle);
        wtMatLeaveTitle = (EditText) findViewById(R.id.editTextWtMatLeaveTitle);
        wtLifInsuranceTitle = (EditText) findViewById(R.id.editTextWtLifeInsuranceTitle);
        activityTitle = findViewById(R.id.editTextCompSetting);

        wtYearlySalaryTitle.setFocusable(false);
        wtYearlyBonusTitle.setFocusable(false);
        wtLeaveTitle.setFocusable(false);
        wtMatLeaveTitle.setFocusable(false);
        wtLifInsuranceTitle.setFocusable(false);
        activityTitle.setFocusable(false);

        //TODO preload data from comparison setting model from DB to UI
        jobCompareDBHandler = new JobCompareDBHandler();
        jobCompareDBHandler.initialize(this);
        comparisonSettingModel = jobCompareDBHandler.retrieveComparisonSettingFromDb();

        if (comparisonSettingModel.getId() == 0) {
            //no record in comparison setting table. create the record
            comparisonSettingModel.setId(1);
            comparisonSettingModel.setWtYearlySalary(1);
            comparisonSettingModel.setWtYearlyBonus(1);
            comparisonSettingModel.setWtLeave(1);
            comparisonSettingModel.setWtMatLeave(1);
            comparisonSettingModel.setWtLifeInsurance(1);
            try {
                jobCompareDBHandler.insertComparisonSettings(comparisonSettingModel);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            //record found.
            wtYearlySalary.setText(String.valueOf(comparisonSettingModel.getWtYearlySalary()));
            wtYearlyBonus.setText(String.valueOf(comparisonSettingModel.getWtYearlyBonus()));
            wtLeave.setText(String.valueOf(comparisonSettingModel.getWtLeave()));
            wtMatLeave.setText(String.valueOf(comparisonSettingModel.getWtMatLeave()));
            wtLifeInsurance.setText(String.valueOf(comparisonSettingModel.getWtLifeInsurance()));
        }
    }




    public void handleClick(View view){

        switch (view.getId()){
            case R.id.buttonWtSave:
                String wtSalaryStr = wtYearlySalary.getText().toString();
                String wtBonusStr = wtYearlyBonus.getText().toString();
                String wtLeaveStr = wtMatLeave.getText().toString();
                String wtMatLeaveStr = wtMatLeave.getText().toString();
                String wtLifeInStr = wtLifeInsurance.getText().toString();

                //ui constrains
                if(wtSalaryStr.isEmpty()) {wtYearlySalary.setError("Please input value "); return;};
                if(wtBonusStr.isEmpty()) {wtYearlyBonus.setError("Please input value "); return;};
                if(wtLeaveStr.isEmpty()) {wtMatLeave.setError("Please input value "); return;};
                if(wtMatLeaveStr.isEmpty()) {wtMatLeave.setError("Please input value "); return;};
                if(wtLifeInStr.isEmpty()) {wtLifeInsurance.setError("Please input value "); return;};

                int wtSalaryInt = Integer.parseInt(wtSalaryStr);
                int wtBonusInt = Integer.parseInt(wtBonusStr);
                int wtLeaveInt = Integer.parseInt(wtLeaveStr);
                int wtMatLeaveInt = Integer.parseInt(wtMatLeaveStr);
                int wtLifeInInt = Integer.parseInt(wtLifeInStr);

                if (wtSalaryInt + wtBonusInt + wtLeaveInt + wtMatLeaveInt + wtLifeInInt == 0 ){
                    Toast.makeText(ComparisonSetting.this, "At least one weight should be greater than 0.", Toast.LENGTH_SHORT).show();

                }else {
                    comparisonSettingModel.setWtYearlySalary(wtSalaryInt);
                    comparisonSettingModel.setWtYearlyBonus(wtBonusInt);
                    comparisonSettingModel.setWtLeave(wtLeaveInt);
                    comparisonSettingModel.setWtMatLeave(wtMatLeaveInt);
                    comparisonSettingModel.setWtLifeInsurance(wtLifeInInt);

                    try {
                        jobCompareDBHandler.updateComparisonSettings(comparisonSettingModel);
                        Toast.makeText(ComparisonSetting.this, "Comparison Setting Saved.", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    finish();
                }
                break;
            case R.id.buttonWtCancel:
                finish();
                break;
            case R.id.buttonWtReset:
                wtYearlySalary.setText("1");
                wtYearlyBonus.setText("1");
                wtLeave.setText("1");
                wtMatLeave.setText("1");
                wtLifeInsurance.setText("1");

                comparisonSettingModel.setWtYearlySalary(1);
                comparisonSettingModel.setWtYearlyBonus(1);
                comparisonSettingModel.setWtLeave(1);
                comparisonSettingModel.setWtMatLeave(1);
                comparisonSettingModel.setWtLifeInsurance(1);

                try {
                    jobCompareDBHandler.updateComparisonSettings(comparisonSettingModel);
                    Toast.makeText(ComparisonSetting.this, "Comparison Setting Reset Completed.", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                finish();
                break;

        }

    }

}