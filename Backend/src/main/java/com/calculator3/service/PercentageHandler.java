package com.calculator3.service;

import com.calculator3.model.CalculatorState;

public class PercentageHandler {

    public static CalculatorState handle(CalculatorState state) {
        double n1 = Double.parseDouble(state.getNum1());
        double n2 = Double.parseDouble(state.getNum2());
        char op = state.getOperator();
        double result;

        if (op == '\0' && !state.isPastIsEqual()) {
            return new CalculatorState();
        } else if (op == '\0' && state.isPastIsEqual()) {
            state.setNum1(NumberFormatter.format(n1));
            state.setNum2("0");
            state.setDisplay2(state.getNum1());
            DisplayManager.updateFullExpressionDisplay(state, '1');
            state.setSfr1(true);
            return state;
        }

        switch (op) {
            case '+':
            case '-':
                if (state.isPastIsEqual()) {
                    result = (n1 * n1) / 100.0;
                    state.setNum1(NumberFormatter.format(result));
                    state.setSfr1(true);
                    state.setSfr2(false);
                    DisplayManager.updateFullExpressionDisplay(state, '1');
                    state.setDisplay2(state.getDisplay1());
                } else if (state.isPastIsOper()) {
                    result = (n1 * n1) / 100.0;
                    state.setNum2(NumberFormatter.format(result));
                    state.setSfr2(true);
                    state.setSfr1(false);
                    DisplayManager.updateFullExpressionDisplay(state, '2');
                    state.setDisplay2(state.getNum2());
                } else if (!state.isRead1()) {
                    result = (n1 * n2) / 100.0;
                    state.setNum2(NumberFormatter.format(result));
                    state.setSfr2(true);
                    state.setSfr1(false);
                    DisplayManager.updateFullExpressionDisplay(state, '2');
                    state.setDisplay2(state.getNum2());
                }
                break;
            default:
                if (state.isPastIsEqual()) {
                    result = n1 / 100.0;
                    state.setNum1(NumberFormatter.format(result));
                    state.setSfr1(true);
                    state.setSfr2(false);
                    DisplayManager.updateFullExpressionDisplay(state, '1');
                    state.setDisplay2(state.getDisplay1());
                } else if (state.isPastIsOper()) {
                    result = n1 / 100.0;
                    state.setNum2(NumberFormatter.format(result));
                    state.setSfr2(true);
                    state.setSfr1(false);
                    DisplayManager.updateFullExpressionDisplay(state, '2');
                    state.setDisplay2(state.getNum2());
                } else if (!state.isRead1()) {
                    result = n2 / 100.0;
                    state.setNum2(NumberFormatter.format(result));
                    state.setSfr2(true);
                    state.setSfr1(false);
                    DisplayManager.updateFullExpressionDisplay(state, '2');
                    state.setDisplay2(state.getNum2());
                }
        }
        return state;
    }
}