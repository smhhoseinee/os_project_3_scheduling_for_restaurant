package os_project_3_scheduling;

public class Food {

    private String name;
    private int burst, deadline, period;
    private int utilization;
    boolean isCompleted = false;
    private int priority = -1;
    private int id;

    private int remainedTime;
    private int currentState;
    private int watingTime;
    private int countPeriod;
    private int sw = 1;
    private int deadLineInCurrentPeriod;

    public Food(String name, int burst, int deadline, int period) {
        this.name = name;
        this.burst = burst;
        this.deadline = deadline;
        this.period = period;

        utilization = 1000 * this.burst / this.period;

        System.out.println("utilization = " + utilization);
        this.remainedTime = this.burst;
        this.currentState = 1;
        this.watingTime = 0;
        this.countPeriod = 0;
        this.deadLineInCurrentPeriod = this.deadline;
    }

    public void setState(int time) {
        if (this.remainedTime == 0) {
            setIsCompleted(true);

            if (time % this.period == 0) {
                this.deadLineInCurrentPeriod = time + this.deadline;
                sw = 1;
                this.currentState = 1;
                this.remainedTime = this.burst;

                setIsCompleted(false);
            } else if (sw == 1) {
                sw = 0;
                this.currentState = 0;
                this.countPeriod++;
                this.watingTime += ((time - 1) % this.period) + 1 - this.burst;

            }
        }
    }

    // getters & setters 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setRemainTime(int remainedTime) {
        if (remainedTime == 0) {
            this.setIsCompleted(true);
        }
        this.remainedTime = remainedTime;
    }

    public int getRemainTime() {
        return this.remainedTime;
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

}
