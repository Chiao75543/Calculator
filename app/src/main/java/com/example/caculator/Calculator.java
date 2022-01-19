package com.example.caculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Calculator extends AppCompatActivity {
    private Button btn1; //1
    private Button btn2; //2
    private Button btn3; //3
    private Button btn4; //4
    private Button btn5; //5
    private Button btn6; //6
    private Button btn7; //7
    private Button btn8; //8
    private Button btn9; //9
    private Button btn0; //0
    private Button add;  //加
    private Button sub;  //減
    private Button mul;  //乘
    private Button div;  //除
    private Button fac;  //階乘
    private Button clear;//清除
    private Button equal;//等於
    private Button dot;  //點
    private EditText res;//運算完的答案
    private String StoreStr="";//紀錄當前的算是
    public double Number = 0; //用來儲存運算的變數


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        findView();
        TextView nameGet = findViewById(R.id.NameText);
        nameGet.setText("Hello,"+getIntent().getStringExtra("MyName"));
    }
    private Button.OnClickListener Operation = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn0:
                    StoreStr = res.getText() + "0";
                    res.setText(StoreStr);
                    break;
                case R.id.btn1:
                    StoreStr = res.getText() + "1";
                    res.setText(StoreStr);
                    break;
                case R.id.btn2:
                    StoreStr = res.getText()+ "2";
                    res.setText(StoreStr);
                    break;
                case R.id.btn3:
                    StoreStr = res.getText() + "3";
                    res.setText(StoreStr);
                    break;
                case R.id.btn4:
                    StoreStr = res.getText() + "4";
                    res.setText(StoreStr);
                    break;
                case R.id.btn5:
                    StoreStr = res.getText() + "5";
                    res.setText(StoreStr);
                    break;
                case R.id.btn6:
                    StoreStr = res.getText() + "6";
                    res.setText(StoreStr);
                    break;
                case R.id.btn7:
                    StoreStr = res.getText() + "7";
                    res.setText(StoreStr);
                    break;
                case R.id.btn8:
                    StoreStr = res.getText() + "8";
                    res.setText(StoreStr);
                    break;
                case R.id.btn9:
                    StoreStr = res.getText() + "9";
                    res.setText(StoreStr);
                    break;
                case R.id.dot:
                    StoreStr = res.getText() + ".";
                    res.setText(StoreStr);
                    break;
                //運算歸0
                case R.id.clear:
                    StoreStr = "";
                    Number=0;
                    res.setText(StoreStr);
                    break;
                case R.id.div:
                    StoreStr = res.getText() + "/";
                    res.setText(StoreStr);
                    break;
                case R.id.mul:
                    StoreStr = res.getText() + "*";
                    res.setText(StoreStr);
                    break;
                case R.id.sub:
                    StoreStr = res.getText() + "-";
                    res.setText(StoreStr);
                    break;
                case R.id.add:
                    StoreStr = res.getText() + "+";
                    res.setText(StoreStr);
                    break;
                case R.id.fac:
                    StoreStr = res.getText() + "!";
                    res.setText(StoreStr);
                    break;
                case R.id.equal:
                    operate(StoreStr);
                    StoreStr = String.valueOf(Number);
                    //把多餘的.0刪除
                    if(StoreStr.indexOf('.')>0){
                        StoreStr = StoreStr.replace(".0","");
                    }
                    res.setText(StoreStr);
                    break;
            }
        }
    };
    private void findView() {
        //抓取id
        res = (EditText) findViewById(R.id.res);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
        btn5 = (Button)findViewById(R.id.btn5);
        btn6 = (Button)findViewById(R.id.btn6);
        btn7 = (Button)findViewById(R.id.btn7);
        btn8 = (Button)findViewById(R.id.btn8);
        btn9 = (Button)findViewById(R.id.btn9);
        btn0 = (Button)findViewById(R.id.btn0);
        add  = (Button)findViewById(R.id.add);
        sub = (Button)findViewById(R.id.sub);
        mul = (Button)findViewById(R.id.mul);
        div = (Button)findViewById(R.id.div);
        equal = (Button)findViewById(R.id.equal);
        clear = (Button)findViewById(R.id.clear);
        dot = (Button)findViewById(R.id.dot);
        fac = (Button)findViewById(R.id.fac);

        btn0.setOnClickListener(Operation);
        btn1.setOnClickListener(Operation);
        btn2.setOnClickListener(Operation);
        btn3.setOnClickListener(Operation);
        btn4.setOnClickListener(Operation);
        btn5.setOnClickListener(Operation);
        btn6.setOnClickListener(Operation);
        btn7.setOnClickListener(Operation);
        btn8.setOnClickListener(Operation);
        btn9.setOnClickListener(Operation);
        add.setOnClickListener(Operation);
        sub.setOnClickListener(Operation);
        mul.setOnClickListener(Operation);
        div.setOnClickListener(Operation);
        equal.setOnClickListener(Operation);
        clear.setOnClickListener(Operation);
        dot.setOnClickListener(Operation);
        fac.setOnClickListener(Operation);

    }

    private void operate(String str){
        //infix轉postfix
        String[] infix = makeArray(str);
        String[] stack = new String[infix.length];
        String[] postfix = new String[stack.length];
        String op;

        int top = 0;
        stack[top]="0";
        int k=0;
        for(int i = 0; i < infix.length; i++) {
            op = infix[i];
            if (op != null) {
                switch (op) {
                    //運算子堆疊,比較加權值,若op>top,就push,反之,就pop直到op>top
                    //pop就是存到postfix裡面
                    case "+": case "-": case "*": case "/":
                        while (priority(stack[top]) >= priority(op)) {
                            postfix[i] = stack[top];
                            top--;
                        }
                        // 運算子push堆疊
                        if (top < stack.length) {
                            top++;
                            stack[top] = op;
                        }
                        break;
                    // 運算元直接輸出
                    default:
                        postfix[i] = op;
                        break;
                }
                k = i+1;//紀錄最後的i值,用來判斷postfix接下來要存在哪
            }
        }
        //將剩下的運算子pop出來
        while(top > 0) {
            postfix[k++]=stack[top];
            top--;
        }
        calculatePostfix(postfix);
    }

    //計算postfix
    private void calculatePostfix(String[] postfix){
        double[] stack = new double[postfix.length];
        int top=0;
        String nextToken;
        for (int i=0;i<postfix.length;i++){
            nextToken=postfix[i];
            if(nextToken!=null) {
                //如果有階乘,優先權最高,必須先算
                if(nextToken.contains("!")){
                    String fac=nextToken.substring(0,nextToken.indexOf("!"));
                    int ans = CalculateFac(fac);
                    nextToken = String.valueOf(ans);
                }
                //如果遇到數字.就push,遇到運算子,pop出最上面兩個數字做運算
                switch (nextToken) {
                    case "+": case "-": case "*": case "/":
                        stack[top - 1] = calculation(stack[top], nextToken, stack[top - 1]);
                        top--;
                        break;
                    default:
                        if (top < stack.length) {
                            String temp = nextToken;
                            top++;
                            stack[top] = Double.parseDouble(temp);
                        }
                        break;
                }
            }
        }
        Number=stack[top];
    }
    //設定運算子的優先權
    private int priority(String op) {
        switch(op) {
            case "+": case "-":
                return 1;
            case "*": case "/":
                return 2;
            default:
                return 0;
        }
    }
    //計算加減乘除
    private double calculation(double p1, String op, double p2) {
        switch(op) {
            case "+":
                return p1 + p2;
            case "-":
                return p2 - p1;
            case "*":
                return p1 * p2;
            case "/":
                return p1 / p2;
        }
        return 0.0;
    }
    //計算階乘
    private int CalculateFac (String fac){
        int sum=1;
        for (int j=1;j<=Integer.parseInt(fac);j++){
            sum = sum*j;
        }
        return sum;
    }


    private String[] makeArray(String str){
        char[] arr=str.toCharArray();
        String[] result = new String[arr.length+1];
        String s="";
        char next;
        int count=0;
        for (int i =0;i<arr.length;i++){
            next = arr[i];
            switch (next){
                case '+': case '-': case '*': case '/':
                    result[count++]=s;
                    result[count++]=String.valueOf(next);
                    s="";
                    break;
                default:
                    s = s+next;
                    break;
            }
        }
        result[count++]=s;
        return result;
    }

}