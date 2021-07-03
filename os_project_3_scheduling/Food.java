package os_project_3_scheduling;

public class Food {

    private String name;
    private int burst, deadline, period;
    private int utilization;
    boolean isCompleted = false;
    private int remainTime;
    private int currentState;
    private int watingTime;
    private int countPeriod;
    private int sw=1;
    private int deadLineInCurrentPeriod;

    public Food(String name, int burst, int deadline, int period) {
        this.name = name;
        this.burst = burst;
        this.deadline = deadline;
        this.period = period;

        utilization = 1000 * this.burst / this.period;

        System.out.println("utilization = " + utilization);
        this.remainTime = this.burst;
        this.currentState = 1;
        this.watingTime = 0;
        this.countPeriod = 0;
        this.deadLineInCurrentPeriod = this.deadline;
    }

    public void setState(int time) {
        if (this.remainTime == 0) {
            if (time % this.period == 0) {
                this.deadLineInCurrentPeriod = time + this.deadline;
                sw=1;
                this.currentState = 1;
                this.remainTime = this.burst;
            } else if (sw==1){
                sw=0;
                this.currentState = 0;
                this.countPeriod++;
                this.watingTime += ((time-1)%this.period) + 1 - this.burst;
            }
        }
    }
    
    public int getDeadLineInCurrentPeriod() {
        return this.deadLineInCurrentPeriod;
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

    public void setRemainTime(int remainTime) {
        this.remainTime = remainTime;
    }

    public int getRemainTime() {
        return this.remainTime;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    public int getCurrentState() {
        return this.currentState;
    }

    public int getwatingTime() {
        return this.watingTime;
    }

}
