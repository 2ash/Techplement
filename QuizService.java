package techplement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

/**
 * QuizService
 */

public class QuizService implements QuizGeneratorInterface{
    Scanner sc = new Scanner(System.in);
    private ArrayList<Question> questions = new ArrayList<>();
    private HashMap<String, ArrayList<Question>> hmap = new HashMap<>();

    public HashMap<String, ArrayList<Question>> getHmap() {
        return hmap;
    }

    public void setQuiz(HashMap<String, ArrayList<Question>> hmap) {
        this.hmap = hmap;
    }

    @Override
    public void createQuiz(){
        System.out.print("Enter name of the Quiz: ");
        String name_of_the_quiz = sc.nextLine();
        addQuestion(name_of_the_quiz);   
    }

    @Override
    public void takeQuiz(){
        if(hmap.size() == 0){
            System.out.println("No Quizzes are Available");
            return;
        }
        System.out.println("Available Quizzes: ");
        for(Map.Entry<String, ArrayList<Question>> map : hmap.entrySet()){
            System.out.println(map.getKey());
        }
        System.out.print("Select any one quiz: ");
        String quiz = sc.nextLine();
        if(!hmap.containsKey(quiz)){
            System.out.println("This Quiz is not Available");
            return;
        }
        int result = 0;
        int ques_no = 1;
        for(Question que : hmap.get(quiz)){
            System.out.println(ques_no + ". " + que.getQues());
            for(int j = 0; j < que.getOptions().size(); j++){
                System.out.println((char)('a' + j) + ". " + que.getOptions().get(j));
            }
            System.out.print("Choose the Answer: ");
            char ans = sc.nextLine().charAt(0);
            if(ans < 'a' || ans > (char)('a' + que.getOptions().size() - 1)){
                System.out.println("Please choose valid option");
                ans = sc.nextLine().charAt(0);
            }
            if(ans == que.getAnswer()){
                result++;
            }
            ques_no++;
        }
        System.out.println();  
        System.out.println("Your Score :" + result + "/" + hmap.get(quiz).size());
        System.out.println();
    }

    @Override
    public void addQuestion(String quiz){
        System.out.print("How many questions do you want to add? ");
        int no_of_ques = 0;
        try{
            no_of_ques = sc.nextInt();
        }
        catch(InputMismatchException ex){
            System.out.println("Enter numeric value");
        }
        sc.nextLine();

        outer:
        for(int i = 1; i <= no_of_ques; i++){
            System.out.print("Question " + i + ": ");
            String ques = sc.nextLine();

            System.out.print("Number of options: ");
            int no_of_options = 0;
            try{
                no_of_options = sc.nextInt();
            }
            catch(InputMismatchException ex){
                System.out.println("Number of option lies between 2 to 4");
            }
            int counter = 0;
            while(counter < 2 && (no_of_options < 2 || no_of_options > 4)){
                System.out.println("Number of option lies between 2 to 4");
                no_of_options = sc.nextInt();
                counter++;
                if(counter == 2){
                    return;
                }
            }
            sc.nextLine();

            ArrayList<String> options = new ArrayList<>();
            for(int j = 0; j < no_of_options; j++){
                System.out.print((char)('a' + j) + ". ");
                String option = sc.nextLine();
                options.add(option);
            }

            System.out.print("ans: ");
            char ans = sc.nextLine().charAt(0);

            int count = 1;
            while(ans < 'a' || ans > (char)('a' + (no_of_options - 1))){
                System.out.println("Please Enter correct option");
                ans = sc.nextLine().charAt(0);
                count++;
                if(count >= 3){
                    break outer;
                }
            }
            if(hmap.containsKey(quiz)){
                hmap.get(quiz).add(new Question(ques, new ArrayList<>(options), ans));
            }
            else{
                questions.add(new Question(ques, new ArrayList<>(options), ans));
                hmap.put(quiz, questions);
            }
        }
        System.out.println();
        // hmap.put(key, hmap.getOrDefault(key, questions));
    }

    @Override
    public void deleteQuestion(String quiz){
        if(!hmap.containsKey(quiz)){
            System.out.println("This quiz is not present");
            return;
        }
        int ques_no = 1;
        for(Question que : hmap.get(quiz)){
            System.out.println(ques_no + ". " + que.getQues());
            ques_no++;
        }
        System.out.print("Enter question number do you want to delete. ");
        ques_no = sc.nextInt();
        if(ques_no <= 0 && ques_no > hmap.get(quiz).size()){
            System.out.println("Question no. " + ques_no + "not_present in the quiz");
            return;
        }
        hmap.get(quiz).remove(ques_no - 1);
        if(hmap.get(quiz).size() == 0){
            hmap.remove(quiz);
        }
        System.out.println("Delete Successfully");
        System.out.println();
    }

    @Override
    public void updateQuiz(String quiz){
        showQuiz(quiz);
        System.out.print("Which question do you want to update? ");
        int ques_no = sc.nextInt();
        sc.nextLine();

        if(!(ques_no > 0 && ques_no <= hmap.get(quiz).size())){
            System.out.println("Question no. " + ques_no + " is not present");
            return;
        }
        Question question = hmap.get(quiz).get(ques_no - 1);
        System.out.println(ques_no + ". " + question.getQues() + " update yes/no");
        String update = sc.nextLine();
        if(update.equals("yes")){
            String newQuestion = sc.nextLine();
            question.setQues(newQuestion);
        }
        for(int i = 0; i < question.getOptions().size(); i++){
            System.out.println((char)('a' + i) + ". " + question.getOptions().get(i) + " update yes/no");
            update = sc.nextLine();
            if(update.equals("yes")){
                String newOption = sc.nextLine();
                question.getOptions().set(i, newOption);
            }
        }
        System.out.println("Answer. " + question.getAnswer() + " update yes/no");
        update = sc.nextLine();
        if(update.equals("yes")){
            char newAnswer = sc.nextLine().charAt(0);
            int count = 1;
            while(newAnswer < 'a' || newAnswer > question.getOptions().size() - 1){
                System.out.println("Please enter correct option");
                newAnswer = sc.nextLine().charAt(0);
                if(count > 3){
                    return;
                }
                count++;
            }
            question.setAnswer(newAnswer);
        }
        System.out.println();
    }

    @Override
    public void showQuiz(String quiz){
        int ques_no = 1;
        for(Question ele : hmap.get(quiz)){
            System.out.println(ques_no + ". " + ele.getQues());
            for(int i = 0; i < ele.getOptions().size(); i++){
                System.out.println((char)('a' + i) + ". " + ele.getOptions().get(i));
            }
            ques_no++;
            System.out.println("Answer is option (" + ele.getAnswer() + ")");
            System.out.println();
        }
    }
    
    @Override
    public void modifyQuiz(String quiz){
        System.out.println("What type of modification do you want? add/delete/update");
        String modification = sc.nextLine();

        switch(modification){
            case "add":
                addQuestion(quiz); 
                break;
            case "delete":
                deleteQuestion(quiz);
                break;
            case "update":
                updateQuiz(quiz);
                break;
            default:
                break;
        }
        System.out.println();
    }
}