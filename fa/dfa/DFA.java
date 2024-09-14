package fa.dfa;

import fa.State;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DFA implements DFAInterface{

    private Set<String> states;
    private Set<Character> alphabet;
    private String startState;
    private Set<String> finalStates;
    private Map<String, Map<Character, String>> transitions;
    
    public DFA(){
        this.states = new HashSet<>();
        this.alphabet = new HashSet<>();
        this.finalStates = new HashSet<>();
        this.transitions = new HashMap<>();
    }

    @Override
    public boolean addTransition(String fromState, String toState, char onSymb) {
        return false;
    }

    @Override
    public DFA swap(char symb1, char symb2) {
        return null;
    }

    @Override
    public boolean addState(String name) {
        return states.add(name);
    }

    @Override
    public boolean setFinal(String name) {
        if (states.contains(name)) {
            finalStates.add(name);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean setStart(String name) {
         if (states.contains(name)) {
            this.startState = name;
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void addSigma(char symbol) {
        alphabet.add(symbol);
    }

    @Override
    public boolean accepts(String s) {
        return false;
    }

    @Override
    public Set<Character> getSigma() {
        return alphabet;
    }

    @Override
    public State getState(String name) {
        return null;
    }

    @Override
    public boolean isFinal(String name) {
        return finalStates.contains(name);
    }

    @Override
    public boolean isStart(String name) {
        return startState != null && startState.equals(name);
    }
}
