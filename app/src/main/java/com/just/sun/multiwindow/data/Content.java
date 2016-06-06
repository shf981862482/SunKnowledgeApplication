package com.just.sun.multiwindow.data;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by walkingMen on 2016/6/1.
 */
public class Content {
    @DatabaseField(id = true)
    private String content;

    public Content() {
    }

    public Content(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Content content1 = (Content) o;

        return content != null ? content.equals(content1.content) : content1.content == null;

    }

    @Override
    public int hashCode() {
        return content != null ? content.hashCode() : 0;
    }
}
