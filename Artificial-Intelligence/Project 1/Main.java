package me.projects.bridge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
    	ArrayList<String> names = new ArrayList<>();
    	ArrayList<Integer> time = new ArrayList<Integer>();
        Scanner speed = new Scanner(System.in);
        Scanner name = new Scanner(System.in);
        System.out.print("The number of people to cross the bridge is: ");
        int number = speed.nextInt();
        for (int i = 1; i < number+1; i++) {
            System.out.print("Import name for person number "+i+": ");
            names.add(name.nextLine());
            System.out.print("Import person's time: ");     
            time.add(speed.nextInt());
        }
        State bridge = new State(time,names);
        Algorithm Astar = new Algorithm();
        State terminalState;
        long start = System.currentTimeMillis();
        terminalState = Astar.Algorithm(bridge);
        long end = System.currentTimeMillis();
        if (terminalState == null) {
            System.out.println("Could not find solution");
        } else {
            State temp = terminalState;
            ArrayList<State> path = new ArrayList<>();
            path.add(terminalState);
            while (temp.getFather() != null) {
                path.add(temp.getFather());
                temp = temp.getFather();
            }
            Collections.reverse(path);
            int j = 0;
            System.out.println();
            for (State item : path) {
                System.out.println("State " + j++);
                System.out.println();
                item.print();
                System.out.println();
            }
            System.out.println("The total time needed is: " + terminalState.getScore() + " with search time:  "+(double)(end - start) / 1000 +" seconds");
        }
    }
}