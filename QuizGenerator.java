package techplement;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Map;

class QuizGenerator{
    static Scanner sc = new Scanner(System.in);
    public static void main(String args[]){
        QuizService quizService = new QuizService();
        System.out.println("Welcome to Techplement");

        quizService.sampleQuiz(quizService.getHmap());
        outer:
        while(true){
            System.out.println("1: Create a new Quiz");
            System.out.println("2: Play a Quiz");
            System.out.println("3: Modify Existing Quiz");
            System.out.println("4: Exit");
            System.out.print("Enter your choice: ");
            int choice = 0;
            try{
                choice = sc.nextInt();
            }
            catch(InputMismatchException ex){
                System.out.println("Please Enter Correct input");
            }
            sc.nextLine();
            System.out.println();
            switch(choice){
                case 1: 
                quizService.createQuiz();
                    break;
                case 2: 
                    quizService.takeQuiz();
                    break;
                case 3: 
                    if(quizService.getHmap().size() == 0){
                        System.out.println("No Quiz are Available");
                        continue;
                    }
                    System.out.println("Quizzes Available: ");
                    for(Map.Entry<String, ArrayList<Question>> map : quizService.getHmap().entrySet()){
                        System.out.println(map.getKey());
                    }
                    System.out.print("Select any one: ");
                    String quiz = sc.nextLine();
                    if(!quizService.getHmap().containsKey(quiz)){
                        System.out.println("This quiz is not present");
                        continue;
                    }
                    quizService.modifyQuiz(quiz);
                    break;
                case 4:
                    System.out.println("Thank you for using our service");
                    break outer;
                default:
                    break outer;
            }
        }
    }
}