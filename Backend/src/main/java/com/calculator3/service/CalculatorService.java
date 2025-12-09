package com.calculator3.service;

import com.calculator3.model.CalculatorState;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    private final InputProcessor inputProcessor = new InputProcessor();

    public CalculatorState processInput(String input, CalculatorState state) {
        if (state == null) {
            state = new CalculatorState();
            state.setDisplay2("0");
            state.setDisplay1("");
        }
        return inputProcessor.process(input.charAt(0), state);
    }

    public String formatNumber(double value) {
        return NumberFormatter.format(value);
    }
}