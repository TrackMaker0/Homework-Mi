package com.example.homeworkday09;

public class MyRepo {
    private int id;
    private String name;
    private String full_name;
    private String html_url;
    private String description;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return full_name;
    }

    public String getHtmlUrl() {
        return html_url;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Repo ID: " + id
                + "\nRepo Name: " + name
                + "\nFull Name: " + full_name
                + "\nURL: " + html_url
                + "\nDescription: " + description +
                "\n";
    }
}

