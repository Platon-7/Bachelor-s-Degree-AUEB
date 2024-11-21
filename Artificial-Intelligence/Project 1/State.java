package me.projects.bridge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class State implements Comparable<State> {
	private static HashMap<Integer, String> associate = new HashMap<>();
    private ArrayList<Integer> left = new ArrayList<Integer>();
    private ArrayList<Integer> right = new ArrayList<Integer>();
    private int score;
    private boolean lamp;
    private State father;
 
    public State(ArrayList<Integer> right,ArrayList<String> names) {
        for (int i = 0; i < right.size(); i++) {
            this.right.add(right.get(i));
            associate.put(right.get(i), names.get(i)+'('+right.get(i)+')');
        }
        this.score = 0;
        this.lamp = true;
        this.father = null;
    }
    
    public State(ArrayList<Integer> left, ArrayList<Integer> right, int score, State father, boolean lamp) {
        for (int i = 0; i < right.size(); i++) {
            this.right.add(right.get(i));
        }
        for (int i = 0; i < left.size(); i++) {
            this.left.add(left.get(i));
        }
        this.score = score;
        this.lamp = lamp;
        this.father = father;
    }
    
    public ArrayList<State> getChildren() {
        ArrayList<State> children = new ArrayList<State>();
        State child = new State(this.left, this.right, this.score, this.father, this.lamp);
        State tempfather = child;
        if (child.lamp) {
            for (int k = 0; k < this.right.size(); k++) {
                for (int l = k + 1; l < this.right.size(); l++) {
                    this.heuristicRight(k, l);
                    child = new State(this.left, this.right, this.score, this.father, false);
                    child.setFather(tempfather);
                    children.add(child);
                    //reset the state to father's values to get next pair
                    child.setScore(tempfather.score);
                    this.setRight(tempfather.right);
                    this.setLeft(tempfather.left);
                }
            }

        } else {
            if (left.size() > 0 && right.size() > 0) {
                this.heuristicLeft();
                this.setFather(tempfather);
                children.add(this);
                this.lamp = true;
            }
        }
        return children;
    }

    public void heuristicRight(int k, int l) {

        this.left.add(this.right.get(k));
        this.left.add(this.right.get(l));
        if (this.right.get(k)>=this.right.get(l)) {
        	this.score = right.get(k);
        }else {
        	this.score = right.get(l);
        }
        this.right.remove(k);
        this.right.remove(l - 1);
        this.lamp = false;
    }

    public void heuristicLeft() {
        this.right.add(left.get(minIndex(left)));
        this.score += (this.left.get(minIndex(left)));
        this.left.remove(minIndex(left));
    }
    
    public void print() {
        for (int i = 0; i < this.left.size(); i++) {
            System.out.print(associate.get(this.left.get(i)) + " ");
        }
        if (!lamp) {
            System.out.print("(Lamp)");
            System.out.print("|<<<<<<<<<<<<<<<<<<<|");           
        }else if(this.left.isEmpty()) {
        	System.out.print("|===================|");
        }else 
        	System.out.print("|>>>>>>>>>>>>>>>>>>>|");
        for (int i = 0; i < this.right.size(); i++) {
            System.out.print(associate.get(this.right.get(i)) + " ");
        }
        if (lamp) {
            System.out.print("(Lamp)");
        }
        System.out.println();
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score += score;
    }

    public State getFather() {
        return this.father;
    }

    public void setLeft(ArrayList<Integer> left) {
        this.left.clear();
        for (int i = 0; i < left.size(); i++) {
            this.left.add(left.get(i));
        }
    }

    public void setRight(ArrayList<Integer> right) {
        this.right.clear();
        for (int i = 0; i < right.size(); i++) {
            this.right.add(right.get(i));
        }
    }

    public void setFather(State father) {
        this.father = father;
    }

    public static int minIndex(ArrayList<Integer> list) {
        return list.indexOf(Collections.min(list));
    }
    
    public boolean isTerminal() {
        if (this.right.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(State s) {
        return Double.compare(this.score, s.score);
    }

    @Override
    public int hashCode() {
        return this.right.size() + this.left.size() + this.score;
    }
}	