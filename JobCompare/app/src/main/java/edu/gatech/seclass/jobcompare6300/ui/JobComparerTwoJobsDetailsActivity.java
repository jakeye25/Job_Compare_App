package edu.gatech.seclass.jobcompare6300.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.backend.JobCompareDBHandler;
import edu.gatech.seclass.jobcompare6300.model.JobDetails;

public class JobComparerTwoJobsDetailsActivity extends AppCompatActivity {

    private JobCompareDBHandler jobCompareDBHandler;


    private EditText editTextJobTitle;
    private EditText editTextCompany;
    private EditText editTextLocation;
    private EditText editTextAdjSalary;
    private EditText editTextAdjBonus;
    private EditText editTextLeave;
    private EditText editTextMPLeave;
    private EditText editTextInsurance;

    private EditText editTextJobTitleFirst;
    private EditText editTextCompanyFirst;
    private EditText editTextLocationFirst;
    private EditText editTextAdjSalaryFirst;
    private EditText editTextAdjBonusFirst;
    private EditText editTextLeaveFirst;
    private EditText editTextMPLeaveFirst;
    private EditText editTextInsuranceFirst;
    private EditText editTextJobTitleSecond;
    private EditText editTextCompanySecond;
    private EditText editTextLocationSecond;
    private EditText editTextAdjSalarySecond;
    private EditText editTextAdjBonusSecond;
    private EditText editTextLeaveSecond;
    private EditText editTextMPLeaveSecond;
    private EditText editTextInsuranceSecond;

    private Button buttonCancel;
    private Button buttonReturnMain;

    JobDetails firstSelectedJob;

    JobDetails secondSelectedJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_comparer_two_jobs_details);

        //link UI to variables

        editTextJobTitle = findViewById(R.id.job_title);
        editTextCompany = findViewById(R.id.job_company);
        editTextLocation = findViewById(R.id.job_location);
        editTextAdjSalary = findViewById(R.id.job_salary);
        editTextAdjBonus = findViewById(R.id.job_bonus);
        editTextLeave= findViewById(R.id.job_leave);
        editTextMPLeave = findViewById(R.id.job_mpleave);
        editTextInsurance = findViewById(R.id.job_insurance);


        editTextJobTitleFirst = findViewById(R.id.job_title_first);
        editTextCompanyFirst = findViewById(R.id.job_company_first);
        editTextLocationFirst = findViewById(R.id.job_location_first);
        editTextAdjSalaryFirst = findViewById(R.id.job_salary_first);
        editTextAdjBonusFirst = findViewById(R.id.job_bonus_first);
        editTextLeaveFirst= findViewById(R.id.job_leave_first);
        editTextMPLeaveFirst = findViewById(R.id.job_mpleave_first);
        editTextInsuranceFirst = findViewById(R.id.job_insurance_first);

        editTextJobTitleSecond = findViewById(R.id.job_title_second);
        editTextCompanySecond = findViewById(R.id.job_company_second);
        editTextLocationSecond = findViewById(R.id.job_location_second);
        editTextAdjSalarySecond = findViewById(R.id.job_salary_second);
        editTextAdjBonusSecond = findViewById(R.id.job_bonus_second);
        editTextLeaveSecond= findViewById(R.id.job_leave_second);
        editTextMPLeaveSecond = findViewById(R.id.job_mpleave_second);
        editTextInsuranceSecond = findViewById(R.id.job_insurance_second);

        //set all fields not focusable

        editTextJobTitle.setFocusable(false);
        editTextCompany.setFocusable(false);
        editTextLocation.setFocusable(false);
        editTextAdjSalary.setFocusable(false);
        editTextAdjBonus.setFocusable(false);
        editTextLeave.setFocusable(false);
        editTextMPLeave.setFocusable(false);
        editTextInsurance.setFocusable(false);

        editTextJobTitleFirst.setFocusable(false);
        editTextCompanyFirst.setFocusable(false);
        editTextLocationFirst.setFocusable(false);
        editTextAdjSalaryFirst.setFocusable(false);
        editTextAdjBonusFirst.setFocusable(false);
        editTextLeaveFirst.setFocusable(false);
        editTextMPLeaveFirst.setFocusable(false);
        editTextInsuranceFirst.setFocusable(false);

        editTextJobTitleSecond.setFocusable(false);
        editTextCompanySecond.setFocusable(false);
        editTextLocationSecond.setFocusable(false);
        editTextAdjSalarySecond.setFocusable(false);
        editTextAdjBonusSecond.setFocusable(false);
        editTextLeaveSecond.setFocusable(false);
        editTextMPLeaveSecond.setFocusable(false);
        editTextInsuranceSecond.setFocusable(false);

        //Get first and second selected job id
        Intent intent = this.getIntent();
        int firstSelectedJobId = intent.getIntExtra("firstSelectedJob", 0);
        int secondSelectedJobId = intent.getIntExtra("secondSelectedJob", 0);

        //Get first and second selected job Details from DB
        jobCompareDBHandler = new JobCompareDBHandler();
        jobCompareDBHandler.initialize(this);
        firstSelectedJob = jobCompareDBHandler.retrieveSingleJobOffer(firstSelectedJobId);
        secondSelectedJob = jobCompareDBHandler.retrieveSingleJobOffer(secondSelectedJobId);

        //Set UI Values

        editTextJobTitleFirst.setText(String.valueOf(firstSelectedJob.getTitle()));
        editTextCompanyFirst.setText(String.valueOf(firstSelectedJob.getCompany()));
        editTextLocationFirst.setText(String.valueOf(firstSelectedJob.getLocation()));
        editTextAdjSalaryFirst.setText(String.valueOf(firstSelectedJob.getAdjYearlySalary()));
        editTextAdjBonusFirst.setText(String.valueOf(firstSelectedJob.getAdjYearlyBonus()));
        editTextLeaveFirst.setText(String.valueOf(firstSelectedJob.getLeave()));
        editTextMPLeaveFirst.setText(String.valueOf(firstSelectedJob.getMPLeave()));
        editTextInsuranceFirst.setText(String.valueOf(firstSelectedJob.getInsurance()));

        editTextJobTitleSecond.setText(String.valueOf(secondSelectedJob.getTitle()));
        editTextCompanySecond.setText(String.valueOf(secondSelectedJob.getCompany()));
        editTextLocationSecond.setText(String.valueOf(secondSelectedJob.getLocation()));
        editTextAdjSalarySecond.setText(String.valueOf(secondSelectedJob.getAdjYearlySalary()));
        editTextAdjBonusSecond.setText(String.valueOf(secondSelectedJob.getAdjYearlyBonus()));
        editTextLeaveSecond.setText(String.valueOf(secondSelectedJob.getLeave()));
        editTextMPLeaveSecond.setText(String.valueOf(secondSelectedJob.getMPLeave()));
        editTextInsuranceSecond.setText(String.valueOf(secondSelectedJob.getInsurance()));


        buttonCancel = findViewById(R.id.buttonJobCancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JobComparerTwoJobsDetailsActivity.this, JobComparerActivity.class);
                startActivity(intent);
                finish();
            }
        });
        buttonReturnMain = findViewById(R.id.buttonReturnMain);
        buttonReturnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToMainActivity();
            }
        });
    }
    private void navigateToJobComparerActivity() {
        Intent intent = new Intent(JobComparerTwoJobsDetailsActivity.this, JobComparerActivity.class);
        startActivity(intent);
        finish();
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(JobComparerTwoJobsDetailsActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }










}