package com.calculator3.model;

public class CalculatorRequest {
    private String input;
    private CalculatorState state;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public CalculatorState getState() {
        return state;
    }

    public void setState(CalculatorState state) {
        this.state = state;
    }
}