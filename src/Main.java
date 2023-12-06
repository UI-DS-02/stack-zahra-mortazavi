import calculator.Calculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner s=new Scanner(System.in);
        Calculator calculator=new Calculator();
        while (true){
           String order= s.nextLine();
           if (order.equals("new")){
               calculator.makeOperator(s.nextLine());
           }
           else {
               try {
                   calculator.makeStack(order);

                   while (!calculator.getNumOperation().isEmpty())
                       System.out.println(calculator.getNumOperation().pop().getData());
               } catch (Exception e) {
                   System.out.println(e.getMessage());
               }

           }

    }
}}

