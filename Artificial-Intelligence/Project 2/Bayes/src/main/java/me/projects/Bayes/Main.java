package me.projects.Bayes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Main {
    static Bayes item = new Bayes();
    static Sort obj = new Sort();
    static ArrayList<String> attr = new ArrayList<>();
    static HashMap<String,Integer> examples = new HashMap<>();

    public static void main(String args[]) throws IOException {
       Scanner scanner = new Scanner(System.in);
       System.out.println("Give me the train files: ");
       int trainfiles = scanner.nextInt();
       System.out.println("Give me the test files: ");
       int testfiles = scanner.nextInt();
       item.loadAll(trainfiles);
       item.loadTest(testfiles);
       System.out.println("The accuracy is: " + item.testBayes());
        

    }

}
