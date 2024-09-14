package fa.dfa;

import fa.State;
import java.util.HashMap;
import java.util.Map;

public class DFAState extends State {

    // This is just meant to store transitions for this state.
    private HashMap<Character, DFAState> transitions;

    /**
     * This constructor sets the state's name
     * @param name
     */
    public DFAState(String name){
        super(name);
        transitions = new HashMap<>();
    }

    /**
     * Helper method to add a transition from this state on a given symbol.
     * @param symb
     * @param nextState
     */
    public void addTransition(char symb, DFAState nextState){
        transitions.put(symb, nextState);
    }

    /**
     * Helper method to get the next state based on a the symbol.
     * @param symb
     * @return
     */
    public DFAState getTransition(char symb){
        return transitions.get(symb);
    }

     /**
      * Getter for all transitions.
      * @return
      */
    public Map<Character, DFAState> getTransitions() {
        return transitions;
    }
}
