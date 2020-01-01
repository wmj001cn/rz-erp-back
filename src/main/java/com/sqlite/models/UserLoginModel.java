/*
 * Decompiled with CFR 0_123.
 */
package com.sqlite.models;

public class UserLoginModel {
    private Integer id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String mobile;
    private String email;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        UserLoginModel person = (UserLoginModel)obj;
        if (this.firstName != null ? !this.firstName.equals(person.firstName) : person.firstName != null) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "UserLoginModel [id=" + this.id + ", firstName=" + this.firstName + ", lastName=" + this.lastName + ", userName=" + this.userName + ", password=" + this.password + ", mobile=" + this.mobile + ", email=" + this.email + "]";
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

