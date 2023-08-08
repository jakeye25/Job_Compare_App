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


public class JobofferActivity extends AppCompatActivity {

    private EditText joTitle;
    private EditText joCompany;
    private EditText joLocation;
    private EditText joCostofLiving;
    private EditText joSalary;
    private EditText joBonus;
    private EditText joLeave;
    private EditText joMPLeave;
    private EditText joInsurance;

    private Button joButtonsave;
    private Button joButtoncancel;
    private Button joButtonanother;

    private EditText joTitletext;
    private EditText joCompanytext;
    private EditText joLocationtext;
    private EditText joCostofLivingtext;
    private EditText joSalarytext;
    private EditText joBonustext;
    private EditText joLeavetext;
    private EditText joMPLeavetext;
    private EditText joInsurancetext;

    private JobDetails jobOffer;
    private JobCompareDBHandler jobCompareDBHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joboffer);

        joTitle = findViewById(R.id.jo_title_input);
        joCompany = findViewById(R.id.jo_company_input);
        joLocation = findViewById(R.id.jo_location_input);
        joCostofLiving = findViewById(R.id.jo_costofliving_input);
        joSalary = findViewById(R.id.jo_salary_input);
        joBonus = findViewById(R.id.jo_bonus_input);
        joLeave = findViewById(R.id.jo_leave_input);
        joMPLeave = findViewById(R.id.jo_mpleave_input);
        joInsurance = findViewById(R.id.jo_insurance_input);

        joButtonsave = findViewById(R.id.buttonJOSave);
        joButtoncancel = findViewById(R.id.buttonJOCancel);
        joButtonanother= findViewById(R.id.buttonJOAnother);

        joTitletext = findViewById(R.id.jo_title);
        joCompanytext = findViewById(R.id.jo_company);
        joLocationtext = findViewById(R.id.jo_location);
        joCostofLivingtext = findViewById(R.id.jo_costofliving);
        joSalarytext = findViewById(R.id.jo_salary);
        joBonustext = findViewById(R.id.jo_bonus);
        joLeavetext = findViewById(R.id.jo_leave);
        joMPLeavetext = findViewById(R.id.jo_mpleave);
        joInsurancetext = findViewById(R.id.jo_insurance);

        joTitletext.setFocusable(false);
        joCompanytext.setFocusable(false);
        joLocationtext.setFocusable(false);
        joCostofLivingtext.setFocusable(false);
        joSalarytext.setFocusable(false);
        joBonustext.setFocusable(false);
        joLeavetext.setFocusable(false);
        joMPLeavetext.setFocusable(false);
        joInsurancetext.setFocusable(false);

        jobCompareDBHandler = new JobCompareDBHandler();
        jobCompareDBHandler.initialize(this);

        // Retrieve the current job details from the database
        jobOffer = new JobDetails();

        // Set the retrieved values in the UI fields
//        joTitle.setText(jobOffer.getTitle());
//        joCompany.setText(jobOffer.getCompany());
//        joLocation.setText(jobOffer.getLocation());
          joCostofLiving.setText(String.valueOf(0));
          joSalary.setText(String.valueOf(0));
          joBonus.setText(String.valueOf(0));
          joLeave.setText(String.valueOf(0));
          joMPLeave.setText(String.valueOf(0));
          joInsurance.setText(String.valueOf(0));

        joButtonsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Convert the relevant fields to appropriate types (int, double)
                // before saving them to the database
                String joTitleStr = joTitle.getText().toString();
                String joCompanyStr = joCompany.getText().toString();
                String joLocationStr = joLocation.getText().toString();
                int joCostofLivingInt = Integer.parseInt(joCostofLiving.getText().toString());
                Double joSalaryDouble = Double.parseDouble(joSalary.getText().toString());
                Double joBonusDouble = Double.parseDouble(joBonus.getText().toString());
                int joLeaveInt = Integer.parseInt(joLeave.getText().toString());
                int joMPLeaveInt = Integer.parseInt(joMPLeave.getText().toString());
                int joInsuranceInt = Integer.parseInt(joInsurance.getText().toString());

                //Constrain of input
                if(joTitleStr.isEmpty()) {joTitle.setError("Invalid Title Entry"); return;};
                if(joCompanyStr.isEmpty()) {joCompany.setError("Invalid Company Entry"); return;};
                if(joLocationStr.isEmpty()) {joLocation.setError("Invalid Location Entry"); return;};
                if(joCostofLivingInt<0) {joCostofLiving.setError("Invalid Cost of Living Entry"); return;};
                if(joSalaryDouble<0) {joSalary.setError("Invalid Salary Entry"); return;};
                if(joBonusDouble<0) {joBonus.setError("Invalid Bonus Entry"); return;};
                if(joLeaveInt<0 || joLeaveInt>30) {joLeave.setError("Invalid Leave Entry"); return;};
                if(joMPLeaveInt<0 || joMPLeaveInt>20) {joMPLeave.setError("Invalid Maternity Leave Entry"); return;};
                if(joInsuranceInt<0 || joInsuranceInt>10) {joInsurance.setError("Invalid Insurance Entry"); return;};

                // Insert the new job offer details in the database
                jobOffer.setJobType(2);//1 is for current job and 2 is for  job offers
                jobOffer.setTitle(joTitleStr);
                jobOffer.setCompany(joCompanyStr);
                jobOffer.setLocation(joLocationStr);
                jobOffer.setCostOfLiving(joCostofLivingInt);
                jobOffer.setYearlySalary(joSalaryDouble);
                jobOffer.setYearlyBonus(joBonusDouble);
                jobOffer.setLeave(joLeaveInt);
                jobOffer.setMPLeave(joMPLeaveInt);
                jobOffer.setLifeInsurance(joInsuranceInt);
                Integer jobId = jobCompareDBHandler.retrieveMaxJobId();
                if(jobId == 0) jobId=1;
                jobOffer.setId(++jobId); //Need to increment based on max id in DB

                // Save the new offer  job details to the database
                jobCompareDBHandler.addEntryInJobDetails(jobOffer);

                finish();
                Intent intent = new Intent(JobofferActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        joButtoncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JobofferActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        joButtonanother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String joTitleStr = joTitle.getText().toString();
                String joCompanyStr = joCompany.getText().toString();
                String joLocationStr = joLocation.getText().toString();
                int joCostofLivingInt = Integer.parseInt(joCostofLiving.getText().toString());
                Double joSalaryDouble = Double.parseDouble(joSalary.getText().toString());
                Double joBonusDouble = Double.parseDouble(joBonus.getText().toString());
                int joLeaveInt = Integer.parseInt(joLeave.getText().toString());
                int joMPLeaveInt = Integer.parseInt(joMPLeave.getText().toString());
                int joInsuranceInt = Integer.parseInt(joInsurance.getText().toString());

                //Constrain of input
                if(joTitleStr.isEmpty()) {joTitle.setError("Invalid Title Entry"); return;};
                if(joCompanyStr.isEmpty()) {joCompany.setError("Invalid Company Entry"); return;};
                if(joLocationStr.isEmpty()) {joLocation.setError("Invalid Location Entry"); return;};
                if(joCostofLivingInt<0) {joCostofLiving.setError("Invalid Cost of Living Entry"); return;};
                if(joSalaryDouble<0) {joSalary.setError("Invalid Salary Entry"); return;};
                if(joBonusDouble<0) {joBonus.setError("Invalid Bonus Entry"); return;};
                if(joLeaveInt<0 || joLeaveInt>30) {joLeave.setError("Invalid Leave Entry"); return;};
                if(joMPLeaveInt<0 || joMPLeaveInt>20) {joMPLeave.setError("Invalid Maternity Leave Entry"); return;};
                if(joInsuranceInt<0 || joInsuranceInt>10) {joInsurance.setError("Invalid Insurance Entry"); return;};

                // Update the current job details in the database
                jobOffer.setJobType(2);//1 is for current job and 2 is for  job offers
                jobOffer.setTitle(joTitleStr);
                jobOffer.setCompany(joCompanyStr);
                jobOffer.setLocation(joLocationStr);
                jobOffer.setCostOfLiving(joCostofLivingInt);
                jobOffer.setYearlySalary(joSalaryDouble);
                jobOffer.setYearlyBonus(joBonusDouble);
                jobOffer.setLeave(joLeaveInt);
                jobOffer.setMPLeave(joMPLeaveInt);
                jobOffer.setLifeInsurance(joInsuranceInt);

                Integer jobId = jobCompareDBHandler.retrieveMaxJobId();
                if(jobId == 0) jobId=1;
                jobOffer.setId(++jobId); //Need to increment based on max id in DB

                // Save the new offer  job details to the database
                jobCompareDBHandler.addEntryInJobDetails(jobOffer);

                finish();

                joTitle.setText("");
                joCompany.setText("");
                joLocation.setText("");
                joCostofLiving.setText("");
                joSalary.setText("");
                joBonus.setText("");
                joLeave.setText("");
                joMPLeave.setText("");
                joInsurance.setText("");

                Intent intent = new Intent(JobofferActivity.this, JobofferActivity.class);
                startActivity(intent);


            }
        });
    }
}
