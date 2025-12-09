package com.calculator3.service;

import com.calculator3.model.CalculatorState;

public class InputProcessor {

    public CalculatorState process(char input, CalculatorState state) {
        switch (input) {
            case 'c': return handleClear();
            case '+': case '-': case '*': case '/': return OperatorHandler.handle(input, state);
            case 'o': return UnaryOperationHandler.handleReciprocal(state);
            case 's': return UnaryOperationHandler.handleSquare(state);
            case 'r': return UnaryOperationHandler.handleSquareRoot(state);
            case 'n': return UnaryOperationHandler.handleNegate(state);
            case '=': return EqualsHandler.handle(state);
            case 'p': return PercentageHandler.handle(state);
            case 'b': return BackspaceHandler.handle(state);
            default: return handleDigitOrDecimal(input, state);
        }
    }

    private CalculatorState handleClear() {
        return new CalculatorState();
    }

    private CalculatorState handleDigitOrDecimal(char input, CalculatorState state) {
        if ((input >= '0' && input <= '9') || input == '.') {
            return DigitHandler.process(input, state);
        }
        return state;
    }
}