package org.codinglabs.rss2epub;

public class BookConfig {
    /**
     * Book title
     */
    private String title;
    /**
     * Book author
     */
    private String author;
    /**
     * Include images
     */
    private boolean image;
    /**
     * Book cover image
     */
    private String cover;
    /**
     * Feed urls
     */
    private String[] feeds;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isImage() {
        return image;
    }

    public void setImage(boolean image) {
        this.image = image;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String[] getFeeds() {
        return feeds;
    }

    public void setFeeds(String[] feedUrls) {
        this.feeds = feedUrls;
    }
}