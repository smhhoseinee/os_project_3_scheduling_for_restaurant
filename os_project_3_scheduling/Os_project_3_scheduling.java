/*
3
Food1 1 5 6
Food2 2 4 5
Food3 4 8 10
 */
package os_project_3_scheduling;

import java.util.Scanner;

public class Os_project_3_scheduling {

    public static int lcm(int number1, int number2) {
        if (number1 == 0 || number2 == 0) {
            return 0;
        }
        int absNumber1 = Math.abs(number1);
        int absNumber2 = Math.abs(number2);
        int absHigherNumber = Math.max(absNumber1, absNumber2);
        int absLowerNumber = Math.min(absNumber1, absNumber2);
        int lcm = absHigherNumber;
        while (lcm % absLowerNumber != 0) {
            lcm += absHigherNumber;
        }
        return lcm;
    }

    public static int EDFscheduling(int foodNumber, Food food[]) {
        int EDFood = -1;
        int ED = 10000;
        for (int i = 0; i < foodNumber; i++) {
            if (food[i].getDeadLineInCurrentPeriod() < ED && food[i].getCurrentState() == 1) {
                EDFood = i;
                ED = food[i].getDeadLineInCurrentPeriod();
            }
        }
        return EDFood;
    }

    public static void EDF(int totalRunTime, int n, Food food[]) {
        int currentTime = 0, idleTime = 0;
        while (currentTime < totalRunTime) {
            System.out.print(currentTime + " ");
            //set state of foods
            for (int i = 0; i < n; i++) {
                food[i].setState(currentTime);
            }
            int inputFood = EDFscheduling(n, food);//compute earliest deadline between ready food
            if (inputFood != -1) {// this means there is a food which is ready for making
                System.out.println(food[inputFood].getName());
                food[inputFood].setRemainTime(food[inputFood].getRemainTime() - 1);//reduce remain time in this period for performed food
            } else {// this means there is no food which is ready for making in this clock
                System.out.println("idle");
                idleTime++;
            }
            currentTime++;
        }
        System.out.println("Idle time = " + idleTime);
        for (int i = 0; i < n; i++) {
            System.out.println(food[i].getName() + " waiting time = " + food[i].getwatingTime());
        }
    }

    public static void RM(int totalRunTime, int n, Food food[]) {
        //implement Rate Monotonic algorithm here
        int currentTime = 0, idleTime = 0;
        while (currentTime < totalRunTime) {
            System.out.print(currentTime + " ");
            //set state of foods
            for (int i = 0; i < n; i++) {
                food[i].setState(currentTime);
            }
            RateMonotonic rm = new RateMonotonic(food);

            int nextFood = rm.nextHighestPriorityIncompletedFood();

            if (nextFood != -1) {// this means there is a food which is ready for making
                System.out.println(food[nextFood].getName());
                food[nextFood].setRemainTime(food[nextFood].getRemainTime() - 1);//reduce remain time in this period for performed food
            } else {// this means there is no food which is ready for making in this clock
                System.out.println("idle");
                idleTime++;
            }
            currentTime++;

        }
        System.out.println("Idle time = " + idleTime);
        for (int i = 0; i < n; i++) {
            System.out.println(food[i].getName() + " waiting time = " + food[i].getwatingTime());
        }

    }

    public static int LLFscheduling(int foodNumber, Food food[], int currentTime) {
        int EDFood = -1;
        int ED = 10000;
        for (int i = foodNumber - 1; i >= 0; i--) {
            int lasity = food[i].getDeadLineInCurrentPeriod() - food[i].getRemainTime() - currentTime;
//            System.out.print(" " + lasity + " ");
            if (lasity < ED && food[i].getCurrentState() == 1) {
                EDFood = i;
                ED = lasity;
            }
        }
        return EDFood;
    }

    public static void LLF(int totalRunTime, int n, Food food[]) {
        int currentTime = 0, idleTime = 0;
        while (currentTime < totalRunTime) {
            System.out.print(currentTime + " ");
            //set state of foods
            for (int i = 0; i < n; i++) {
                food[i].setState(currentTime);
            }
            int inputFood = LLFscheduling(n, food, currentTime);//compute earliest deadline between ready food
            if (inputFood != -1) {// this means there is a food which is ready for making
                System.out.println(food[inputFood].getName());
                food[inputFood].setRemainTime(food[inputFood].getRemainTime() - 1);//reduce remain time in this period for performed food
            } else {// this means there is no food which is ready for making in this clock
                System.out.println("idle");
                idleTime++;
            }
            currentTime++;
        }
        System.out.println("Idle time = " + idleTime);
        for (int i = 0; i < n; i++) {
            System.out.println(food[i].getName() + " waiting time = " + food[i].getwatingTime());
        }
    }

    public static boolean isSchedulingPossible(Food food[]) {
        int sum = 0;
        for (Food d : food) {
            sum += d.getUtilization();
        }
        if (sum > 1000) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("enter number of foods (n)");
        int n = input.nextInt();

        // 0 : burst time
        // 1 : deadline
        // 2 : period
        Food food[] = new Food[n];
        int totalRunTime = 1; // it show lowest common multiple between process's interval

        System.out.println("Enter foods like this format : <Food name> <burst time> <deadline> <period> ");
        for (int i = 0; i < n; i++) {
            String name = input.next();
            int burst = input.nextInt();
            int deadline = input.nextInt();
            int period = input.nextInt();
            totalRunTime = lcm(totalRunTime, period);
            food[i] = new Food(name, burst, deadline, period);
            food[i].setId(i);
        }

        if (!isSchedulingPossible(food)) {
            System.out.println("Can't handle orders");
            return;
        }

        System.out.println("choose an algorithm :\n1-Earliest Deadline First , 2-Rate Monotonic 3-Least Laxity First");
        int num = new Scanner(System.in).nextInt();
        if (num == 1) {
            EDF(totalRunTime, n, food);
        }
        if (num == 2) {
            RM(totalRunTime, n, food);
        }
        if (num == 3) {
            LLF(totalRunTime, n, food);
        }
    }

}
