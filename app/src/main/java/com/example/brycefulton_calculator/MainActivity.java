package com.example.brycefulton_calculator;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Declare UI elements
    private EditText editText_Num1, editText_Num2;
    private TextView textView_Result;
    private ImageButton button_Add, button_Subtract, button_Multiply, button_Divide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize UI elements link to XML
        editText_Num1 = findViewById(R.id.editText_Num1);
        editText_Num2 = findViewById(R.id.editText_Num2);
        textView_Result = findViewById(R.id.textView_Result);
        button_Add = findViewById(R.id.button_Add);
        button_Subtract = findViewById(R.id.button_Subtract);
        button_Multiply = findViewById(R.id.button_Multiply);
        button_Divide = findViewById(R.id.button_Divide);

        //OnClickListener for the operations
        button_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performOperation("add");
            }
        });
        button_Subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performOperation("subtract");
            }
        });
        button_Multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performOperation("multiply");
            }
        });
        button_Divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performOperation("divide");
            }
        });
    }
    // Method to handle button clicks from the ImageButtons
    public void onOperatorClick(View view) {
        String operation = view.getTag().toString();
        performOperation(operation);
    }

    //Performing the selected operation
    private void performOperation(String operation) {
        String num1String = editText_Num1.getText().toString();
        String num2String = editText_Num2.getText().toString();

        // Input validation: Check if both input fields are not empty
        if (num1String.isEmpty() || num2String.isEmpty()) {
            Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
            Log.w("CalculatorApp", "Attempted operation with empty input");
            return;
        }

        try {
            // Parse input strings to double values
            double num1 = Double.parseDouble(num1String);
            double num2 = Double.parseDouble(num2String);
            double result = 0;

            // Perform the selected operation using a switch statement
            switch (operation) {
                case "add":
                    result = num1 + num2;
                    Log.d("CalculatorApp", "Addition: " + num1 + " + " + num2 + " = " + result);
                    break;
                case "subtract":
                    result = num1 - num2;
                    Log.d("CalculatorApp", "Subtraction: " + num1 + " - " + num2 + " = " + result);
                    break;
                case "multiply":
                    result = num1 * num2;
                    Log.d("CalculatorApp", "Multiplication: " + num1 + " * " + num2 + " = " + result);
                    break;
                case "divide":
                    if (num2 == 0) {
                        // Handle division by zero
                        Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                        Log.e("CalculatorApp", "Division by zero attempted");
                        return;
                    }
                    result = num1 / num2;
                    Log.d("CalculatorApp", "Division: " + num1 + " / " + num2 + " = " + result);
                    break;
                default:
                    Toast.makeText(this, "Unknown operation", Toast.LENGTH_SHORT).show();
                    Log.e("CalculatorApp", "Unknown operation: " + operation);
                    return;
            }

            //Update the result TextView with the computed results
            textView_Result.setText("Result: " + result);

        } catch (NumberFormatException e) {
            // Handle invalid number format exception
            Toast.makeText(this, "Invalid input. Please enter valid numbers.", Toast.LENGTH_SHORT).show();
            Log.e("CalculatorApp", "NumberFormatException: " + e.getMessage());
        }
    }
}