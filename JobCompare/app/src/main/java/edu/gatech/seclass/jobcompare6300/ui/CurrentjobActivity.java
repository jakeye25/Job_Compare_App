package edu.gatech.seclass.jobcompare6300.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.backend.JobCompareDBHandler;
import edu.gatech.seclass.jobcompare6300.model.JobDetails;

public class CurrentjobActivity extends AppCompatActivity {

    private EditText cjTitle;
    private EditText cjCompany;
    private EditText cjLocation;
    private EditText cjCostofLiving;
    private EditText cjSalary;
    private EditText cjBonus;
    private EditText cjLeave;
    private EditText cjMPLeave;
    private EditText cjInsurance;

    private Button cjButtonsave;
    private Button cjButtoncancel;

    private EditText cjTitletext;
    private EditText cjCompanytext;
    private EditText cjLocationtext;
    private EditText cjCostofLivingtext;
    private EditText cjSalarytext;
    private EditText cjBonustext;
    private EditText cjLeavetext;
    private EditText cjMPLeavetext;
    private EditText cjInsurancetext;

    private JobDetails currentJob;
    private JobCompareDBHandler jobCompareDBHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currentjob);

        cjTitle = findViewById(R.id.cj_title_input);
        cjCompany = findViewById(R.id.cj_company_input);
        cjLocation = findViewById(R.id.cj_location_input);
        cjCostofLiving = findViewById(R.id.cj_costofliving_input);
        cjSalary = findViewById(R.id.cj_salary_input);
        cjBonus = findViewById(R.id.cj_bonus_input);
        cjLeave = findViewById(R.id.cj_leave_input);
        cjMPLeave = findViewById(R.id.cj_mpleave_input);
        cjInsurance = findViewById(R.id.cj_insurance_input);

        cjButtonsave = findViewById(R.id.buttonCJSave);
        cjButtoncancel = findViewById(R.id.buttonCJCancel);

        cjTitletext = findViewById(R.id.cj_title);
        cjCompanytext = findViewById(R.id.cj_company);
        cjLocationtext = findViewById(R.id.cj_location);
        cjCostofLivingtext = findViewById(R.id.cj_costofliving);
        cjSalarytext = findViewById(R.id.cj_salary);
        cjBonustext = findViewById(R.id.cj_bonus);
        cjLeavetext = findViewById(R.id.cj_leave);
        cjMPLeavetext = findViewById(R.id.cj_mpleave);
        cjInsurancetext = findViewById(R.id.cj_insurance);

        cjTitletext.setFocusable(false);
        cjCompanytext.setFocusable(false);
        cjLocationtext.setFocusable(false);
        cjCostofLivingtext.setFocusable(false);
        cjSalarytext.setFocusable(false);
        cjBonustext.setFocusable(false);
        cjLeavetext.setFocusable(false);
        cjMPLeavetext.setFocusable(false);
        cjInsurancetext.setFocusable(false);

        // Create an instance of JobDatabaseHelper
        jobCompareDBHandler = new JobCompareDBHandler();
        jobCompareDBHandler.initialize(this);

        // Retrieve the current job details from the database
        currentJob = jobCompareDBHandler.retrieveCurrentJobDetails();

        if(currentJob.getId()==0){
            cjSalary.setText(String.valueOf(0));
            cjBonus.setText(String.valueOf(0));
            cjCostofLiving.setText(String.valueOf(0));
            cjLeave.setText(String.valueOf(0));
            cjMPLeave.setText(String.valueOf(0));
            cjInsurance.setText(String.valueOf(0));
        } else {
            // Set the retrieved values in the UI fields
            cjTitle.setText(currentJob.getTitle());
            cjCompany.setText(currentJob.getCompany());
            cjLocation.setText(currentJob.getLocation());
            cjCostofLiving.setText(String.valueOf(currentJob.getCostOfLiving()));
            cjSalary.setText(String.valueOf(currentJob.getYearlySalary()));
            cjBonus.setText(String.valueOf(currentJob.getYearlyBonus()));
            cjLeave.setText(String.valueOf(currentJob.getLeave()));
            cjMPLeave.setText(String.valueOf(currentJob.getMPLeave()));
            cjInsurance.setText(String.valueOf(currentJob.getInsurance()));
        }

        cjButtonsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Convert the relevant fields to appropriate types (int, double)
                // before saving them to the database
                String cjTitleStr = cjTitle.getText().toString();
                String cjCompanyStr = cjCompany.getText().toString();
                String cjLocationStr = cjLocation.getText().toString();
//                int cjCostofLivingInt = Integer.parseInt(cjCostofLiving.getText().toString());
                String cjCostofLivingStr = cjCostofLiving.getText().toString();
                Double cjSalaryDouble = Double.parseDouble(cjSalary.getText().toString());
                Double cjBonusDouble = Double.parseDouble(cjBonus.getText().toString());
                int cjLeaveInt = Integer.parseInt(cjLeave.getText().toString());
                int cjMPLeaveInt = Integer.parseInt(cjMPLeave.getText().toString());
                int cjInsuranceInt = Integer.parseInt(cjInsurance.getText().toString());

                //Constrain of input
                if(cjTitleStr.isEmpty()) {cjTitle.setError("Invalid Title Entry"); return;};
                if(cjCompanyStr.isEmpty()) {cjCompany.setError("Invalid Company Entry"); return;};
                if(cjLocationStr.isEmpty()) {cjLocation.setError("Invalid Location Entry"); return;};
                if(cjCostofLivingStr.isEmpty()) {cjLocation.setError("Invalid Location Entry"); return;};
                if(Integer.parseInt(cjCostofLivingStr)<0) {cjCostofLiving.setError("Invalid Cost of Living Entry"); return;};
                if(cjSalaryDouble<0) {cjSalary.setError("Invalid Salary Entry"); return;};
                if(cjBonusDouble<0) {cjBonus.setError("Invalid Bonus Entry"); return;};
                if(cjLeaveInt<0 || cjLeaveInt>30) {cjLeave.setError("Invalid Leave Entry"); return;};
                if(cjMPLeaveInt<0 || cjMPLeaveInt>20) {cjMPLeave.setError("Invalid Maternity Leave Entry"); return;};
                if(cjInsuranceInt<0 || cjMPLeaveInt>10) {cjInsurance.setError("Invalid Insurance Entry"); return;};



                // Update the current job details in the database
                currentJob.setTitle(cjTitleStr);
                currentJob.setCompany(cjCompanyStr);
                currentJob.setLocation(cjLocationStr);
                currentJob.setCostOfLiving(Integer.parseInt(cjCostofLivingStr));
                currentJob.setYearlySalary(cjSalaryDouble);
                currentJob.setYearlyBonus(cjBonusDouble);
                currentJob.setLeave(cjLeaveInt);
                currentJob.setMPLeave(cjMPLeaveInt);
                currentJob.setLifeInsurance(cjInsuranceInt);
                currentJob.setJobType(1);//for current jon type is 1

                // Save the updated current job details to the database
                if(currentJob.getId() == 0){
                    currentJob.setId(1);
                    jobCompareDBHandler.addEntryInJobDetails(currentJob);
                }
                else {
                    jobCompareDBHandler.updateCurrentJobDetails(currentJob);
                }

                finish();
                Intent intent = new Intent(CurrentjobActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        cjButtoncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CurrentjobActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
