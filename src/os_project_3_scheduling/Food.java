/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os_project_3_scheduling;

/**
 *
 * @author No1
 */
public class Food {

    private String name;
    private int burst, deadline, period;
    private int utilization;
    boolean isCompleted = false;

    public Food(String name, int burst, int deadline, int period) {
        this.burst = burst;
        this.deadline = deadline;
        this.period = period;

        utilization = 1000 * this.burst / this.period;

        System.out.println("utilization = " + utilization);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBurst() {
        return burst;
    }

    public void setBurst(int burst) {
        this.burst = burst;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public boolean isIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public int getUtilization() {
        return utilization;
    }

    public void setUtilization(int utilization) {
        this.utilization = utilization;
    }
    
    

}
