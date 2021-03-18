package com.example.lifelapseapp.model;


import java.io.Serializable;

/**
 * Helps navigate through the pagination of the application
 * with next and prev url string
 */
public class PageInfo implements Serializable {

    private String count, pages, next, prev;

    public PageInfo(String next, String prev) {
        this.next = next;
        this.prev = prev;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }
}
