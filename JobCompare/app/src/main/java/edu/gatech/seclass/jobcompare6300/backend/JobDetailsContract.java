package edu.gatech.seclass.jobcompare6300.backend;

import android.provider.BaseColumns;

public class JobDetailsContract {

        // To prevent someone from accidentally instantiating the contract class,
        // make the constructor private to prevent instantiating the class.
        private JobDetailsContract() {}

        /* Inner class that defines the table contents */
        public static class JobDetails implements BaseColumns {
            public static final String TABLE_NAME = "JobDetails";
            public static final String COLUMN_NAME_JOBID = "jobid";
            public static final String COLUMN_NAME_JOBTYPE = "jobtype";
            public static final String COLUMN_NAME_TITLE = "title";
            public static final String COLUMN_NAME_COMPANY = "company";
            public static final String COLUMN_NAME_LOCATION = "location";
            public static final String COLUMN_NAME_COSTOFLIVING = "costofliving";
            public static final String COLUMN_NAME_YEARLYSALARY = "yearlysalary";
            public static final String COLUMN_NAME_SIGNINGBONUS = "signingbonus";
            public static final String COLUMN_NAME_LEAVE = "leave";
            public static final String COLUMN_NAME_MATPATLEAVE = "matpatleave";
            public static final String COLUMN_NAME_LIFEINSURANCE = "lifeinsurance";
            public static final String COLUMN_NAME_RANK = "rank";
            public static final String COLUMN_NAME_SCORE = "score";
        }

}
