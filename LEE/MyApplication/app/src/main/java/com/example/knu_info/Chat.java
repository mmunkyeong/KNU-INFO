package com.example.knu_info;

public class Chat {
    String text;
    String Id;
    String email;

    public Chat(){

    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }


}
