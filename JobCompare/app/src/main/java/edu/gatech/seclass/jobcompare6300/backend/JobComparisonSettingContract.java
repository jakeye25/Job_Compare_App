package edu.gatech.seclass.jobcompare6300.backend;

import android.provider.BaseColumns;

public class JobComparisonSettingContract {
    private JobComparisonSettingContract() {}

    /* Inner class that defines the table contents */
    public static class comparisonSettings implements BaseColumns {
        //fdong - add id to table
        public static final String COLUMN_NAME_ID = "id";
        public static final String TABLE_NAME = "comparisonsettings";
        public static final String COLUMN_NAME_SALARYWEIGHT = "salaryweight";
        public static final String COLUMN_NAME_BONUSWEIGHT = "bonusweight";
        public static final String COLUMN_NAME_LEAVEWEIGHT = "leaveweight";
        public static final String COLUMN_NAME_MATPATLEAVEWEIGHT = "matpatleaveweight";
        public static final String COLUMN_NAME_LIFEINSURANCEWEIGHT = "lifeinsuranceweight";

    }
}
