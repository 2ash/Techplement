package techplement;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;

interface QuizGeneratorInterface{
    //Create a new Quiz
    public void createQuiz();

    //Play quiz
    public void takeQuiz();

    //If any User want to modify the quiz
    public void modifyQuiz(String modification);

    //Add question in an exixting Quiz
    public void addQuestion(String key);

    //Delete Question in an existing Quiz
    public void deleteQuestion(String quiz);

    //Update any question, option and answer
    public void updateQuiz(String quiz);

    //Show the quiz with answer
    public void showQuiz(String quiz);

    //It is a sample quiz
    default void sampleQuiz(HashMap<String, ArrayList<Question>> hmap){
        Question question = new Question();
        question.setQues("What is a correct syntax to output \"Hello World\" in Java?");
        question.setOptions(new ArrayList<>(Arrays.asList(
            "print(\"Hello World);",
            "Console.WriteLine(\"Hello World);",
            "echo(\"Hello World)",
            "System.out.println(\"Hello World)"
        )));
        question.setAnswer('d');
        hmap.put("Java", new ArrayList<Question>(Arrays.asList(question)));
        
        question = new Question();
        question.setQues("Which data type is used to create a variable that should store text?");
        question.setOptions(new ArrayList<>(Arrays.asList(
            "String", "Text", "string", "myString")));
            question.setAnswer('a');
        hmap.get("Java").add(question);
    }
}
