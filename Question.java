package techplement;

import java.util.ArrayList;
class Question{
    private String ques;
    private ArrayList<String> options;
    private char answer;

    public Question(){
    }

    public Question(String ques, ArrayList<String> options, char answer) {
        this.ques = ques;
        this.options = options;
        this.answer = answer;
    }
    public String getQues() {
        return ques;
    }
    public void setQues(String ques) {
        this.ques = ques;
    }
    
    public char getAnswer() {
        return answer;
    }
    public void setAnswer(char answer) {
        this.answer = answer;
    }
    public ArrayList<String> getOptions() {
        return options;
    }
    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }
}