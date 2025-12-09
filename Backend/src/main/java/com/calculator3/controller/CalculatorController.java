package com.calculator3.controller;

import com.calculator3.Exception.CalculatorException;
import com.calculator3.model.CalculatorState;
import com.calculator3.service.CalculatorService;
import com.calculator3.model.CalculatorResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")


@RestController
@RequestMapping("/api")
public class CalculatorController {

    private static final String CALCULATOR_STATE = "calculatorState";

    @Autowired
    private CalculatorService calculatorService;

    @PostMapping("/calculate")
    public CalculatorResponse calculate(@RequestParam String input, HttpSession session) {
        try {
            CalculatorState currentState = (CalculatorState) session.getAttribute(CALCULATOR_STATE);
            if (currentState == null) {
                currentState = new CalculatorState();
            }

            if ("c".equals(input)) {
                currentState = new CalculatorState();
            } else {
                currentState = calculatorService.processInput(input, currentState);
            }

            session.setAttribute(CALCULATOR_STATE, currentState);

            return new CalculatorResponse(
                    currentState,
                    currentState.getDisplay1(),
                    currentState.getDisplay2(),
                    false
            );

        } catch (CalculatorException e) {
            CalculatorState errorState = new CalculatorState();
            session.removeAttribute(CALCULATOR_STATE);


            return new CalculatorResponse(errorState, "", "E", true);
        } catch (Exception e) {
            CalculatorState errorState = new CalculatorState();
            session.removeAttribute(CALCULATOR_STATE);

            return new CalculatorResponse(
                    errorState,
                    "",
                    "E",
                    true
            );
        }

    }@PostMapping("/reset")
    public CalculatorResponse reset(HttpSession session) {
        CalculatorState state = new CalculatorState();
        session.setAttribute(CALCULATOR_STATE, state);
        return new CalculatorResponse(
                state,
                state.getDisplay1(),
                state.getDisplay2(),
                false
        );
    }

    @GetMapping("/state")
    public CalculatorResponse getState(HttpSession session) {
        CalculatorState state = (CalculatorState) session.getAttribute(CALCULATOR_STATE);
        if (state == null) {
            state = new CalculatorState();
            session.setAttribute(CALCULATOR_STATE, state);
        }
        return new CalculatorResponse(
                state,
                state.getDisplay1(),
                state.getDisplay2(),
                false
        );
    }}