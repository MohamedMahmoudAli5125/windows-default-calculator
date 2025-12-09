package com.calculator3.model;

public class CalculatorResponse {
     private CalculatorState state;
        private String display1;
        private String display2;
        private boolean error;

        public CalculatorResponse(CalculatorState state,String display1, String display2, boolean error) {
            this.state = state;
            this.display1 = display1;
            this.display2 = display2;
            this.error = error;
        }

    // Getters and Setters
    public CalculatorState getState() { return state; }
    public void setState(CalculatorState state) { this.state = state; }

    public boolean isError() { return error; }
    public void setError(boolean error) { this.error = error; }
    public String getDisplay1() { return display1; }
    public void setDisplay1(String display1) { this.display1 = display1; }

    public String getDisplay2() { return display2; }
    public void setDisplay2(String display2) { this.display2 = display2; }

}