package by.mishastoma.converter.impl;

import by.mishastoma.converter.ExpressionConverter;
import by.mishastoma.entity.ExpressionSymbols;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class RegularExpressionToPolishConverter implements ExpressionConverter {

    private static final int OPEN_BRACKET_PRIORITY = 0;
    private static final int PLUS_MINUS_PRIORITY = 1;
    private static final int DEFAULT_PRIORITY = 2;


    private List<String> result;
    private Deque<String> stack;

    @Override
    public List<String> convert(List<String> data) {
        result = new ArrayList<>();
        stack = new ArrayDeque<>();
        for (String token : data) {
            if (isNumber(token)) {
                result.add(token);
            } else if (ExpressionSymbols.OPEN_BRACKET.equals(token)) {
                stack.push(token);
            } else if (ExpressionSymbols.CLOSE_BRACKET.equals(token)) {
                while (!ExpressionSymbols.OPEN_BRACKET.equals(stack.peek())) {
                    result.add(stack.pop());
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && getPriority(stack.peek()) >= getPriority(token)) {
                    result.add(stack.pop());
                }
                stack.push(token);
            }
        }
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    private boolean isNumber(String token) {
        return Character.isDigit(token.charAt(0));
    }

    private int getPriority(String operation) {
        if (operation.equals("(")) {
            return OPEN_BRACKET_PRIORITY;
        } else if (operation.equals("+") || operation.equals("-")) {
            return PLUS_MINUS_PRIORITY;
        } else {
            return DEFAULT_PRIORITY;
        }
    }

}
