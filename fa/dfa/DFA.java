package fa.dfa;

import fa.State;

import java.util.*;

public class DFA implements DFAInterface{

    private Map<String, DFAState> states;
    private Set<Character> alphabet;
    private String startState;
    private Set<String> finalStates;
    private List<String> stateOrder; // List to store the order of added states
    private List<Character> symbolOrder; // List to store the order of added symbols
    private List<String> finalStateOrder; // List to store the order of final states

    public DFA(){
        this.states = new HashMap<>();
        this.alphabet = new HashSet<>();
        this.finalStates = new HashSet<>();
        this.stateOrder = new ArrayList<>(); // Initialize the state order list
        this.symbolOrder = new ArrayList<>(); // Initialize the symbol order list
        this.finalStateOrder = new ArrayList<>(); // Initialize the final state order list
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
        stateOrder.add(name); // Add state to the list to track insertion order
        return true;
    }

    @Override
    public boolean setFinal(String name) {
        if (states.containsKey(name)) {
            finalStates.add(name);
            if (!finalStateOrder.contains(name)) { // Ensure no duplicates in the list
                finalStateOrder.add(name); // Track final state insertion order
            }
            return true;
        } else {
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
        if (!alphabet.contains(symbol)) { // Ensure no duplicates
            alphabet.add(symbol);
            symbolOrder.add(symbol); // Track the order of symbol insertion
        }
    }

    @Override
    public boolean accepts(String s) {
        // Check if the start state is null (no states or no start state set)
        if (startState == null) {
            return false;  // Return false if there's no start state
        }

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Adding states (Q) in the order they were added
        sb.append("Q = { ");
        for (String stateName : stateOrder) {  // Use stateOrder list
            sb.append(stateName).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1); // Remove the trailing space
        sb.append(" }\n");  // Add space before closing brace

        // Adding alphabet (Sigma)
        sb.append("Sigma = { ");
        for (Character symbol : symbolOrder) { // Use symbolOrder list
            sb.append(symbol).append(" ");
        }
        if (!symbolOrder.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1); // Remove the trailing space if Sigma is not empty
        }
        sb.append(" }\n");

        // Adding transitions (delta) only if the alphabet (Sigma) is not empty
        sb.append("delta =\n");
        if (!symbolOrder.isEmpty()) {  // Only add delta if Sigma has symbols
            sb.append("\t\t"); // Add two tabs for column headers alignment
            for (Character symbol : symbolOrder) { // Use symbolOrder list
                sb.append(symbol).append("\t");
            }
            sb.append("\n");

            // Iterate over states in the order they were added
            for (String stateName : stateOrder) {  // Use stateOrder list
                DFAState state = states.get(stateName);
                sb.append("\t").append(state.getName()).append("\t"); // Print the state name
                for (Character symbol : symbolOrder) { // Use symbolOrder list
                    DFAState nextState = state.getTransition(symbol);
                    if (nextState != null) {
                        sb.append(nextState.getName()).append("\t");
                    } else {
                        sb.append("-\t"); // Handle missing transitions
                    }
                }
                sb.append("\n");
            }
        }

        // Adding start state (q0)
        sb.append("q0 = ").append(startState).append("\n");

        // Adding final states (F)
        sb.append("F = { ");
        for (String finalState : finalStateOrder) {  // Use finalStateOrder list
            sb.append(finalState).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1); // Remove the trailing space
        sb.append("}");

        return sb.toString();
    }
}
