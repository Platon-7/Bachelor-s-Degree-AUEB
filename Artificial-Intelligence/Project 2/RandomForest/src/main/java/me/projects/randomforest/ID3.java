package me.projects.randomforest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Math.abs;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;


public class ID3 {

    HashMap<String, Integer> test = new HashMap<>();
    ArrayList<String> train_attributes = new ArrayList<>();
    ArrayList<String> sorted = new ArrayList<>();
    Sort object = new Sort();
    Set<Map.Entry<String, Integer>> gensort;
    HashMap<String, Integer> master = new HashMap<>();
    HashMap<String, Integer> masterneg = new HashMap<>();
    HashMap<String, Integer> masterpos = new HashMap<>();
    int cpos, cneg = 0;
    HashMap<String, Integer> example = new HashMap<>();
    TreeMap<String, HashMap<String, Integer>> treemap = new TreeMap<>();
    TreeMap<String, Double> treemapBETA = new TreeMap<>();

    public HashMap<String, Integer> words(String a, int value) {
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

    public ArrayList<String> getAttributes(int l) {
        ArrayList<String> attributes = new ArrayList<>();
        master.putAll(masterneg);
        masterpos.forEach((key, value) -> master.merge(key, value, (oldValue, newValue) -> newValue = oldValue + newValue));
        gensort = object.sort(master);
        Iterator iterator = gensort.iterator();
        for (int n = 0; n < l; n++) {
            Map.Entry f = (Map.Entry) iterator.next();
            sorted.add((String) f.getKey());
        }

        double px, py;
        for (int i = 0; i < l; i++) {
            if (masterpos.get(sorted.get(i)) == null) {
                px = 0;
            } else {
                px = ((double) (masterpos.get(sorted.get(i))) / (double) (masterpos.size()));
            }
            if (masterneg.get(sorted.get(i)) == null) {
                py = 0;
            } else {
                py = ((double) (masterneg.get(sorted.get(i))) / (double) (masterneg.size()));
            }
            if (abs((px) - (py)) > 0.003) {
                attributes.add(sorted.get(i));
            }
        }
        return attributes;
    }

    public int ID3_train(HashMap<String, Integer> paradeigmata, ArrayList<String> att, int k) {
        ArrayList<String> sent = new ArrayList<>();
        HashMap<String, Integer> keys = new HashMap<>();
        HashMap<String, Integer> brokenkeys = new HashMap<>();
        HashMap<String, Integer> copycat = new HashMap<>();
        ArrayList<String> local;
        ArrayList<String> neighbour;
        copycat.putAll(paradeigmata);
        String best;
        if (paradeigmata.isEmpty()) {
            return 0;
        } else if (!paradeigmata.containsValue(1)) {
            return 0;
        } else if (!paradeigmata.containsValue(0)) {
            return 1;
        } else if (att.isEmpty()) {
            if (findMajority(paradeigmata) == 0) {
                return 0;
            } else {
                return 1;
            }
        } else {
            best = calculateEntropy(att, paradeigmata);
            train_attributes.add(best);
            sent.addAll(getHashMapKey(paradeigmata));
            for (int i = 0; i < sent.size(); i++) {
                if (sent.get(i).contains(best)) {
                    keys.put(sent.get(i), paradeigmata.get(sent.get(i)));
                } else {
                    brokenkeys.put(sent.get(i), paradeigmata.get(sent.get(i)));
                }
            }
            double possibility = 0;
            if (keys.size() > 0) {
                possibility = calculatePossibility(keys);
            }
            treemapBETA.put(best + " 0", possibility);

            treemap.put(best + " 0", keys);
            if (brokenkeys.size() > 0) {
                possibility = calculatePossibility(brokenkeys);
                treemapBETA.put(best + " 1", possibility);
            } else {
                treemapBETA.put(best + " 1", 0.0);
            }
            treemap.put(best + " 1", brokenkeys);
        }
        att.remove(best);
        local = getHashMapKey(brokenkeys);
        neighbour = getHashMapKey(keys);
        ID3_train(paradeigmata, att, findMajority(paradeigmata));
        for (int x = 0; x < local.size(); x++) {
            paradeigmata.remove(local.get(x));
        }
        ID3_train(paradeigmata, att, findMajority(copycat));
        for (int x = 0; x < neighbour.size(); x++) {
            copycat.remove(neighbour.get(x));
        }
        return 0;
    }

    public ArrayList<Integer> ID3_test() {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<String> test2 = new ArrayList<>();
        test2.addAll(getHashMapKey(test));
        int variable = 0;
        for (int w = 0; w < test.size(); w++) {
            for (int u = 0; u < train_attributes.size(); u++) {
                //me bash to tree map
                if (test2.get(w).contains(train_attributes.get(u))) {
                    if (treemapBETA.get(train_attributes.get(u) + " 0") > 0.6) {
                        variable = 0;

                        break;
                    } else if (treemapBETA.get(train_attributes.get(u) + " 0") < 0.4) {
                        variable = 1;
                        break;
                    }
                } else {
                    if (treemapBETA.get(train_attributes.get(u) + " 1") > 0.6) {
                        variable = 0;
                        break;
                    } else if (treemapBETA.get(train_attributes.get(u) + " 1") < 0.4) {
                        variable = 1;
                        break;
                    }
                }
            }
            //Forest.matrix[w]+= variable;
            list.add(variable);
        }
        return list;
    }

    public String calculateEntropy(ArrayList<String> att, HashMap<String, Integer> paradeigmata) {
        int count = -1;
        double max = 0;
        double IG[] = new double[att.size()];
        double has_word = 0.0;
        double not_word = 0.0;
        double has_wordPos = 0.0;
        double has_wordNeg = 0.0;
        double not_wordPos = 0.0;
        double not_wordNeg = 0.0;
        double entropyPos = 0.0;
        double entropyNeg = 0.0;
        double positive = calculatePossibility(paradeigmata);
        double negative = 1.0 - positive;
        double entropy = 0.0;
        entropy = +(-(positive) * ((double) (Math.log((positive + 1))) / (double) (Math.log(2))) - (((negative)) * ((double) (Math.log(negative + 1)))) / (double) (Math.log(2)));
        for (int i = 0; i < att.size(); i++) {
            has_word = separate(paradeigmata, att.get(i));
            not_word = 1.0 - has_word;
            has_wordPos = calculateHasWord(paradeigmata, att.get(i), true);
            has_wordNeg = 1.0 - has_wordPos;
            not_wordPos = calculateHasWord(paradeigmata, att.get(i), false);
            not_wordNeg = 1 - not_wordPos;
            entropyPos = -(has_wordPos) * ((double) (Math.log((has_wordPos + 1))) / (double) (Math.log(2))) - (((has_wordNeg)) * ((double) (Math.log(has_wordNeg + 1)))) / (double) (Math.log(2));
            entropyNeg = -(not_wordPos) * ((double) (Math.log((not_wordPos + 1))) / (double) (Math.log(2))) - (((not_wordNeg)) * ((double) (Math.log(not_wordNeg + 1)))) / (double) (Math.log(2));
            IG[i] = entropy - entropyPos * has_word - entropyNeg * not_word;
            if (count == -1) {
                max = IG[i];
                count++;
            } else if (IG[i] > max) {
                max = IG[i];
                count = i;
            }
        }

        return att.get(count);
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
            temporary = words(tempor, 1);
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
            temporary2 = words(tempor, 0);
            temporary2.forEach((key, value) -> masterpos.merge(key, value, (oldValue, newValue) -> newValue = oldValue + newValue));
            example.put(tempor, 0);
            cpos++;
            j++;

        }

        return example;

    }

    public HashMap<String, Integer> loadTest(int k) throws IOException {
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
        return test;
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
