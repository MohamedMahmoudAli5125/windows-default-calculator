package com.calculator3.model;

public class CalculatorState {
    private String num1 = "0";
    private String num2 = "0";
    private char operator = '\0';
    private boolean read1 = true;
    private boolean pastIsEqual = false;
    private boolean pastIsOper = false;
    private boolean sfr1 = false;
    private boolean sfr2 = false;
    private String display1 = "";
    private String display2 = "0";

    // Getters and Setters

    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    public char getOperator() {
        return operator;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    public boolean isRead1() {
        return read1;
    }

    public void setRead1(boolean read1) {
        this.read1 = read1;
    }

    public boolean isPastIsEqual() {
        return pastIsEqual;
    }

    public void setPastIsEqual(boolean pastIsEqual) {
        this.pastIsEqual = pastIsEqual;
    }

    public boolean isPastIsOper() {
        return pastIsOper;
    }

    public void setPastIsOper(boolean pastIsOper) {
        this.pastIsOper = pastIsOper;
    }

    public boolean isSfr1() {
        return sfr1;
    }

    public void setSfr1(boolean sfr1) {
        this.sfr1 = sfr1;
    }

    public boolean isSfr2() {
        return sfr2;
    }

    public void setSfr2(boolean sfr2) {
        this.sfr2 = sfr2;
    }

    public String getDisplay1() {
        return display1;
    }

    public void setDisplay1(String display1) {
        this.display1 = display1;
    }

    public String getDisplay2() {
        return display2;
    }

    public void setDisplay2(String display2) {
        this.display2 = display2;
    }
}
