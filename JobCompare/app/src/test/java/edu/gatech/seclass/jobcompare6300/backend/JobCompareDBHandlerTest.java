package edu.gatech.seclass.jobcompare6300.backend;

import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import edu.gatech.seclass.jobcompare6300.model.JobDetails;

@RunWith(RobolectricTestRunner.class)
public class JobCompareDBHandlerTest {
    @Mock
    Context context;
    @Mock
    private JobCompareDBHandler jbHandler;

    @Mock
    private SQLiteDatabase db;

    @Before
    public void setup() {
        jbHandler = new JobCompareDBHandler(db);

    }

    @Test
    public void addEntryInJobDetailsTest_HappyPath1() {
        JobDetails jbDetails = new JobDetails();

        //creating current job details, so setting jobtype to 1
        Integer jobid = 1;
        jbDetails.setJobType(jobid);
        jbDetails.setCompany("ABC");
        jbDetails.setTitle("Mgr");
        jbDetails.setLocation("Houston,TX");
        jbDetails.setYearlySalary(150000.00);
        jbDetails.setYearlyBonus(20000.00);
        jbDetails.setLeave(20);
        jbDetails.setMPLeave(30);
        jbDetails.setLifeInsurance(600);
        jbDetails.setId(1);
        jbDetails.setCostOfLiving(150);
        jbHandler.addEntryInJobDetails(jbDetails);

        assertTrue(jbHandler.retrieveSingleJobOffer(jobid).equals(jbDetails));
    }

    @After
    public void tearDown() {
        jbHandler.tearDown();
    }

}
