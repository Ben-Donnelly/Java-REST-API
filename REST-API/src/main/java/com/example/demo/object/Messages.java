package com.example.demo.object;

public class Messages {

    private String emailAddress1;
    private String emailAddress2;
    private String chatHistory;

    public Messages()
    {
        super();
    }


    public Messages(String emailAddress1, String emailAddress2, String chatHistory) {
        super();
        this.emailAddress1 = emailAddress1;
        this.emailAddress2 = emailAddress2;
        this.chatHistory = chatHistory;
    }

    // Getters and Setters

    public String getEmailAddress1() {
        return emailAddress1;
    }

    public void setEmailAddress1(String emailAddress1) {
        this.emailAddress1 = emailAddress1;
    }

    public String getEmailAddress2() {
        return emailAddress2;
    }

    public void setEmailAddress2(String emailAddress2) {
        this.emailAddress2 = emailAddress2;
    }

    public String getChatHistory()
    {
        return chatHistory;
    }

    public void setChatHistory(String chatHistory)
    {
        this.chatHistory = chatHistory;
    }
}
