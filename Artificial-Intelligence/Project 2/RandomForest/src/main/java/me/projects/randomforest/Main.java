package me.projects.randomforest;

import java.io.IOException;

public class Main {

    static Forest object = new Forest();

    public static void main(String args[]) throws IOException {
        object.trainForest();
        object.testForest();
    }

}
