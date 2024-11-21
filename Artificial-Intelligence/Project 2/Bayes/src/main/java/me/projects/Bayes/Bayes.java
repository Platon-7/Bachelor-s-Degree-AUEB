package me.projects.Bayes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class Bayes {

    HashMap<String, Integer> masterneg = new HashMap<>();
    HashMap<String, Integer> masterpos = new HashMap<>();
    HashMap<String, Integer> example = new HashMap<>();
    Set<Map.Entry<String, Integer>> gensort;
    int cpos, cneg = 0;
    HashMap<String, Integer> master = new HashMap<>();
    HashMap<String, Integer> test = new HashMap<>();

    public HashMap<String, Integer> words(String a) {
        HashMap<String, Integer> map = new HashMap<>();
        String[] splitted = a.toLowerCase().split("[^a-zA-Z+']+");
        String[] splitted2 = a.split("[^!._,'@?-]");
        for (int j = 0; j < splitted2.length; j++) {
            if (!splitted2[j].equals("") && !map.containsKey(splitted2[j])) {
                map.put(splitted2[j], 1);
            }
        }
        for (int i = 0; i < splitted.length; i++) {//ypologizei grammata mona toys
            if (!map.containsKey(splitted[i])) {
                map.put(splitted[i], 1);
            }
        }

        return map;
    }
    public int possibilityBayes(String txt) {
        master.putAll(masterneg);
        masterpos.forEach((key, value) -> master.merge(key, value, (oldValue, newValue) -> newValue = oldValue + newValue));
        ArrayList<String> temp = new ArrayList<>();
        temp.addAll(getHashMapKey(words(txt)));
        double ppos = calculatePossibility(example);
        double pneg = 1 - ppos;
        boolean flagpos;
        boolean flagneg;
        for (int y = 0; y < temp.size(); y++) {
            flagpos=true;
            flagneg=true;
            String h = temp.get(y);
            if (masterpos.get(temp.get(y)) == null) {
                flagpos=false;
            }else if(masterneg.get(temp.get(y))==null){
                flagneg=false;
            }else{
                
                ppos = ppos * masterpos.get(temp.get(y)) / master.get(temp.get(y)) * (cpos - masterpos.get(temp.get(y))) / (example.size() - master.get(temp.get(y)));
                pneg = pneg * masterneg.get(temp.get(y)) / master.get(temp.get(y)) * (cneg - masterneg.get(temp.get(y))) / (example.size() - master.get(temp.get(y)));


            }
            if(!flagpos){
                if(!(masterneg.get(temp.get(y))==null)){
                    ppos = ppos * 1/master.get(temp.get(y))*(cpos-1)/(example.size()-master.get(temp.get(y))+2); 
                    pneg = pneg * masterneg.get(temp.get(y)) / master.get(temp.get(y)) * (cneg - masterneg.get(temp.get(y))) / (example.size() - master.get(temp.get(y)));
                }

            }
            if(!flagneg){
                if(!(masterpos.get(temp.get(y))==null)){
                    pneg = pneg * 1/master.get(temp.get(y))*(cneg-1)/(example.size()-master.get(temp.get(y))+2); 
                    ppos = ppos * masterpos.get(temp.get(y)) / master.get(temp.get(y)) * (cpos - masterpos.get(temp.get(y))+1) / (example.size() - master.get(temp.get(y)));
                }

            }
        }
        double x = ppos/pneg;
        if (ppos >= pneg) {//edw allazoyme to threshold
            return 0;
        } else {
            return 1;
        }

    }

    public String testBayes() {
        int truepositive=0;
        int truenegative=0;
        int falsepositive=0;
        int falsenegative=0;
        double precisionpos = 0;
        double precisionneg = 0;
        double bpos = 0;
        double bneg = 0;
        double accuracy;
        double counter = 0;
        double f1pos = 0;
        double f1neg = 0;
        ArrayList<String> test2 = new ArrayList<>();
        test2.addAll(getHashMapKey(test));
        int variable = 0;
        for (int w = 0; w < test.size(); w++) {
            variable = possibilityBayes(test2.get(w));
            if (variable == test.get(test2.get(w))) {
                if(variable==0){
                    truepositive++;
                }else{
                    truenegative++;
                }
                counter++;
            }
        }
        falsepositive = cpos-truepositive;
        falsenegative = cneg - truenegative;
        precisionpos = (double)truepositive/(double)(truepositive + falsepositive);
        precisionneg =(double)truenegative/(double)(truenegative + falsenegative);
        bpos = (double)truepositive/(double)(truepositive + falsenegative);
        bneg = (double)truenegative/(double)(truenegative + falsepositive);
        f1pos = 2*bpos*precisionpos/(bpos + precisionpos);
        f1neg = 2*bneg*precisionneg/(bneg + precisionneg);
        
        System.out.println("Your data have arrived!");
        System.out.println("The precisions: The positive: "+ precisionpos +"The negative: " + precisionneg);
        System.out.println("The recalls: The positive: "+ bpos +"The negative: " + bneg);
        System.out.println("The f1's: The positive: "+ f1pos +"The negative: " + f1neg);
        
        accuracy = (counter / (double) test.size()) * 100;
        return accuracy + "%";
    }

    public int findMajority(HashMap<String, Integer> majority) {
        ArrayList<String> maj = new ArrayList<>();
        int poscount = 0;
        int negcount = 0;
        maj.addAll(getHashMapKey(majority));
        for (int y = 0; y < maj.size(); y++) {
            if (majority.get(maj.get(y)) == 0) {
                poscount++;
            } else {
                negcount++;
            }
        }
        if (poscount >= negcount) {
            return 0;
        } else {
            return 1;
        }
    }

    public HashMap<String, Integer> loadAll(int k) throws IOException {
        int i = 0;
        List<String> filenames = Files.list(Paths.get("C:\\Users\\Aris\\Desktop\\Τεχνητή\\aclImdb\\train\\neg"))
                .filter(Files::isRegularFile)
                .map(p -> p.getFileName().toString())
                .collect(Collectors.toList());
        while (i < k) {
            HashMap<String, Integer> temporary;
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Aris\\Desktop\\Τεχνητή\\aclImdb\\train\\neg\\" + filenames.get(i)));
            String tempor = reader.readLine();
            temporary = words(tempor);
            temporary.forEach((key, value) -> masterneg.merge(key, value, (oldValue, newValue) -> newValue = oldValue + newValue));
            example.put(tempor, 1);
            cneg++;
            i++;

        }
        List<String> filenames2 = Files.list(Paths.get("C:\\Users\\Aris\\Desktop\\Τεχνητή\\aclImdb\\train\\pos"))
                .filter(Files::isRegularFile)
                .map(p -> p.getFileName().toString())
                .collect(Collectors.toList());
        //read the positive reviews
        int j = 0;
        while (j < k) {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Aris\\Desktop\\Τεχνητή\\aclImdb\\train\\pos\\" + filenames2.get(j)));
            String tempor = reader.readLine();
            HashMap<String, Integer> temporary2;
            temporary2 = words(tempor);
            temporary2.forEach((key, value) -> masterpos.merge(key, value, (oldValue, newValue) -> newValue = oldValue + newValue));
            example.put(tempor, 0);
            cpos++;
            j++;

        }

        return example;

    }

    public void loadTest(int k) throws IOException {
        int i = 0;
        List<String> filenames = Files.list(Paths.get("C:\\Users\\Aris\\Desktop\\Τεχνητή\\aclImdb\\test\\neg"))
                .filter(Files::isRegularFile)
                .map(p -> p.getFileName().toString())
                .collect(Collectors.toList());

        while (i < k) {
            HashMap<String, Integer> temporary;
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Aris\\Desktop\\Τεχνητή\\aclImdb\\test\\neg\\" + filenames.get(i)));
            String tempor = reader.readLine();
            test.put(tempor, 1);
            i++;
        }
        List<String> filenames2 = Files.list(Paths.get("C:\\Users\\Aris\\Desktop\\Τεχνητή\\aclImdb\\test\\pos"))
                .filter(Files::isRegularFile)
                .map(p -> p.getFileName().toString())
                .collect(Collectors.toList());
        //read the positive reviews
        int j = 0;
        while (j < k) {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Aris\\Desktop\\Τεχνητή\\aclImdb\\test\\pos\\" + filenames2.get(j)));
            String tempor = reader.readLine();
            test.put(tempor, 0);
            j++;

        }

    }

    public ArrayList<String> getHashMapKey(HashMap<String, Integer> b) {
        ArrayList<String> list = new ArrayList<>();
        Set<Map.Entry<String, Integer>> set;
        set = b.entrySet();
        Iterator iterator = set.iterator();
        for (int o = 0; o < set.size(); o++) {
            Map.Entry f = (Map.Entry) iterator.next();
            list.add((String) f.getKey());
        }
        return list;
    }

    double calculatePossibility(HashMap<String, Integer> pos) {
        double result;
        ArrayList<Integer> local = new ArrayList<>();
        local.addAll(pos.values());
        int cull = 0;
        for (int q = 0; q < pos.size(); q++) {
            if (local.get(q) == 0) {
                cull++;
            }
        }
        result = cull / (double) pos.size();
        return result;
    }

    double calculateHasWord(HashMap<String, Integer> hasWord, String word, boolean flag) {
        double result;
        ArrayList<String> local = new ArrayList<>();
        local.addAll(getHashMapKey(hasWord));
        double hasPos = 0;
        double counterWord = 0;
        if (flag) {
            for (int q = 0; q < hasWord.size(); q++) {
                if (local.get(q).contains(word)) {
                    counterWord++;
                    if (hasWord.get(local.get(q)) == 0) {
                        hasPos++;
                    }
                }
            }
        } else {
            for (int q = 0; q < hasWord.size(); q++) {
                if (!local.get(q).contains(word)) {
                    counterWord++;
                    if (hasWord.get(local.get(q)) == 0) {
                        hasPos++;
                    }
                }
            }
        }
        result = hasPos / counterWord;
        return result;
    }

    double separate(HashMap<String, Integer> hasWord, String word) {
        ArrayList<String> local = new ArrayList<>();
        local.addAll(getHashMapKey(hasWord));

        double counterWord = 0;
        for (int q = 0; q < hasWord.size(); q++) {
            if (local.get(q).contains(word)) {
                counterWord++;
            }
        }
        return counterWord / (double) hasWord.size();
    }

}
