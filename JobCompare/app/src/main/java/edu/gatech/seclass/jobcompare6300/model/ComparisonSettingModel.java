package edu.gatech.seclass.jobcompare6300.model;

public class ComparisonSettingModel {
    private int id;
    private int wtYearlySalary;
    private int wtYearlyBonus;
    private int wtLeave;
    private int wtMatLeave;
    private int wtLifeInsurance;

    public ComparisonSettingModel(){

    }

    public ComparisonSettingModel(int id, int wtYearlySalary, int wtYearlyBonus, int wtLeave, int wtMatLeave, int wtLifeInsurance){
        this.id = id;
        this.wtYearlySalary = wtYearlySalary;
        this.wtYearlyBonus = wtYearlyBonus;
        this.wtLeave = wtLeave;
        this.wtMatLeave = wtMatLeave;
        this.wtLifeInsurance = wtLifeInsurance;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getWtYearlySalary(){
        return wtYearlySalary;
    }

    public void setWtYearlySalary(int wtYearlySalary){
        this.wtYearlySalary = wtYearlySalary;
    }

    public int getWtYearlyBonus(){
        return this.wtYearlyBonus;
    }

    public void setWtYearlyBonus(int wtYearlyBonus){
        this.wtYearlyBonus = wtYearlyBonus;
    }

    public int getWtLeave(){
        return this.wtLeave;
    }

    public void setWtLeave(int wtLeave){
        this.wtLeave = wtLeave;
    }

    public int getWtMatLeave(){
        return this.wtMatLeave;
    }

    public void setWtMatLeave(int wtMatLeave){
        this.wtMatLeave = wtMatLeave;
    }

    public int getWtLifeInsurance(){
        return this.wtLifeInsurance;
    }

    public void setWtLifeInsurance(int wtLifeInsurance){
        this.wtLifeInsurance = wtLifeInsurance;
    }

}
