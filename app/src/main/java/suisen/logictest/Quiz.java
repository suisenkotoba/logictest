package suisen.logictest;

/**
 * Created by suisen on 12/07/16.
 */
public class Quiz {
    private String question;
    private String[] options = new String[5];
    private int key;

    public Quiz(String question, String[] options, int key) {
        this.question = question;
        this.options = options;
        this.key = key;
    }

    public Quiz(){}

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptions(int idx) {
        return options[idx];
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public void setOption(int idx, String option) {
        this.options[idx] = option;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
