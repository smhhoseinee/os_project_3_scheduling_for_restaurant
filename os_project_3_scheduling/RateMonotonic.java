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
public class RateMonotonic {

    Food food[];

    public RateMonotonic(Food[] food) {
        SetPriorityRateMonotonic(food);
        this.food = food;
    }

    public void SetPriorityRateMonotonic(Food[] food) {
        int minBurst = -1;
        int minBursti = -1;
        int currentPriorityToAssign = 0;
        for (int i = 0; i < food.length; i++) {
            if (food[i].getPriority() < 0 && food[i].getBurst() < minBurst) {
                minBurst = food[i].getBurst();
                minBursti = i;
            }
        }
        if (minBursti != -1) {
            food[minBursti].setPriority(currentPriorityToAssign);
            currentPriorityToAssign++;
        }
    }

    public int nextHighestPriorityIncompletedFood() {

        for (int i = 0; i < food.length; i++) {
            for (Food f : food) {
                if (f.getPriority() == i 
                        && f.getPriority() >= 0
                        && !f.isIsCompleted()) {
                   return f.getId();
                }
            }
        }
        
        return 0;
    }
}
