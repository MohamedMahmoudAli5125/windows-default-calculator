package com.calculator3.service;

import com.calculator3.model.CalculatorState;
import com.calculator3.Exception.CalculatorException;

public class UnaryOperationHandler {

    public static CalculatorState handleReciprocal(CalculatorState state) {
        String valueBeforeOper;
        if (state.isRead1() || state.isPastIsEqual()) {
            valueBeforeOper = state.getNum1();
            double val = Double.parseDouble(valueBeforeOper);
            if (val == 0) {
                throw new CalculatorException("Division by zero");
            } else {
                val = 1 / val;
                state.setNum1(String.valueOf(val));
                state.setDisplay2(NumberFormatter.format(val));
                state.setDisplay1("1/(" + NumberFormatter.format(Double.parseDouble(valueBeforeOper)) + ")");
            }
            state.setSfr1(true);
        } else {
            valueBeforeOper = state.getNum2();
            double val = Double.parseDouble(valueBeforeOper);
            if (val == 0) {
                throw new CalculatorException("Division by zero");
            } else {
                val = 1 / val;
                state.setNum2(String.valueOf(val));
                state.setDisplay2(NumberFormatter.format(val));
                state.setDisplay1(NumberFormatter.format(Double.parseDouble(state.getNum1())) + " " +
                        state.getOperator() + " 1/(" + NumberFormatter.format(Double.parseDouble(valueBeforeOper)) + ")");
            }
            state.setSfr2(true);
        }
        return state;
    }

    public static CalculatorState handleSquare(CalculatorState state) {
        String valueBeforeOper;
        if (state.isRead1() || state.isPastIsEqual()) {
            valueBeforeOper = state.getNum1();
            double val = Double.parseDouble(valueBeforeOper);
            val = val * val;
            state.setNum1(String.valueOf(val));
            state.setDisplay2(NumberFormatter.format(val));
            state.setDisplay1("sqr(" + NumberFormatter.format(Double.parseDouble(valueBeforeOper)) + ")");
            state.setSfr1(true);
        } else {
            valueBeforeOper = state.getNum2();
            double val = Double.parseDouble(valueBeforeOper);
            val = val * val;
            state.setNum2(String.valueOf(val));
            state.setDisplay2(NumberFormatter.format(val));
            state.setDisplay1(NumberFormatter.format(Double.parseDouble(state.getNum1())) + " " +
                    state.getOperator() + " sqr(" + NumberFormatter.format(Double.parseDouble(valueBeforeOper)) + ")");
            state.setSfr2(true);
        }
        return state;
    }

    public static CalculatorState handleSquareRoot(CalculatorState state) {
        String valueBeforeOper;
        if (state.isRead1() || state.isPastIsEqual()) {
            valueBeforeOper = state.getNum1();
            double val = Double.parseDouble(valueBeforeOper);
            if (val < 0) {
                throw new CalculatorException("Square root of negative number");
            } else {
                val = Math.sqrt(val);
                state.setNum1(String.valueOf(val));
                state.setDisplay2(NumberFormatter.format(val));
                state.setDisplay1("√(" + NumberFormatter.format(Double.parseDouble(valueBeforeOper)) + ")");
            }
            state.setSfr1(true);
        } else {
            valueBeforeOper = state.getNum2();
            double val = Double.parseDouble(valueBeforeOper);
            if (val < 0) {
                throw new CalculatorException("Square root of negative number");
            } else {
                val = Math.sqrt(val);
                state.setNum2(String.valueOf(val));
                state.setDisplay2(NumberFormatter.format(val));
                state.setDisplay1(NumberFormatter.format(Double.parseDouble(state.getNum1())) + " " +
                        state.getOperator() + " √(" + NumberFormatter.format(Double.parseDouble(valueBeforeOper)) + ")");
            }
            state.setSfr2(true);
        }
        return state;
    }

    public static CalculatorState handleNegate(CalculatorState state) {
        if (state.isRead1() || state.isPastIsEqual()) {
            double val = Double.parseDouble(state.getNum1());
            val = -1 * val;
            state.setNum1(String.valueOf(val));
            state.setDisplay2(NumberFormatter.format(val));
            state.setSfr1(true);
        } else {
            double val = Double.parseDouble(state.getNum2());
            val = -1 * val;
            state.setNum2(String.valueOf(val));
            state.setDisplay2(NumberFormatter.format(val));
            DisplayManager.updateOperatorDisplay(state);
            state.setSfr2(true);
        }
        return state;
    }
}