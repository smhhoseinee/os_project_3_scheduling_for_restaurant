/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os_project_3_scheduling;

import java.io.Console;
import java.util.Scanner;

/**
 *
 * @author No1
 */
public class Os_project_3_scheduling {

    /**
     * @param args the command line arguments
     */
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

        System.out.println("Enter foods like this format : <Food name> <burst time> <deadline> <period> ");
        for (int i = 0; i < n; i++) {
            String name = input.next();
            int burst = input.nextInt();
            int deadline = input.nextInt();
            int period = input.nextInt();

            food[i] = new Food(name, burst, deadline, period);

        }
    }

}
