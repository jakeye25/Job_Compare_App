package edu.gatech.seclass.jobcompare6300.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.ArrayList;

import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.backend.JobCompareDBHandler;
import edu.gatech.seclass.jobcompare6300.model.ComparisonSettingModel;
import edu.gatech.seclass.jobcompare6300.model.JobDetailViewAdapter;
import edu.gatech.seclass.jobcompare6300.model.JobDetails;
import android.widget.TextView;
import android.widget.Toast;

public class JobComparerActivity extends AppCompatActivity {

    private JobCompareDBHandler jobCompareDBHandler;
    private List<JobDetails> rankedJobs;

    private Button buttonCancel;
    private Button buttonCompare;

    private JobDetailViewAdapter adapter;
    private List<JobDetails> jobDetailList;
    private TextView compareReminderTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_comparer);

        jobCompareDBHandler = new JobCompareDBHandler();
        jobCompareDBHandler.initialize(this);
        jobDetailList = jobCompareDBHandler.retrieveAllJobDetails();

        ComparisonSettingModel compSetting = jobCompareDBHandler.retrieveComparisonSettingFromDb();
        rankedJobs = JobRanker.rankJobs(jobDetailList, compSetting);
        jobCompareDBHandler.updateJobDetails(rankedJobs);

        adapter = new JobDetailViewAdapter(this, jobDetailList);

        ListView listView = findViewById(R.id.compareTwoJobsListview);
        listView.setAdapter(adapter);

        compareReminderTextView = findViewById(R.id.compareReminderTextView);
        compareReminderTextView.setVisibility(View.INVISIBLE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                JobDetails selectedJob = jobDetailList.get(position);
                boolean isChecked = selectedJob.isChecked();
                selectedJob.setChecked(!isChecked);
                adapter.notifyDataSetChanged();

                int selectedJobCount = getSelectedJobCount();

                if (selectedJobCount == 2) {
                    compareReminderTextView.setVisibility(View.INVISIBLE);
                }
            }
        });

        buttonCancel = findViewById(R.id.buttonCompCancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToMainActivity();
            }
        });

        buttonCompare = findViewById(R.id.btnCompareJobs);
        buttonCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedJobCount = getSelectedJobCount();
                if (selectedJobCount == 2) {
                    navigateToNextPage();
                } else {
                    Toast.makeText(JobComparerActivity.this, "Please select 2 jobs to compare.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private int getSelectedJobCount() {
        int selectedJobCount = 0;
        for (JobDetails job : jobDetailList) {
            if (job.isChecked()) {
                selectedJobCount++;
            }
        }
        return selectedJobCount;
    }

    private void navigateToNextPage() {
        jobCompareDBHandler.updateJobDetails(rankedJobs);
        List<Integer> selectedJobIds = new ArrayList<>();
        for (JobDetails job : jobDetailList) {
            if (job.isChecked()) {
                selectedJobIds.add(job.getId());
            }
        }

        Intent intent = new Intent(JobComparerActivity.this, JobComparerTwoJobsDetailsActivity.class);
        intent.putExtra("firstSelectedJob", selectedJobIds.get(0));
        intent.putExtra("secondSelectedJob", selectedJobIds.get(1));
        startActivity(intent);
        // finish();
    }
    private void navigateToMainActivity() {
        Intent intent = new Intent(JobComparerActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}