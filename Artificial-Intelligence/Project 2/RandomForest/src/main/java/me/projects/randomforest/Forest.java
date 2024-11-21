package me.projects.randomforest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author aris
 */
public class Forest {

    static int cpos;
    static int cneg;
    static ID3 item = new ID3();
    static Sort obj = new Sort();
    static ArrayList<String> attr = new ArrayList<>();
    static HashMap<String, Integer> examples = new HashMap<>();
    static HashMap<String, Integer> test = new HashMap<>();
    static ArrayList<ID3> forest = new ArrayList<>();
    static ArrayList<String> temp = new ArrayList<>();
    static int matrix[];

    void trainForest() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give me the train files: ");
        int trainfiles = scanner.nextInt();
        cpos = trainfiles;
        cneg = trainfiles;
        System.out.println("Give me the attributes: ");
        int attributes = scanner.nextInt();
        System.out.println("Give me the test files: ");
        int testfiles = scanner.nextInt();
        examples.putAll(item.loadAll(trainfiles));
        attr = item.getAttributes(attributes);
        System.out.println(attr.size());
        temp = item.getHashMapKey(examples);
        for (int i = 0; i < 5; i++) {
            Collections.shuffle(temp);
            HashMap<String, Integer> tempHash = new HashMap<>();
            for (int j = 0; j < trainfiles / 2; j++) {
                tempHash.put(temp.get(j), examples.get(temp.get(j)));
            }
            Collections.shuffle(attr);
            ArrayList<String> tempAttr = new ArrayList<>();
            for (int j = 0; j < (attr.size() - attr.size() / 4); j++) {
                tempAttr.add(attr.get(j));
            }
            System.out.println(tempAttr.size());
            System.out.println(tempHash.size());
            item.ID3_train(tempHash, tempAttr, 0);

            test.putAll(item.loadTest(testfiles));
            forest.add(item);
            item = new ID3();
        }
    }

    void testForest() throws IOException {
        int choice = 0;
        ArrayList<String> testing = new ArrayList<>();
        testing.addAll(item.getHashMapKey(test));
        double accuracy = 0.0;
        double neg[];
        double pos[];
        int counter = 0;
        int variable;
        int truepositive = 0;
        int truenegative = 0;
        int falsepositive = 0;
        int falsenegative = 0;
        double precisionpos = 0;
        double precisionneg = 0;
        double bpos = 0;
        double bneg = 0;
        double f1pos = 0;
        double f1neg = 0;
        for (int j = 0; j < test.size(); j++) {
            int counterPos = 0;
            int counterNeg = 0;
            for (int i = 0; i < forest.size(); i++) {
                    if (forest.get(i).ID3_test().get(j) == 0) {
                        counterPos++;
                    } else {
                        counterNeg++;
                    }
            }

            if (counterPos > 1) {//edw allazoyme to threshold
                if (test.get(testing.get(j)) == 0) {
                    truepositive++;
                    counter++;
                }
            } else {
                if (test.get(testing.get(j)) == 1) {
                    truenegative++;
                    counter++;
                }
            }
        }
        falsepositive = cpos - truepositive;
        falsenegative = cneg - truenegative;
        precisionpos = (double) truepositive / (double) (truepositive + falsepositive);
        precisionneg = (double) truenegative / (double) (truenegative + falsenegative);
        bpos = (double) truepositive / (double) (truepositive + falsenegative);
        bneg = (double) truenegative / (double) (truenegative + falsepositive);
        f1pos = 2 * bpos * precisionpos / (bpos + precisionpos);
        f1neg = 2 * bneg * precisionneg / (bneg + precisionneg);
        System.out.println("Your data have arrived!");
        System.out.println("The precisions: The positive: " + precisionpos + "The negative: " + precisionneg);
        System.out.println("The recalls: The positive: " + bpos + "The negative: " + bneg);
        System.out.println("The f1's: The positive: " + f1pos + "The negative: " + f1neg);
        accuracy = (double) counter / (double) test.size() * 100;
        System.out.println(accuracy);
    }
}
