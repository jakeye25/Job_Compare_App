package edu.gatech.seclass.jobcompare6300.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.model.JobDetails;

public class CompareTwoJobsActivity extends AppCompatActivity {
    private final List<HashMap<String, Object>> twoSelectedJobs = new ArrayList<>();
    public String flag;
    public List<JobDetails> jobSelectedtoCompare = new ArrayList<>();

    private static final int COST_OF_LIVING_INDEX = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_comparer);
        Intent intent = getIntent();
        flag = intent.getExtras().getString("Source");
//        if (flag.equals("from Job Rank")) {
//            jobSelectedtoCompare = JobComparerActivity.getJobSelected();
//        }

        Context context = CompareTwoJobsActivity.this;
        ListView listViewCompareTwo = (ListView) findViewById(R.id.compareTwoJobsListview);
        initData();
        CompareTwoJobsAdapter adapter = new CompareTwoJobsAdapter(twoSelectedJobs, context);
        listViewCompareTwo.setAdapter(adapter);

    }

    private void initData() {

        String[] jobDetailName = {"Title","Company", "Location", "Yearly Salary Adjusted", "Signing Bonus Adjusted", "Yearly Bonus Adjusted", "Retirement Benefits", "Leave Time"};
        String[] jobSelectedOneStr = adjustedJob(jobSelectedtoCompare.get(0));
        String[] jobSelectedTwoStr = adjustedJob(jobSelectedtoCompare.get(1));
        Log.v("Tag", "I am here let do it");
        for (int i = 0; i < jobDetailName.length; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("jobDetailName", jobDetailName[i]);
            map.put("jobSelectedOne",jobSelectedOneStr[i]);
            map.put("jobSelectedTwo",jobSelectedTwoStr[i]);
            twoSelectedJobs.add(map);
        }
    }

    private String[] adjustedJob(JobDetails job) {

        BigDecimal salary = BigDecimal.valueOf(job.getYearlySalary());
        BigDecimal signingBonus = BigDecimal.valueOf(job.getYearlyBonus());
        // the gotCostOfLivingIndex() based on the location information should be incorporated (short costOfLivingIndex = fixed value)
        BigDecimal AYS = CostOfLivingAdjuster.calculateAdjustedAmount(salary, BigDecimal.valueOf(COST_OF_LIVING_INDEX));
        BigDecimal ASB = CostOfLivingAdjuster.calculateAdjustedAmount(signingBonus, BigDecimal.valueOf(COST_OF_LIVING_INDEX));

        return new String[]{String.valueOf(job.getTitle()), String.valueOf(job.getCompany()), (job.getLocation()), AYS.toString(),ASB.toString(), String.valueOf(job.getLeave()),String.valueOf(job.getMPLeave()),String.valueOf(job.getInsurance())};

    }

    public void onNewComparisonClick(View view) {
        finish();
    }

    public void onReturnToMainClick(View view) {
        Intent returnToMain = new Intent(CompareTwoJobsActivity.this, MainActivity.class);
        startActivity(returnToMain);
    }

    public void onBackClick(View view) {
        finish();
    }
}