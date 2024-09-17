# CS 361: Project 1 - Deterministic Finite Automaton (DFA)

## Authors:
- Marc Mangini
- Aarik Guy

## Program Description:
This project implements a deterministic finite automaton (DFA) in Java. The DFA is modeled using Java classes, interfaces, and abstract classes. The project focuses on:
- Familiarization with Java packages, specifically the `fa` and `fa.dfa` packages.
- Utilizing the Java Collections API to represent DFA elements such as states, transitions, and alphabet symbols.
- Implementing object-oriented design principles by writing classes such as `DFA` and `DFAState`.
- Applying test-based development using JUnit test cases to validate the functionality of the DFA.

The program allows users to:
- Add states to the DFA.
- Set the start and final states.
- Define transitions between states.
- Check if a given string is accepted by the DFA.
- Swap transition symbols between states.

## Program Structure:
- **fa.FAInterface**: Interface that defines basic functionalities for any finite automaton.
- **fa.State**: Abstract class representing a state in a finite automaton.
- **fa.dfa.DFAInterface**: Interface that extends `FAInterface`, adding methods specific to DFA.
- **fa.dfa.DFA**: Class to be implemented, modeling a DFA. Implements `DFAInterface`.
- **fa.dfa.DFAState**: Class to be implemented, representing a DFA state. Extends `State`.
- **test.dfa.DFATest**: JUnit test class provided to validate the DFA implementation. It contains multiple tests to check the functionality.

## Compilation Instructions:
1. Open a terminal and navigate to the top directory containing your source files.
2. Compile the DFA implementation using the following command: javac fa/dfa/DFA.java fa/dfa/DFAState.java
3. Compile the JUnit test cases using the following command: javac -cp .:/usr/share/java/junit.jar ./test/dfa/DFATest.java

## Running the Tests:
1. To run the JUnit test cases, use the following command: java -cp .:/usr/share/java/junit.jar:/usr/share/java/hamcrest/core.jar org.junit.runner.JUnitCore test.dfa.DFATest

This will run the provided test cases in `DFATest.java` to check if your DFA implementation passes all the tests.

## Class Descriptions:
- **DFA**:
- Implements `DFAInterface`.
- Contains methods for adding states, transitions, and defining the start and final states.
- Implements the transition function and string acceptance logic.
- Includes a `toString()` method to display DFA details in a formatted output.
- Implements the `swap(char symb1, char symb2)` method to swap transition labels.

- **DFAState**:
- Extends the `State` abstract class.
- Stores transition information for each state.
- Uses `java.util.HashMap` to manage transitions between states.

## Additional Notes:
- Ensure that the `toString()` method in `DFA` follows the format expected by the test cases.
- You are encouraged to write additional test cases to further validate your DFA implementation.




