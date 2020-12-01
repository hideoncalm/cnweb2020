package com.cnweb2020.Json2Model;

public class JsonReturnModel extends CodeAndMessage{
    private Object content;
    public JsonReturnModel(int code, String message, Object content) {
        super(code, message);
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
