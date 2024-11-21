package src;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Math;


public class RandomizedBST implements MafiaInterface {

    private class TreeNode {

        Suspect item;
        TreeNode left; // pointer to left subtree
        TreeNode right; // pointer to right subtree
        int N; //number of nodes in the subtree rooted at this TreeNode

        private TreeNode(Suspect item) {
            this.item = item;
        }

        public TreeNode(Suspect item, TreeNode l, TreeNode r, int N) {
            this.item = item;
            this.N = l.N + r.N;
            this.left = l;
            this.right = r;
        }

    }
    private TreeNode root;
    List list = new List();
    static Heapsort heap = new Heapsort();
    double savings_array[];
    Suspect afm_array[];
    int count = 0;
    int second_count = 0;
    int caller = 0;
    int helping_hand = -1;
    int helping_hand_2 = 0;

    @Override
    public void insert(Suspect item) {
        if (searchByAFM(item.getAFM()) != null) {
            System.out.println("Suspect already exists");
        } else {
            root = insertAsRoot(root, item);
            if (root != null && helping_hand_2 < root.N) {
                System.out.println("The new suspect was added successfully!");
            }
        }
    }

    private TreeNode insertT(TreeNode theroot, Suspect x) {
        if (theroot == null) {
            TreeNode tn = new TreeNode(x);
            tn.N = 1;
            return tn;
        }
        if (less(x.key(), theroot.item.key())) {
            theroot.left = insertT(theroot.left, x);
            theroot = rotR(theroot);
            theroot.N++;
        } else {
            theroot.right = insertT(theroot.right, x);
            theroot = rotL(theroot);
            theroot.N++;
        }
        return theroot;
    }

    private TreeNode insertAsRoot(TreeNode theroot, Suspect x) {
        if (theroot == null) {
            TreeNode tn = new TreeNode(x);
            tn.N = 1;
            return tn;
        }
        if (Math.random() * (theroot.N + 1) < 1.0) {
            return insertT(theroot, x);
        }
        if (less(x.key(), theroot.item.key())) {
            theroot.left = insertAsRoot(theroot.left, x);
            theroot.N++;
        } else {
            theroot.right = insertAsRoot(theroot.right, x);
            theroot.N++;
        }
        return theroot;
    }

    @Override
    public void load(String filename){
        try ( BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            for (int i = 0; i < 99999; i++) {
                String txt = reader.readLine();
                if (txt == null) {
                    break;
                } else {
                    String splitted[] = txt.split(" ");
                    Suspect suspect = new Suspect(Integer.parseInt(splitted[0]), splitted[1], splitted[2], Double.parseDouble(splitted[3]), Double.parseDouble(splitted[4]));
                    insert(suspect);
                }
            }
            reader.close();
        }catch(IOException ex){
        System.out.println (ex.toString());
        System.out.println("Could not find file " + filename);
    }
    }

    @Override
    public void updateSavings(int AFM, double savings) {
        Suspect komvos = searchR(root, AFM);
        if (komvos == null) {
            System.out.println("The AFM you entered does not exist. ");
        } else {
            komvos.setSavings(savings);
            System.out.println("Savings updated.");
        }
    }

    @Override
    public Suspect searchByAFM(int AFM) {
        helping_hand++;
        helping_hand_2++;
        return searchR(root, AFM);
    }

    @Override
    public List searchByLastName(String last_name) {
        searchEveryNode(last_name, root);
        if (list.size() <= 5 && list.size > 0) {
            list.display();
            return list;
        } else if (list.size() == 0) {
            System.out.println("The list is null");
            return null;
        } else {
            System.out.println("The suspects with that name are too many");
            return list;
        }
    }

    public TreeNode searchEveryNode(String a, TreeNode root) {
        TreeNode result = null;
        if (root == null) {
            return null;
        }
        if (root.item.getLastName().equals(a)) {
            list.addNode(root.item);
        }
        if (root.left != null) {
            result = searchEveryNode(a, root.left);
        }
        if (result == null) {
            result = searchEveryNode(a, root.right);
        }
        return result;
    }

    private Suspect searchR(TreeNode r, int v) {
        if (r == null && root != null && helping_hand > root.N) {
            System.out.println("The suspect you are looking for does not exist");
            return null;
        } else if (r == null) {
            return null;
        }
        if (r.item.key() == v && helping_hand == root.N) {
            return r.item;
        } else if (r.item.key() == v && helping_hand > root.N) {
            System.out.println(r.item.getAFM() + " " + r.item.getFirstName() + " " + r.item.getLastName() + " " + r.item.getSavings() + " " + r.item.getTaxedIncome());
            return r.item;
        }
        if (less(v, r.item.key())) {
            return searchR(r.left, v);
        } else {
            return searchR(r.right, v);
        }
    }

    private int countR(TreeNode h) {
        if (h == null) {
            return 0;
        }
        return 1 + countR(h.left) + countR(h.right);
    }

    int count() {
        return countR(root);
    }

    @Override
    public void remove(int AFM) {
        Suspect temp = searchR(root, AFM);
        if (temp == null) {
            System.out.println("The AFM you entered does not belong to any suspect");
        } else {
            root = removeR(root, AFM);
            System.out.println("The suspect was successfully removed!");
            helping_hand--;
            helping_hand_2--;
        }
    }

    private TreeNode removeR(TreeNode h, int AFM) {
        if (h == null) {
            return null;
        }
        int w = h.item.key();
        if (less(AFM, w)) {
            h.left = removeR(h.left, AFM);
            

        }
        if (less(w, AFM)) {
            h.right = removeR(h.right, AFM);

        }
        if (AFM == w) {//an mpw edw shmainei oti tha kanw delete
            h = joinLR(h.left, h.right);
            root.N--;
        }
        return h;
    }

    private TreeNode joinLR(TreeNode a, TreeNode b) {

        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        int N = a.N + b.N;
        if (Math.random() * N < 1.0 * a.N) {
            a.right = joinLR(a.right, b);
            a.N = N;
            return a;
        } else {
            b.left = joinLR(a, b.left);
            b.N=N;
            return b;
        }
    }

    private TreeNode rotL(TreeNode theroot) {
        int temp = 0;
        if (theroot.right != null) {
            temp = theroot.N;
            theroot.right.N = theroot.N;

        }//an exei kai ta dyo paidia poy mporei na exei tote athroise ta kai prosthese 1
        if (theroot.right != null && theroot.right.left != null && theroot.left != null) {
            theroot.N = theroot.left.N + theroot.right.left.N + 1;
            if(temp==theroot.N) temp=-1;
        } else {//ean exei mono to ena pare to N toy kai prosthese 1
            if (theroot.right != null && theroot.right.left != null) {
                theroot.N = theroot.right.left.N + 1;
                if(temp==theroot.N) temp=-1;
            }
            if (theroot.left != null) {//ean exei to allo pare to N toy kai prosthese 1
                theroot.N = theroot.left.N + 1;
                if(temp==theroot.N) temp=-1;
            }
        }//ean den exei kanena paidi tote to theroot.N den allakse, mpes edw kai bale oti exei N=1
        if (theroot.N == temp) {
            theroot.N = 1;
        }
        TreeNode x = theroot.right;
        theroot.right = x.left;
        x.left = theroot;
        return x;
    }

    private TreeNode rotR(TreeNode theroot) {
        int temp =0;
        if (theroot.left != null) {
            temp = theroot.N;
            theroot.left.N = theroot.N;
        }
        if (theroot.left != null && theroot.left.right != null && theroot.right != null) {
            theroot.N = theroot.right.N + theroot.left.right.N + 1;//an o komvos exei kai aristero kai deksi paidi
            if(temp==theroot.N) temp=-1;
        } else {
            if (theroot.left != null && theroot.left.right != null) {//an exei mono deksi
               theroot.N = theroot.left.right.N + 1;
                if(temp==theroot.N) temp=-1;
            }
            if (theroot.right != null) {// an exei mono aristero
                theroot.N = theroot.right.N + 1;
                if(temp==theroot.N) temp=-1;
            }
        }
        if (theroot.N == temp) {
            theroot.N = 1;
        }
        TreeNode x = theroot.left;
        theroot.left = x.right;
        x.right = theroot;
        return x;
    }

    @Override
    public double getMeanSavings() {
        int length = count();
        savings_array = new double[length];
        caller = 0;
        crossTree(root);
        double sum = 0;
        double mean;
        for (int j = 0; j < savings_array.length; j++) {
            sum += savings_array[j];
        }
        mean = sum / (double) savings_array.length;
        return mean;
    }

    private TreeNode crossTree(TreeNode root) {
        TreeNode result = null;
        if (root == null) {
            return null;
        }
        if (root.item != null) {
            if (savings_array != null && caller == 0) {
                savings_array[count] = root.item.getSavings();
                count++;
            }
            if (afm_array != null && caller == 1) {
                afm_array[second_count] = root.item;
                second_count++;
            }
        }
        if (root.left != null) {
            result = crossTree(root.left);
        }
        if (result == null) {
            result = crossTree(root.right);
        }
        return result;
    }

    boolean less(int key, int key0) {
        if (key < key0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void printΤopSuspects(int k) {
        Suspect local[];
        int length = count();
        caller = 1;
        if (k > length) {
            k = length;
            System.out.println("We are sorry, we have only " + length + " suspects. Here they are: ");
        }
        afm_array = new Suspect[length];
        crossTree(root);
        local = heap.sort(afm_array, afm_array.length, 1);//shmaforos gia to poion comparator tha xrhsimopoihsw
        for (int l = 0; l < k; l++) {
            System.out.println(local[l].getAFM() + " " + local[l].getFirstName() + " " + local[l].getLastName() + " " + local[l].getSavings() + " " + local[l].getTaxedIncome() + " " + local[l].getSubtraction());
        }
    }

    @Override
    public void printByAFM() {
        System.out.println("We present you all of our suspects, printed in descending AFM order!");
        Suspect temp[];
        int length = count();
        afm_array = new Suspect[length];
        caller = 1;
        crossTree(root);
        temp = heap.sort(afm_array, afm_array.length, 0);
        for (int n = 0; n < temp.length; n++) {
            System.out.println(temp[n].getAFM() + " " + temp[n].getFirstName() + " " + temp[n].getLastName() + " " + temp[n].getSavings() + " " + temp[n].getTaxedIncome());
        }
    }

}
