package edu.gatech.seclass.jobcompare6300.model;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


import edu.gatech.seclass.jobcompare6300.R;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import edu.gatech.seclass.jobcompare6300.model.JobDetails;

public class JobDetailViewAdapter extends ArrayAdapter<JobDetails> {
    private LayoutInflater inflater;

    public JobDetailViewAdapter(Context context, List<JobDetails> objects) {
        super(context, 0, objects);
        inflater = LayoutInflater.from(context);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.job_item, parent, false);
        }

        JobDetails job = getItem(position);

        // Set the job offer data to the views in the list item layout
        TextView title = convertView.findViewById(R.id.title);
        title.setText(job.getTitle());

        TextView company = convertView.findViewById(R.id.company);
        company.setText(job.getCompany());

        TextView rank = convertView.findViewById(R.id.rank);
        rank.setText(String.valueOf(job.getRank()));

        TextView currentJob = convertView.findViewById(R.id.currentJob);
        if(job.getJobType() == 1) {
            currentJob.setText("Current Job");
        }

        CheckBox checkBox = convertView.findViewById(R.id.checkBox);
        checkBox.setChecked(job.isChecked());

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                job.setChecked(isChecked);
            }
        });

        return convertView;
    }
}
