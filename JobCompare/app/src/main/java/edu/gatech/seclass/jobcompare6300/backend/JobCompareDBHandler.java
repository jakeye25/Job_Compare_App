package edu.gatech.seclass.jobcompare6300.backend;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.jobcompare6300.model.ComparisonSettingModel;
import edu.gatech.seclass.jobcompare6300.model.JobDetails;

public class JobCompareDBHandler {

    private Integer idGen=1;
    private SQLiteDatabase db = null;

    private JobCompareDbHelper dbHelper;



    //remove insertedRow logic - fdong
    //private boolean insertedRow = false;

    public  JobCompareDBHandler(SQLiteDatabase db) {
        this.db = db;
    }

    public  JobCompareDBHandler() {
        //
    }

    public SQLiteDatabase initialize(Context context) {
        dbHelper = new JobCompareDbHelper(context);


        // Gets the data repository in write mode
        if (db == null)
            db = dbHelper.getWritableDatabase();
        return db;
    }

    public void tearDown() {
        dbHelper.tearDown(db);
    }

    public void updateJobDetails(List<JobDetails> jobDetailList) {
        ContentValues values = null;

        for (JobDetails jobDetails : jobDetailList) {
            values = new ContentValues();
            values.put(JobDetailsContract.JobDetails.COLUMN_NAME_JOBID, jobDetails.getId());
            values.put(JobDetailsContract.JobDetails.COLUMN_NAME_JOBTYPE, jobDetails.getJobType());
            values.put(JobDetailsContract.JobDetails.COLUMN_NAME_TITLE, jobDetails.getTitle());
            values.put(JobDetailsContract.JobDetails.COLUMN_NAME_COMPANY, jobDetails.getCompany());
            values.put(JobDetailsContract.JobDetails.COLUMN_NAME_LOCATION, jobDetails.getLocation());
            values.put(JobDetailsContract.JobDetails.COLUMN_NAME_TITLE, jobDetails.getTitle());
            values.put(JobDetailsContract.JobDetails.COLUMN_NAME_COSTOFLIVING, jobDetails.getCostOfLiving());
            values.put(JobDetailsContract.JobDetails.COLUMN_NAME_YEARLYSALARY, jobDetails.getYearlySalary());
            values.put(JobDetailsContract.JobDetails.COLUMN_NAME_SIGNINGBONUS, jobDetails.getYearlyBonus());
            values.put(JobDetailsContract.JobDetails.COLUMN_NAME_LEAVE, jobDetails.getLeave());
            values.put(JobDetailsContract.JobDetails.COLUMN_NAME_MATPATLEAVE, jobDetails.getMPLeave());
            values.put(JobDetailsContract.JobDetails.COLUMN_NAME_LIFEINSURANCE, jobDetails.getInsurance());
            values.put(JobDetailsContract.JobDetails.COLUMN_NAME_RANK, jobDetails.getRank());
            values.put(JobDetailsContract.JobDetails.COLUMN_NAME_SCORE, jobDetails.getScore());
            String[] params = new String[]{ String.valueOf(jobDetails.getId()) };
            db.update(JobDetailsContract.JobDetails.TABLE_NAME, values, "jobid=?", params);
        }
    }
    public boolean addEntryInJobDetails(JobDetails jobDetails) {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(JobDetailsContract.JobDetails.COLUMN_NAME_JOBID, jobDetails.getId());
        values.put(JobDetailsContract.JobDetails.COLUMN_NAME_JOBTYPE, jobDetails.getJobType());
        values.put(JobDetailsContract.JobDetails.COLUMN_NAME_TITLE, jobDetails.getTitle());
        values.put(JobDetailsContract.JobDetails.COLUMN_NAME_COMPANY, jobDetails.getCompany());
        values.put(JobDetailsContract.JobDetails.COLUMN_NAME_LOCATION, jobDetails.getLocation());
        values.put(JobDetailsContract.JobDetails.COLUMN_NAME_TITLE, jobDetails.getTitle());
        values.put(JobDetailsContract.JobDetails.COLUMN_NAME_COSTOFLIVING, jobDetails.getCostOfLiving());
        values.put(JobDetailsContract.JobDetails.COLUMN_NAME_YEARLYSALARY, jobDetails.getYearlySalary());
        values.put(JobDetailsContract.JobDetails.COLUMN_NAME_SIGNINGBONUS, jobDetails.getYearlyBonus());
        values.put(JobDetailsContract.JobDetails.COLUMN_NAME_LEAVE, jobDetails.getLeave());
        values.put(JobDetailsContract.JobDetails.COLUMN_NAME_MATPATLEAVE, jobDetails.getMPLeave());
        values.put(JobDetailsContract.JobDetails.COLUMN_NAME_LIFEINSURANCE, jobDetails.getInsurance());
        values.put(JobDetailsContract.JobDetails.COLUMN_NAME_RANK, jobDetails.getRank());
        values.put(JobDetailsContract.JobDetails.COLUMN_NAME_SCORE, jobDetails.getScore());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(JobDetailsContract.JobDetails.TABLE_NAME, null, values);
        return (newRowId > 0);
    }

    public List<JobDetails> retrieveAllJobDetails() {
        List<JobDetails> jobDetailsList = new ArrayList<JobDetails>();
        Cursor c = db.rawQuery("SELECT jobtype, company, costofliving, jobid, leave, location, lifeinsurance, matpatleave, title, signingbonus, yearlysalary, rank, score FROM JobDetails ", null);

        if (c.moveToFirst()){
            do {
                JobDetails jobDetails = new JobDetails();
                jobDetails.setJobType(c.getInt(0));
                jobDetails.setCompany(c.getString(1));
                jobDetails.setCostOfLiving(c.getInt(2));
                jobDetails.setId(c.getInt(3));
                jobDetails.setLeave(c.getInt(4));
                jobDetails.setLocation(c.getString(5));
                jobDetails.setLifeInsurance(c.getInt(6));
                jobDetails.setMPLeave(c.getInt(7));
                jobDetails.setTitle(c.getString(8));
                jobDetails.setYearlyBonus(c.getDouble(9));
                jobDetails.setYearlySalary(c.getDouble(10));
                jobDetails.setRank(c.getInt(11));
                jobDetails.setScore(c.getDouble(12));
                jobDetailsList.add(jobDetails);
            } while(c.moveToNext());
       }
       return jobDetailsList;
    }

    public JobDetails retrieveCurrentJobDetails() {
        JobDetails jobDetails = new JobDetails();
        Cursor c = db.rawQuery("SELECT jobtype, company, costofliving, jobid, leave, location, lifeinsurance, matpatleave, title, signingbonus, yearlysalary, rank, score FROM JobDetails where jobtype=1 ", null);

        if (c.moveToFirst()){
                jobDetails.setJobType(c.getInt(0));
                jobDetails.setCompany(c.getString(1));
                jobDetails.setCostOfLiving(c.getInt(2));
                jobDetails.setId(c.getInt(3));
                jobDetails.setLeave(c.getInt(4));
                jobDetails.setLocation(c.getString(5));
                jobDetails.setLifeInsurance(c.getInt(6));
                jobDetails.setMPLeave(c.getInt(7));
                jobDetails.setTitle(c.getString(8));
                jobDetails.setYearlyBonus(c.getDouble(9));
                jobDetails.setYearlySalary(c.getDouble(10));
                jobDetails.setRank(c.getInt(11));
                jobDetails.setScore(c.getDouble(12));
        }
        return jobDetails;
    }

    public List<JobDetails> retrieveAllJobOffers() {
        List<JobDetails> jobDetailsList = new ArrayList<JobDetails>();
        Cursor c = db.rawQuery("SELECT jobtype, company, costofliving, jobid, leave, location, lifeinsurance, matpatleave, title, signingbonus, yearlysalary, rank, score FROM JobDetails where jobtype=2 ", null);

        if (c.moveToFirst()){
            do {
                JobDetails jobDetails = new JobDetails();
                jobDetails.setJobType(c.getInt(0));
                jobDetails.setCompany(c.getString(1));
                jobDetails.setCostOfLiving(c.getInt(2));
                jobDetails.setId(c.getInt(3));
                jobDetails.setLeave(c.getInt(4));
                jobDetails.setLocation(c.getString(5));
                jobDetails.setLifeInsurance(c.getInt(6));
                jobDetails.setMPLeave(c.getInt(7));
                jobDetails.setTitle(c.getString(8));
                jobDetails.setYearlyBonus(c.getDouble(9));
                jobDetails.setYearlySalary(c.getDouble(10));
                jobDetails.setRank(c.getInt(11));
                jobDetails.setScore(c.getDouble(12));
                jobDetailsList.add(jobDetails);
            } while(c.moveToNext());
        }
        return jobDetailsList;
    }

    public Integer retrieveMaxJobId() {
        Integer retId = 0;
        Cursor c = db.rawQuery("SELECT max(jobid) FROM JobDetails where jobtype=2 ", null);
        if (c.moveToFirst()) {
            retId = c.getInt(0);
        }
        return retId;
    }
    public JobDetails retrieveSingleJobOffer(Integer jobid) {
        JobDetails jobDetail = new JobDetails();

        String[] params = new String[]{ String.valueOf(jobid) };
        //remove job type so it can be generalized.- fdong9
        //Cursor c = db.rawQuery("SELECT jobtype, company, costofliving, jobid, leave, location, lifeinsurance, matpatleave, title, signingbonus, yearlysalary, rank, score FROM JobDetails where jobtype=2 and jobid = ?", params);
        Cursor c = db.rawQuery("SELECT jobtype, company, costofliving, jobid, leave, location, lifeinsurance, matpatleave, title, signingbonus, yearlysalary, rank, score FROM JobDetails where jobid = ?", params);
        if (c.moveToFirst()){
                //fix typepo jobDetail not JobDetails - fdong
                //JobDetails jobDetails = new JobDetails();
                jobDetail.setJobType(c.getInt(0));
                jobDetail.setCompany(c.getString(1));
                jobDetail.setCostOfLiving(c.getInt(2));
                jobDetail.setId(c.getInt(3));
                jobDetail.setLeave(c.getInt(4));
                jobDetail.setLocation(c.getString(5));
                jobDetail.setLifeInsurance(c.getInt(6));
                jobDetail.setMPLeave(c.getInt(7));
                jobDetail.setTitle(c.getString(8));
                jobDetail.setYearlyBonus(c.getDouble(9));
                jobDetail.setYearlySalary(c.getDouble(10));
                jobDetail.setRank(c.getInt(11));
                jobDetail.setScore(c.getDouble(12));
        }
        return jobDetail;
    }


    public void updateCurrentJobDetails (JobDetails cModel)  {

        ContentValues values = new ContentValues();
        values.put(JobDetailsContract.JobDetails.COLUMN_NAME_TITLE, cModel.getTitle());
        values.put(JobDetailsContract.JobDetails.COLUMN_NAME_COMPANY, cModel.getCompany());
        values.put(JobDetailsContract.JobDetails.COLUMN_NAME_LEAVE, cModel.getLeave());
        values.put(JobDetailsContract.JobDetails.COLUMN_NAME_JOBID, cModel.getId());
        values.put(JobDetailsContract.JobDetails.COLUMN_NAME_JOBTYPE, cModel.getJobType());
        values.put(JobDetailsContract.JobDetails.COLUMN_NAME_COSTOFLIVING, cModel.getCostOfLiving());
        values.put(JobDetailsContract.JobDetails.COLUMN_NAME_LIFEINSURANCE, cModel.getInsurance());
        values.put(JobDetailsContract.JobDetails.COLUMN_NAME_LOCATION, cModel.getLocation());
        values.put(JobDetailsContract.JobDetails.COLUMN_NAME_MATPATLEAVE, cModel.getMPLeave());
        values.put(JobDetailsContract.JobDetails.COLUMN_NAME_SIGNINGBONUS, cModel.getYearlyBonus());
        values.put(JobDetailsContract.JobDetails.COLUMN_NAME_YEARLYSALARY, cModel.getYearlySalary());
        values.put(JobDetailsContract.JobDetails.COLUMN_NAME_RANK, cModel.getRank());
        values.put(JobDetailsContract.JobDetails.COLUMN_NAME_SCORE, cModel.getScore());
        long newRowId = db.update(JobDetailsContract.JobDetails.TABLE_NAME, values, "jobtype=1", null);

    }

    public void updateComparisonSettings (ComparisonSettingModel cModel) throws Exception  {

        //if (insertedRow == true ) {
            ContentValues values = new ContentValues();
            values.put(JobComparisonSettingContract.comparisonSettings.COLUMN_NAME_LEAVEWEIGHT, cModel.getWtLeave());
            values.put(JobComparisonSettingContract.comparisonSettings.COLUMN_NAME_LIFEINSURANCEWEIGHT, cModel.getWtLifeInsurance());
            values.put(JobComparisonSettingContract.comparisonSettings.COLUMN_NAME_BONUSWEIGHT, cModel.getWtYearlyBonus());
            values.put(JobComparisonSettingContract.comparisonSettings.COLUMN_NAME_MATPATLEAVEWEIGHT, cModel.getWtMatLeave());
            values.put(JobComparisonSettingContract.comparisonSettings.COLUMN_NAME_SALARYWEIGHT, cModel.getWtYearlySalary());
            // Update existing row, returning the primary key value of the new row
            long newRowId = db.update(JobComparisonSettingContract.comparisonSettings.TABLE_NAME, values, null, null);
        //}
        //else {
            //throw ( new Exception("Please add comparisonsettings before trying to update"));
        //}
    }

    public void insertComparisonSettings(ComparisonSettingModel cModel) throws Exception {

        //if (insertedRow == false) {
            ContentValues values = new ContentValues();
            values.put(JobComparisonSettingContract.comparisonSettings.COLUMN_NAME_LEAVEWEIGHT, cModel.getWtLeave());
            values.put(JobComparisonSettingContract.comparisonSettings.COLUMN_NAME_LIFEINSURANCEWEIGHT, cModel.getWtLifeInsurance());
            values.put(JobComparisonSettingContract.comparisonSettings.COLUMN_NAME_BONUSWEIGHT, cModel.getWtYearlyBonus());
            values.put(JobComparisonSettingContract.comparisonSettings.COLUMN_NAME_MATPATLEAVEWEIGHT, cModel.getWtMatLeave());
            values.put(JobComparisonSettingContract.comparisonSettings.COLUMN_NAME_SALARYWEIGHT, cModel.getWtYearlySalary());

            // Insert the new row, returning the primary key value of the new row
            long newRowId = db.insert(JobComparisonSettingContract.comparisonSettings.TABLE_NAME, null, values);
            //insertedRow = true;
        //}
        //else {
            //throw (new Exception("ComparisonSetting row already exists, use updateComparisonSettings to make updates"));
        //}
    }
    // comment out delete,  no need for comparison setting
    /*
    public void deleteComparisonSettings(ComparisonSettingModel cModel) throws Exception {

        if (insertedRow == false) {
            throw (new Exception("ComparisonSetting row does not exist, nothing to delete"));
        }
        else {
            // delete row, returning the primary key value of the new row
            long newRowId = db.delete(JobComparisonSettingContract.comparisonSettings.TABLE_NAME, null, null);
            insertedRow = false;
        }
    }
    */

    public ComparisonSettingModel retrieveComparisonSettingFromDb() {
        ComparisonSettingModel compSettings = new ComparisonSettingModel();
        Cursor c = db.rawQuery("SELECT id, leaveweight, lifeinsuranceweight, bonusweight, matpatleaveweight, salaryweight FROM comparisonsettings ", null);
        if (c.moveToFirst()){
            compSettings.setId(c.getInt(0));
            compSettings.setWtLeave(c.getInt(1));
            compSettings.setWtLifeInsurance(c.getInt(2));
            compSettings.setWtYearlyBonus(c.getInt(3));
            compSettings.setWtMatLeave(c.getInt(4));
            compSettings.setWtYearlySalary( c.getInt(5));
        }
        //remove db close so db can be used later - fdong
        //db.close();
        return compSettings;
    }

}
