package com.hanhpk.chatapi.model;

public class ChatFactory {

    private int typeId;
    private String message;
    private Result result;

    public ChatFactory(int typeId, String message) {
        this.typeId = typeId;
        this.message = message;
    }

    public ChatFactory(int typeId, Result result) {
        this.typeId = typeId;
        this.result = result;
    }

    public ChatFactory(int typeId, String message, Result result) {
        this.typeId = typeId;
        this.message = message;
        this.result = result;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
