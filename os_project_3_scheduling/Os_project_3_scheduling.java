/*
3
Food1 2 2 8
Food2 1 3 4
Food3 3 6 8
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

    public static int EDFschaduling(int foodNumber, Food food[]) {
        int EDFood = -1;
        int ED = 10000;
        for (int i = 0; i < foodNumber; i++) {
            if (food[i].getDeadline() < ED && food[i].getCurrentState() == 1) {
                EDFood = i;
                ED = food[i].getDeadline();
            }
        }
        return EDFood;
    }

    public static void EDF(int totalRunTime, int n, Food food[]) {
        int spentTime = 0, idleTime = 0;
        while (spentTime < totalRunTime) {
            System.out.print(spentTime + " ");
            //set state of foods
            for (int i = 0; i < n; i++) {
                food[i].setState(spentTime);
            }
            int inputFood = EDFschaduling(n, food);//compute earliest deadline between ready food
            if (inputFood != -1) {// this means there is a food which is ready for making
                System.out.println(food[inputFood].getName());
                food[inputFood].setRemainTime(food[inputFood].getRemainTime() - 1);//reduce remain time in this period for performed food
            } else {// this means there is no food which is ready for making in this clock
                System.out.println("idle");
                idleTime++;
            }
            spentTime++;
        }
        System.out.println("Idle time = " + idleTime);
        for (int i = 0; i < n; i++) {
            System.out.println(food[i].getName() + " waiting time = " + food[i].getwatingTime());
        }
    }

    public static void RM(int totalRunTime, int n, Food food[]) {
        //implement Rate Monotonic algorithm here
        
    }

    public static void LLF(int totalRunTime, int n, Food food[]) {
        //implement Least Laxity First algorithm here
        
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
        int[][] data = new int[n][3];
        String[] names = new String[n];

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
