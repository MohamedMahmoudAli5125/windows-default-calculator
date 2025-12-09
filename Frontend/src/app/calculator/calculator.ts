import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';

interface CalculatorState {
  num1: string;
  num2: string;
  operator: string;
  read1: boolean;
  pastIsEqual: boolean;
  pastIsOper: boolean;
  sfr2: boolean;
  sfr1: boolean;
  display1: string;
  display2: string;

}

interface CalculatorResponse {
  state: CalculatorState;
  display1: string;
  display2: string;
  error: boolean;
}

@Component({
  selector: 'app-calculator',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './calculator.html',
  styleUrls: ['./calculator.css']
})
export class Calculator {
  display2: string = '0';
  display1: string = '';
  buttons: string[] = [
    '%', 'CE', 'C', '⌫',      // Row 1
    '¹⁄ₓ', 'x²', '√', '÷',    // Row 2
    '7', '8', '9', '×',       // Row 3
    '4', '5', '6', '-',       // Row 4
    '1', '2', '3', '+',       // Row 5
    '+/-', '0', '.', '='      // Row 6
  ];
  errorState: boolean = false;

  // Map frontend symbols to backend input characters
  private inputMap: { [key: string]: string } = {
    '÷': '/',
    '×': '*',
    '¹⁄ₓ': 'o',
    'x²': 's',
    '√': 'r',
    '+/-': 'n',
    '=': '=',
    'C': 'c',
    'CE': 'c',
    '%': 'p',
    '.': '.',
    '⌫': 'b'
  };

  constructor(private http: HttpClient) { }

  onButtonClick(button: string) {
    // Map the button to backend input
    const input = this.inputMap[button] || button;
if (this.errorState && !(input === 'c')&& !(input === '0')&& !(input === '1')&& !(input === '2')&& !(input === '3')&& !(input === '4')&& !(input === '5')&& !(input === '6')&& !(input === '7')&& !(input === '8')&& !(input === '9')&& !(input === 'b')&& !(input === '=')&& !(input === '⌫')) {
    return;
  }
    this.sendToBackend(input);
  }
  private sendToBackend(input: string) {
  this.http.post<CalculatorResponse>(
    `http://localhost:8080/api/calculate?input=${encodeURIComponent(input)}`,
    {},
    { withCredentials: true }
  ).subscribe({
    next: (response) => {
      this.display1 = response.display1 || '';
      this.display2 = response.display2 || '0';

   //   Check backend error flag
      if (response.error) {
        this.errorState = true;  
      } else {
        this.errorState = false;
      }
    },
    error: (error) => {
      console.error('Calculator backend error:', error);
      this.display1 = '';
      this.display2 = 'E';
      this.errorState = true;
    }
  });
}


private handleBackspace() {
  if (this.errorState) return; 
  this.sendToBackend('c');
}


  getButtonClass(button: string): string {
    const classes = [];

    if (['+', '-', '×', '÷', '='].includes(button)) {
      classes.push('operator');
    } else if (['%', '¹⁄ₓ', 'x²', '√', '+/-'].includes(button)) {
      classes.push('function');
    } else if (['C', 'CE', '⌫'].includes(button)) {
      classes.push('clear');
    }

    return classes.join(' ');
  }

  getButtonLabel(button: string): string {
    const labels: { [key: string]: string } = {
      '¹⁄ₓ': 'Reciprocal',
      'x²': 'Square',
      '√': 'Square Root',
      '+/-': 'Plus/Minus',
      '⌫': 'Backspace',
      '×': 'Multiply',
      '÷': 'Divide'
    };
    return labels[button] || button;
  }
}