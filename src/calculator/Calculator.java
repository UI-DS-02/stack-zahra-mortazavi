package calculator;

import stack.LinkedStack;

import java.util.ArrayList;

public class Calculator {

    private LinkedStack<Unit> operation;
    private LinkedStack<NumUnit> numOperation;
    private ArrayList<Operator> operator;
    private Unit[] steps = new Unit[100];
    int counter = 0;
    private boolean check = false;
    private boolean check2 = false;
    private Operator operatorName;

    public boolean isCheck2() {
        return check2;
    }

    public void setCheck2(boolean check2) {
        this.check2 = check2;
    }

    private boolean isNumber(char charAt) {
        boolean isNum = false;
        for (int i = 48; i < 58; i++) {
            if (charAt == (char) i) {
                isNum = true;
            }
        }
        return isNum;
    }
    //check operation validation
    public void check(String prime) throws Exception {
        // O(n)
        if (!isNumber(prime.charAt(0)) && prime.charAt(0) != '(' && prime.charAt(0) != '-' && prime.charAt(0) != 'c' && prime.charAt(0) != 's' && prime.charAt(0) != 'l')
            throw new Exception("error!");
        if (!isNumber(prime.charAt(prime.length() - 1)) && prime.charAt(prime.length() - 1) != ')' && prime.charAt(prime.length() - 1) != '!' && prime.charAt(prime.length() - 1) != 'e' && prime.charAt(prime.length() - 1) != 'p')
            throw new Exception("error!");
        LinkedStack<Character> parantas = new LinkedStack<Character>();
        for (int i = 0; i < prime.length(); i++) {
            if (prime.charAt(i) == '(')
                parantas.push('(');
            if (prime.charAt(i) == ')') {
                if (parantas.isEmpty())
                    throw new Exception("error!");
                parantas.pop();
            }

        }
        if (!parantas.isEmpty())
            throw new Exception("error!");
    }
 //constructor
    public Calculator() {
        this.operation = new LinkedStack<Unit>();
        this.numOperation = new LinkedStack<NumUnit>();
        this.operator = new ArrayList<Operator>(20);
    }
//make numbers stack to make postfix
    public void makeStack(String operation) throws Exception {
        setCheck(false);
        StringBuilder primeOperation = new StringBuilder(operation);
        check(new String(operation));

        for (int i = 0; i < primeOperation.length(); i++) {
//O(n)
            int endNum = 0;
            if ((i == (primeOperation.length() - 1)) && !isCheck2())
                check = true;
            if (isNumber(primeOperation.charAt(i))) {
                int startNum = i;

                i++;
                endNum = i;
                while (i != primeOperation.length() && (isNumber(primeOperation.charAt(i)) || primeOperation.charAt(i) == '.')) {
                    i++;
                    endNum = i;

                }
                i--;
                getNumOperation().push(new NumUnit(counter, Double.parseDouble(primeOperation.substring(startNum, endNum))));

                counter++;
                if ((i == (primeOperation.length() - 1)) && !isCheck2())
                    check = true;

                if (isCheck2()) {

                    editPrimeOperation(primeOperation, i + 1, getOperatorName(), String.valueOf(getNumOperation().pop().getData()), String.valueOf(getNumOperation().pop().getData()));
                    setCheck2(false);
                }
                if (isCheck())
                    calculate(new Unit(counter, "final", -1));

            } else if (primeOperation.charAt(i) == 'e') {
                getNumOperation().push(new NumUnit(counter, 2.71828));
                counter++;
                if (isCheck())
                    calculate(new Unit(counter, "final", -1));
            } else if (primeOperation.charAt(i) == 'p') {
                getNumOperation().push(new NumUnit(counter, 3.1415));
                counter++;
                if (isCheck())
                    calculate(new Unit(counter, "final", -1));
            } else if (primeOperation.charAt(i) == '-') {

                if (getNumOperation().isEmpty() || (!getOperation().isEmpty() && (getOperation().top().getData().equals("(") && getOperation().top().getIndex() + 1 == counter))) {

                    getNumOperation().push(new NumUnit(counter, -1));
                    counter++;
                    calculate(new Unit(counter, "*", 2));
                } else
                    calculate(new Unit(counter, "-", 1));
                counter++;
            } else if (primeOperation.charAt(i) == 's' && primeOperation.charAt(i + 1) == 'i' && primeOperation.charAt(i + 2) == 'n') {
                calculate(new Unit(counter, "sin", 2));
                i = i + 2;
                counter++;
            } else if (primeOperation.charAt(i) == '*') {
                calculate(new Unit(counter, "*", 2));
                counter++;
            } else if (primeOperation.charAt(i) == '+') {
                calculate(new Unit(counter, "+", 1));
                counter++;
            } else if (primeOperation.charAt(i) == '-') {
                calculate(new Unit(counter, "-", 1));
                counter++;
            } else if (primeOperation.charAt(i) == '/') {
                calculate(new Unit(counter, "/", 2));
                counter++;
            } else if (primeOperation.charAt(i) == '(') {
                calculate(new Unit(counter, "(", 5));
                counter++;
            } else if (primeOperation.charAt(i) == ')') {
                calculate(new Unit(counter, ")", 5));
                counter++;


            } else if (primeOperation.charAt(i) == '^') {
                calculate(new Unit(counter, "^", 3));
                counter++;
            } else if (primeOperation.charAt(i) == '!') {
                calculate(new Unit(counter, "!", 4));
                counter++;
                if (isCheck())
                    calculate(new Unit(counter, "final", -1));

            } else if (primeOperation.charAt(i) == 'c' && primeOperation.charAt(i + 1) == 'o' && primeOperation.charAt(i + 2) == 's') {
                calculate(new Unit(counter, "cos", 2));
                i = i + 2;
                counter++;
            } else if (primeOperation.charAt(i) == 'l' && primeOperation.charAt(i + 1) == 'o' && primeOperation.charAt(i + 2) == 'g') {
                calculate(new Unit(counter, "log", 2));
                i = i + 2;
                counter++;
            } else if (primeOperation.charAt(i) == 's' && primeOperation.charAt(i + 1) == 'q' && primeOperation.charAt(i + 2) == 'r' && primeOperation.charAt(i + 3) == 't') {
                calculate(new Unit(counter, "sqrt", 3));
                i = i + 3;
                counter++;

            } else if (primeOperation.charAt(i) == ' ') {
                StringBuilder name = new StringBuilder();
                i++;
                while (primeOperation.charAt(i) != ' ') {
                    name.append(primeOperation.charAt(i));
                    i++;
                }


                for (int j = 0; j < getOperator().size(); j++) {

                    if (getOperator().get(j).getName().equals(name.toString())) {
                        setCheck2(true);
                        setOperatorName(getOperator().get(j));
                    }
                }
                if (!isCheck2())
                    throw new Exception("error!");
                counter++;
            } else {
                throw new Exception("error!");
            }


        }
    }


//calculate operation by postfix
    private void calculate(Unit newOperator) throws Exception {
        //O(n/2)=O(n)
        if (getOperation().isEmpty()) {

            getOperation().push(newOperator);
        } else if (newOperator.getPriority() >= getOperation().top().getPriority() && !newOperator.getData().equals(")")) {
            getOperation().push(newOperator);
        } else if (newOperator.getData().equals(")")) {
            while (!getOperation().top().getData().equals("(")) {
                calculateOperation(getOperation().pop());
            }
            Unit operator = getOperation().pop();
            if (isCheck())
                calculate(new Unit(counter, "final", -1));
        } else {

            while (!getOperation().isEmpty() && !getOperation().top().getData().equals("(")) {
                calculateOperation(getOperation().pop());

            }

            if (!newOperator.getData().equals("final")) {
                getOperation().push(newOperator);
            }
        }
    }
//call operator functions
    private void calculateOperation(Unit operator) throws Exception {
        //O(1)
        if (operator.getData().equals("*")) {
            if (getNumOperation().size() < 2)
                throw new Exception("error!");
            multiplication(getNumOperation().pop(), getNumOperation().pop());
        } else if (operator.getData().equals("+")) {
            if (getNumOperation().size() < 2)
                throw new Exception("error!");
            sum(getNumOperation().pop(), getNumOperation().pop());
        } else if (operator.getData().equals("-")) {
            if (getNumOperation().size() < 2)
                throw new Exception("error!");
            subtraction(getNumOperation().pop(), getNumOperation().pop());
        } else if (operator.getData().equals("/")) {
            if (getNumOperation().size() < 2)
                throw new Exception("error!");
            division(getNumOperation().pop(), getNumOperation().pop());
        } else if (operator.getData().equals("sin"))
            sin(getNumOperation().pop());
        else if (operator.getData().equals("cos"))
            cos(getNumOperation().pop());
        else if (operator.getData().equals("log"))
            logaritm(getNumOperation().pop());
        else if (operator.getData().equals("sqrt"))
            sqrt(getNumOperation().pop());
        else if (operator.getData().equals("!"))
            factoiel(getNumOperation().pop());
        else if (operator.getData().equals("^")) {
            if (getNumOperation().size() < 2)
                throw new Exception("error!");
            power(getNumOperation().pop(), getNumOperation().pop());

        }
    }
//operator functions
    private void multiplication(NumUnit num1, NumUnit num2) throws Exception {
        //O(1)
        numOperation.push(new NumUnit(num1.getIndex(), num1.getData() * num2.getData()));
    }

    private void sum(NumUnit num1, NumUnit num2) throws Exception {
        //O(1)
        numOperation.push(new NumUnit(num1.getIndex(), num1.getData() + num2.getData()));
    }

    private void subtraction(NumUnit num1, NumUnit num2) throws Exception {
        //O(1)
        numOperation.push(new NumUnit(num1.getIndex(), num2.getData() - num1.getData()));
    }

    private void division(NumUnit num1, NumUnit num2) throws Exception {
        //O(1)
        if (num1.getData() == 0)
            throw new Exception("error!");
        numOperation.push(new NumUnit(num2.getIndex(), num2.getData() / num1.getData()));
    }

    private void sqrt(NumUnit num1) throws Exception {
        //O(1)
        if (num1.getData() < 0)
            throw new Exception("error!");
        numOperation.push(new NumUnit(num1.getIndex(), Math.sqrt(num1.getData())));
    }

    private void power(NumUnit num1, NumUnit num2) throws Exception {
        //O(1)
        numOperation.push(new NumUnit(num2.getIndex(), Math.pow(num2.getData(), num1.getData())));
    }

    private void sin(NumUnit num1) throws Exception {
        //O(1)
        numOperation.push(new NumUnit(num1.getIndex(), Math.sin(num1.getData() * Math.PI / 180)));
    }

    private void cos(NumUnit num1) throws Exception {
        //O(1)
        numOperation.push(new NumUnit(num1.getIndex(), Math.cos(num1.getData() * Math.PI / 180)));
    }

    private void logaritm(NumUnit num1) throws Exception {
        //O(1)
        numOperation.push(new NumUnit(num1.getIndex(), Math.log(num1.getData())));
    }

    private void factoiel(NumUnit num1) throws Exception {
        //O(n)  n=num1
        int num = 1;
        for (int i = 1; i <= num1.getData(); i++) {
            num *= i;
        }

        numOperation.push(new NumUnit(num1.getIndex(), num));
    }

   ////////////////////////////////////////////////////////////////////////////////////////////////////////
   //make new operator
   public void makeOperator(String newOperator) {
//O(1)
       String[] operatorArray = newOperator.split(" ");
       getOperator().add(new Operator(operatorArray[1], operatorArray[operatorArray.length - 1]));
   }

    //add new operator to operation
   public void editPrimeOperation(StringBuilder prime, int index, Operator operator, String numB, String numA) {
       //O(n) n=code.lengh max=prime-4
       StringBuilder code = new StringBuilder(operator.getCode());
       for (int i = 0; i < code.length(); i++) {

           if (code.charAt(i) == 'a')
               code.replace(i, i + 1, numA);
           if (code.charAt(i) == 'b')
               code.replace(i, i + 1, numB);
       }
       prime.insert(index, code);
   }
   ///////////////////////////////////////////
    //getter and setter
    public ArrayList<Operator> getOperator() {
        return operator;
    }

    public void setOperator(ArrayList<Operator> operator) {
        this.operator = operator;
    }

    public LinkedStack<Unit> getOperation() {
        return operation;
    }

    public void setOperation(LinkedStack<Unit> operation) {
        this.operation = operation;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public LinkedStack<NumUnit> getNumOperation() {
        return numOperation;
    }

    public void setNumOperation(LinkedStack<NumUnit> numOperation) {
        this.numOperation = numOperation;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
    public Operator getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(Operator operatorName) {
        this.operatorName = operatorName;
    }

}
