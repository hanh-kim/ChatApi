
package com.hanhpk.chatapi.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("chatbot")
    @Expose
    private Chatbot chatbot;

    public Chatbot getChatbot() {
        return chatbot;
    }

    public void setChatbot(Chatbot chatbot) {
        this.chatbot = chatbot;
    }

}
