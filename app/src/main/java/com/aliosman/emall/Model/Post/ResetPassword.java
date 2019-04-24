package com.aliosman.emall.Model.Post;

import com.google.gson.Gson;

public class ResetPassword {
    private String Email;

    public ResetPassword(String email) {
        Email = email;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}