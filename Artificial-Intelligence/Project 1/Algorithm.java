package me.projects.bridge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Algorithm {

    private ArrayList<State> states = new ArrayList<State>();
    private HashSet<State> closedSet;

    public State Algorithm(State initialState) {
        closedSet = new HashSet<>();
        states.add(initialState);
        while (states.size() > 0) {
            
            State currentState = states.remove(0);
            if (currentState.isTerminal()) {
                return currentState;
            }
            if (!closedSet.contains(currentState)) {
                closedSet.add(currentState);
                states.addAll(currentState.getChildren());
                Collections.sort(states);
            }
        }
        return null;
    }
}
