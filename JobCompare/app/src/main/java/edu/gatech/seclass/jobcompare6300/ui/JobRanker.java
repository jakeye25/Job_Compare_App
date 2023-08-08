package edu.gatech.seclass.jobcompare6300.ui;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.gatech.seclass.jobcompare6300.model.ComparisonSettingModel;
import edu.gatech.seclass.jobcompare6300.model.JobDetails;

public class JobRanker {
    public static List<JobDetails> rankJobs(List<JobDetails> jobs, ComparisonSettingModel comparisonSetting) {
        for (JobDetails job : jobs) {
            double weightedAverage = (job.getAdjYearlySalary() * comparisonSetting.getWtYearlySalary()
                    + job.getAdjYearlyBonus() * comparisonSetting.getWtYearlyBonus()
                    + (job.getLeave() * job.getAdjYearlySalary()/260) * comparisonSetting.getWtLeave()
                    + (job.getMPLeave() * job.getAdjYearlySalary()/260) * comparisonSetting.getWtMatLeave()
                    + (job.getInsurance()/100 * job.getAdjYearlySalary()) * comparisonSetting.getWtLifeInsurance())
                    / (comparisonSetting.getWtYearlySalary() + comparisonSetting.getWtYearlyBonus()
                    + comparisonSetting.getWtLeave() + comparisonSetting.getWtMatLeave() + comparisonSetting.getWtLifeInsurance());
            job.setScore(weightedAverage);
        }

        // Sort the jobs based on weighted averages in descending order
        Collections.sort(jobs, Comparator.comparingDouble(JobDetails::getScore).reversed());

        // set the ranker field -fdong
        for(int i =0 ; i < jobs.size(); i++){
            JobDetails job = jobs.get(i);
            job.setRank(i+1);
        }
        // Return only the top ten ranked job offers -xwang
        if (jobs.size() > 10) {
            return jobs.subList(0, 10);
        } else {
            return jobs;
        }
    }
}
