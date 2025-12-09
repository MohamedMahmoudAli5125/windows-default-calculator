package com.calculator3.service;

import com.calculator3.model.CalculatorState;

public class OperatorHandler {

    public static CalculatorState handle(char operator, CalculatorState state) {
        if (!state.isRead1() && !state.isPastIsOper() && !state.isPastIsEqual()) {
            calculateAndUpdateState(state);
        }

        state.setOperator(operator);
        state.setPastIsOper(true);
        state.setPastIsEqual(false);
        state.setRead1(false);
        resetSpecialFlags(state);

        DisplayManager.updateOperatorDisplay(state);
        state.setDisplay2(NumberFormatter.format(Double.parseDouble(state.getNum2())));

        return state;
    }

    private static void calculateAndUpdateState(CalculatorState state) {
        double a = Double.parseDouble(state.getNum1());
        double b = Double.parseDouble(state.getNum2());
        double result = OperationHandler.getResult(a, b, state.getOperator());

        state.setNum1(String.valueOf(result));
        state.setDisplay2(NumberFormatter.format(result));
        state.setNum2("0");
    }

    private static void resetSpecialFlags(CalculatorState state) {
        state.setSfr2(false);
        state.setSfr1(false);
    }
}