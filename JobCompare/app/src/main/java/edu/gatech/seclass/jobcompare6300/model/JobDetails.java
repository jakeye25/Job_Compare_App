package edu.gatech.seclass.jobcompare6300.model;

public class JobDetails {
    private int id;
    private String title;
    private String company;
    private String location;
    private int costOfLiving;
    private Double yearlySalary;
    private Double yearlyBonus;
    private int leave;
    private int mpLeave;
    private int Insurance;

    private int jobType;

    //fdong - get the front end UI check
    private boolean isChecked;

    private int rank;
    private Double score;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public int getJobType() {
        return jobType;
    }

    public void setJobType(int jobType) {
        this.jobType = jobType;
    }



    public JobDetails() {
        // Default constructor
    }

    public JobDetails(int id, String title, String company, String location, int costOfLiving,
                      Double yearlySalary, Double yearlyBonus, int leave, int mpLeave,
                      int Insurance) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.location = location;
        this.costOfLiving = costOfLiving;
        this.yearlySalary = yearlySalary;
        this.yearlyBonus = yearlyBonus;
        this.leave = leave;
        this.mpLeave = mpLeave;
        this.Insurance = Insurance;
    }

    // Getters and setters for each field

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCostOfLiving() {
        return costOfLiving;
    }

    public void setCostOfLiving(int costOfLiving) {
        this.costOfLiving = costOfLiving;
    }

    public Double getYearlySalary() {
        return yearlySalary;
    }

    public Double getAdjYearlySalary() {
        return Math.floor(yearlySalary*100/this.costOfLiving);
    }

    public void setYearlySalary(Double yearlySalary) {
        this.yearlySalary = yearlySalary;
    }

    public Double getYearlyBonus() {
        return yearlyBonus;
    }

    public Double getAdjYearlyBonus() {
        return Math.floor(yearlyBonus*100/this.costOfLiving);
    }

    public void setYearlyBonus(Double yearlyBonus) {
        this.yearlyBonus = yearlyBonus;
    }

    public int getLeave() {
        return leave;
    }

    public void setLeave(int leave) {
        this.leave = leave;
    }

    public int getMPLeave() {
        return mpLeave;
    }

    public void setMPLeave(int mpLeave) {
        this.mpLeave = mpLeave;
    }

    public int getInsurance() {
        return Insurance;
    }

    public void setLifeInsurance(int lifeInsurance) {
        this.Insurance = lifeInsurance;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked){
        this.isChecked = isChecked;
    }
}
