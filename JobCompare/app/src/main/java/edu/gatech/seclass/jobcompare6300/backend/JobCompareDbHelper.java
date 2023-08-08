package edu.gatech.seclass.jobcompare6300.backend;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class JobCompareDbHelper extends SQLiteOpenHelper{

        // database version to keep track of schema version
        public static final int DATABASE_VERSION = 2;
        public static final String DATABASE_NAME = "JobCompare.db";

        private static final String SQL_CREATE_JOBDETAIL_TABLE =
            "CREATE TABLE " + JobDetailsContract.JobDetails.TABLE_NAME + " (" +
                    JobDetailsContract.JobDetails.COLUMN_NAME_JOBID + " INTEGER PRIMARY KEY," +
                    JobDetailsContract.JobDetails.COLUMN_NAME_JOBTYPE + " TEXT," +
                    JobDetailsContract.JobDetails.COLUMN_NAME_TITLE + " TEXT," +
                    JobDetailsContract.JobDetails.COLUMN_NAME_COMPANY + " TEXT," +
                    JobDetailsContract.JobDetails.COLUMN_NAME_LOCATION + " TEXT," +
                    JobDetailsContract.JobDetails.COLUMN_NAME_COSTOFLIVING + " INTEGER," +
                    JobDetailsContract.JobDetails.COLUMN_NAME_YEARLYSALARY + " DOUBLE," +
                    JobDetailsContract.JobDetails.COLUMN_NAME_SIGNINGBONUS + " DOUBLE," +
                    JobDetailsContract.JobDetails.COLUMN_NAME_LEAVE + " INTEGER," +
                    JobDetailsContract.JobDetails.COLUMN_NAME_MATPATLEAVE + " INTEGER," +
                    JobDetailsContract.JobDetails.COLUMN_NAME_RANK + " INTEGER," +
                    JobDetailsContract.JobDetails.COLUMN_NAME_SCORE + " DOUBLE," +
                    JobDetailsContract.JobDetails.COLUMN_NAME_LIFEINSURANCE + " INTEGER);" ;


    private static final String SQL_DELETE_JOBDETAIL_TABLE =
            "DROP TABLE IF EXISTS " + JobDetailsContract.JobDetails.TABLE_NAME;

    private static final String SQL_CREATE_COMPARISONSETTINGS_TABLE =
            "CREATE TABLE " + JobComparisonSettingContract.comparisonSettings.TABLE_NAME + " (" +
                    //add id and set as primary key - fdong
                    JobComparisonSettingContract.comparisonSettings.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                    JobComparisonSettingContract.comparisonSettings.COLUMN_NAME_LEAVEWEIGHT + " INTEGER DEFAULT 1," +
                    JobComparisonSettingContract.comparisonSettings.COLUMN_NAME_LIFEINSURANCEWEIGHT + " INTEGER DEFAULT 1," +
                    JobComparisonSettingContract.comparisonSettings.COLUMN_NAME_BONUSWEIGHT + " INTEGER DEFAULT 1," +
                    JobComparisonSettingContract.comparisonSettings.COLUMN_NAME_MATPATLEAVEWEIGHT + " INTEGER DEFAULT 1," +
                    JobComparisonSettingContract.comparisonSettings.COLUMN_NAME_SALARYWEIGHT + " INTEGER DEFAULT 1);";



    private static final String SQL_DELETE_COMPARISONSETTINGS_TABLE =
            "DROP TABLE IF EXISTS " + JobComparisonSettingContract.comparisonSettings.TABLE_NAME;


    public JobCompareDbHelper(Context context) {
            super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory)null, DATABASE_VERSION);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SQL_DELETE_JOBDETAIL_TABLE);
            db.execSQL(SQL_DELETE_COMPARISONSETTINGS_TABLE);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_JOBDETAIL_TABLE);
        db.execSQL(SQL_CREATE_COMPARISONSETTINGS_TABLE);
    }

    public void tearDown(SQLiteDatabase db) {
        db.execSQL(SQL_DELETE_JOBDETAIL_TABLE);
        db.execSQL(SQL_DELETE_COMPARISONSETTINGS_TABLE);
    }
}
