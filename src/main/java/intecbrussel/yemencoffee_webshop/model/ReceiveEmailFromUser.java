package intecbrussel.yemencoffee_webshop.model;

public class ReceiveEmailFromUser {

    private final String emailToReceiveUserQuestion="javagawah@hotmail.com";
    private String usernameWhoHasQuestion;
    private String subjectWhoHasQuestion;
    private String emailWhoHasQuestion;
    private String messageWhoHasQuestion;


    private SendEmailInfo sendEmailInfo;

    public ReceiveEmailFromUser() {
    }

    public ReceiveEmailFromUser(String usernameWhoHasQuestion, String subjectWhoHasQuestion, String emailWhoHasQuestion, String messageWhoHasQuestion) {
        this.usernameWhoHasQuestion = usernameWhoHasQuestion;
        this.subjectWhoHasQuestion = subjectWhoHasQuestion;
        this.emailWhoHasQuestion = emailWhoHasQuestion;
        this.messageWhoHasQuestion = messageWhoHasQuestion;
    }

    public String getEmailToReceiveUserQuestion() {
        return emailToReceiveUserQuestion;
    }

    public String getUsernameWhoHasQuestion() {
        return usernameWhoHasQuestion;
    }

    public void setUsernameWhoHasQuestion(String usernameWhoHasQuestion) {
        this.usernameWhoHasQuestion = usernameWhoHasQuestion;
    }

    public String getSubjectWhoHasQuestion() {
        return subjectWhoHasQuestion;
    }

    public void setSubjectWhoHasQuestion(String subjectWhoHasQuestion) {
        this.subjectWhoHasQuestion = subjectWhoHasQuestion;
    }

    public String getEmailWhoHasQuestion() {
        return emailWhoHasQuestion;
    }

    public void setEmailWhoHasQuestion(String emailWhoHasQuestion) {
        this.emailWhoHasQuestion = emailWhoHasQuestion;
    }

    public String getMessageWhoHasQuestion() {
        return messageWhoHasQuestion;
    }

    public void setMessageWhoHasQuestion(String messageWhoHasQuestion) {
        this.messageWhoHasQuestion = messageWhoHasQuestion;
    }


    public SendEmailInfo getSendEmailInfo() {
        return sendEmailInfo;
    }

    public void setSendEmailInfo(SendEmailInfo sendEmailInfo) {
        this.sendEmailInfo = sendEmailInfo;
    }

    @Override
    public String toString() {
        return "ReceiveEmailFromUser{" +
                "emailToReceiveUserQuestion='" + emailToReceiveUserQuestion + '\'' +
                ", usernameWhoHasQuestion='" + usernameWhoHasQuestion + '\'' +
                ", subjectWhoHasQuestion='" + subjectWhoHasQuestion + '\'' +
                ", emailWhoHasQuestion='" + emailWhoHasQuestion + '\'' +
                ", messageWhoHasQuestion='" + messageWhoHasQuestion + '\'' +
                ", sendEmailInfo=" + sendEmailInfo +
                '}';
    }
}


