package com.example.appmusic.model;

public class Song {
    String linkSong;
    String linkImage;
    String nameSong;
    String nameSinger;
    String description;
    public Song() {
    }

    public Song( String linkSong,String linkImage, String nameSong, String nameSinger, String description) {
        this.linkSong = linkSong;
        this.linkImage = linkImage;
        this.nameSong = nameSong;
        this.nameSinger = nameSinger;
        this.description = description;
    }

    public String getLinkSong() {
        return linkSong;
    }

    public void setLinkSong(String linkSong) {
        this.linkSong = linkSong;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getNameSinger() {
        return nameSinger;
    }

    public void setNameSinger(String nameSinger) {
        this.nameSinger = nameSinger;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
