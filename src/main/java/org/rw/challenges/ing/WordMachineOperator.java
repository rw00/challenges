package org.rw.challenges.ing;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;


public class WordMachineOperator {
    public static final int ERROR_RESULT = -1;
    private static final Map<String, BiConsumer<Deque<Integer>, String>> FUNCTIONS = createSupportedOperationsMap();
    private static final String PUSH_COMMAND = "PUSH";
    private static final long MIN = 0;
    private static final long MAX = 1048575;

    public int solution(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        String[] commands = s.split("\\s"); // assuming strictly valid input
        for (String command : commands) {
            try {
                BiConsumer<Deque<Integer>, String> function = FUNCTIONS.getOrDefault(command, FUNCTIONS.get(PUSH_COMMAND));
                function.accept(stack, command);
            } catch (OperationException ignore) {
                return ERROR_RESULT;
            }
        }
        return stack.isEmpty() ? ERROR_RESULT : stack.peek();
    }

    private static Map<String, BiConsumer<Deque<Integer>, String>> createSupportedOperationsMap() {
        Map<String, BiConsumer<Deque<Integer>, String>> supportedOperations = new HashMap<>();
        supportedOperations.put(PUSH_COMMAND, WordMachineOperator::push);
        supportedOperations.put("POP", (stack, command) -> pop(stack));
        supportedOperations.put("DUP", (stack, command) -> dup(stack));
        supportedOperations.put("+", (stack, command) -> add(stack));
        supportedOperations.put("-", (stack, command) -> subtract(stack));
        return supportedOperations;
    }

    private static void push(Deque<Integer> stack, String num) {
        try {
            int value = Integer.parseInt(num);
            check(value >= MIN && value <= MAX);
            stack.push(value);
        } catch (NumberFormatException translated) {
            throw new OperationException();
        }
    }

    private static void pop(Deque<Integer> stack) {
        check(!stack.isEmpty());
        stack.pop();
    }

    private static void dup(Deque<Integer> stack) {
        check(!stack.isEmpty());
        stack.push(stack.peek());
    }

    private static void add(Deque<Integer> stack) {
        check(stack.size() >= 2);
        int result = stack.pop() + stack.pop();
        check(result <= MAX);
        stack.push(result);
    }

    private static void subtract(Deque<Integer> stack) {
        check(stack.size() >= 2);
        int result = stack.pop() - stack.pop();
        check(result >= MIN);
        stack.push(result);
    }

    private static void check(boolean truthfulValue) {
        if (!truthfulValue) {
            throw new OperationException();
        }
    }

    static class OperationException extends RuntimeException { // marker exception
    }
}
