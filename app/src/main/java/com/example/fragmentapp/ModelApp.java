package com.example.fragmentapp;

public class ModelApp {

    private  String img;
    private String head,detail,date,venue,link;


    public ModelApp(String img, String head, String detail, String date, String venue, String link) {
        this.img = img;
        this.head = head;
        this.detail = detail;
        this.date = date;
        this.venue = venue;
        this.link = link;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

public ModelApp(){


        }}
