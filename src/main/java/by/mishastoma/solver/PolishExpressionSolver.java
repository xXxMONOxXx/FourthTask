package by.mishastoma.solver;

import by.mishastoma.entity.ExpressionSymbols;
import by.mishastoma.exception.TextException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class PolishExpressionSolver {

    private static final Logger logger = LogManager.getLogger();

    public static Double solve(List<String> expression) throws TextException {
        try {
            Deque<Double> stack = new ArrayDeque<>();
            Double a;
            Double b;
            for (String token : expression) {
                switch (token) {
                    case ExpressionSymbols.PLUS -> {
                        a = stack.pop();
                        b = stack.pop();
                        stack.push(a + b);
                    }
                    case ExpressionSymbols.MINUS -> {
                        a = stack.pop();
                        b = stack.pop();
                        stack.push(b - a);
                    }
                    case ExpressionSymbols.MULTIPLY -> {
                        a = stack.pop();
                        b = stack.pop();
                        stack.push(b * a);
                    }
                    case ExpressionSymbols.DIVIDE -> {
                        a = stack.pop();
                        b = stack.pop();
                        stack.push(b / a);
                    }
                    default -> stack.push(Double.parseDouble(token));
                }
            }
            return stack.pop();
        }
        catch (Exception e){
            logger.error("Invalid expression {}", expression);
            throw new TextException(String.format("Invalid expression %s", expression));
        }
    }
}
