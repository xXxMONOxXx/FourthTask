package by.mishastoma.solver;

import by.mishastoma.exception.TextException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class PolishExpressionSolverTest {
    @Test
    public void solvePolishExpressionBase() throws TextException{
        List<String > expression = List.of("7", "5", "12", "*", "2", "5", "+", "2", "-", "71", "-", "*", "+", "12", "/");
        double expected = -329.4166666666667;
        double actual = PolishExpressionSolver.solve(expression);
        Assert.assertEquals(actual,expected);
    }

    @Test(expectedExceptions = TextException.class)
    public void solvePolishExpressionInvalidExpression() throws TextException{
        List<String > expression = List.of(")", "7", "5", "12", "*", "2", "5", "+", "2", "-", "71", "-", "*", "+", "12", "/");
        PolishExpressionSolver.solve(expression);
    }
}
