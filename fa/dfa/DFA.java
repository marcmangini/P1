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
        DFA newDFA = new DFA();

        // Copies the alphabet from the current DFA to the new one.
        newDFA.alphabet = new HashSet<>(this.alphabet);
    
        // Ensures the swapped symbols are in the alphabet
        if (this.alphabet.contains(symb1)) {
            newDFA.alphabet.add(symb2);
        }
        if (this.alphabet.contains(symb2)) {
            newDFA.alphabet.add(symb1);
        }
    
        // Copies states from the current DFA to the new one
        for (DFAState state : states.values()) {
            DFAState newState = new DFAState(state.getName());
            newDFA.states.put(newState.getName(), newState);
        }
    
        // Copy transitions with swapping
        for (DFAState state : states.values()) {
            DFAState newState = newDFA.states.get(state.getName());
            for (Map.Entry<Character, DFAState> entry : state.getTransitions().entrySet()) {
                char symbol = entry.getKey();
                DFAState nextState = newDFA.states.get(entry.getValue().getName());
    
                if (symbol == symb1) {
                    newDFA.addTransition(newState.getName(), nextState.getName(), symb2);
                } else if (symbol == symb2) {
                    newDFA.addTransition(newState.getName(), nextState.getName(), symb1);
                } else {
                    newDFA.addTransition(newState.getName(), nextState.getName(), symbol);
                }
            }
        }
    
        // Copies the start state and final states
        newDFA.startState = this.startState;
        newDFA.finalStates = new HashSet<>(this.finalStates);
    
        return newDFA;
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
        DFAState currentState = states.get(startState);

        for (char symbol : s.toCharArray()) {
            if (currentState.getTransition(symbol) != null) {
                currentState = currentState.getTransition(symbol);
            } else {
                return false;
            }
        }

        return finalStates.contains(currentState.getName());
    }

    @Override
    public Set<Character> getSigma() {
        return new HashSet<>(alphabet);
    }

    @Override
    public State getState(String name) {
        return states.get(name);
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
