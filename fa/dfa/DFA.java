package fa.dfa;

import fa.State;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
public class DFA implements DFAInterface{

    private Map<String, DFAState> states;
    private Set<Character> alphabet;
    private String startState;
    private Set<String> finalStates;
    
    public DFA(){
        this.states = new HashMap<>();
        this.alphabet = new HashSet<>();
        this.finalStates = new HashSet<>();
    }

    @Override
    public boolean addTransition(String fromState, String toState, char onSymb) {
        DFAState from = states.get(fromState);
        DFAState to = states.get(toState);
        
        if (from == null || to == null || !alphabet.contains(onSymb)) {
            return false;
        }
        
        from.addTransition(onSymb, to);
        return true;
    }

    @Override
    public DFA swap(char symb1, char symb2) {
        return null;
    }

    @Override
    public boolean addState(String name) {
        if (states.containsKey(name)) {
            return false; 
        }
        states.put(name, new DFAState(name));
        return true;
    }

    @Override
    public boolean setFinal(String name) {
        if (states.containsKey(name)) {
            finalStates.add(name);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean setStart(String name) {
         if (states.containsKey(name)) {
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
