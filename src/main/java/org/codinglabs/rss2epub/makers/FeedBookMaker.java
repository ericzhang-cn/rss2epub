package org.codinglabs.rss2epub.makers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import nl.siegmann.epublib.domain.Author;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.MediaType;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.domain.TOCReference;
import nl.siegmann.epublib.epub.EpubWriter;

import org.codinglabs.rss2epub.ymal.FeedBookConfig;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class FeedBookMaker {
    /**
     * Parse config file
     * 
     * @param configFilePath
     *            Config file path
     * @return
     */
    protected static FeedBookConfig parseConfig(String configFilePath) {
        Yaml yaml = new Yaml(new Constructor(FeedBookConfig.class));
        FeedBookConfig config;
        try {
            config = (FeedBookConfig) yaml.load(new FileInputStream(new File(
                    configFilePath)));
        } catch (FileNotFoundException e) {
            return null;
        }

        return config;
    }

    /**
     * Read content from feeds
     * 
     * @param config
     *            Configuration
     * @return
     */
    protected static ArrayList<SyndFeed> readFeeds(FeedBookConfig config) {
        ArrayList<SyndFeed> feeds = new ArrayList<SyndFeed>();

        for (String feedUrl : config.getFeeds()) {
            try {
                URL url = new URL(feedUrl);
                XmlReader reader = new XmlReader(url);
                SyndFeed feed = new SyndFeedInput().build(reader);
                feeds.add(feed);
            } catch (Exception e) {
                continue;
            }
        }

        return feeds;
    }

    /**
     * Make epub book
     * 
     * @param config
     *            Configuration
     * @param feeds
     *            Feeds content
     * @param outputFilePath
     *            Output epub book path
     */
    protected static void make(FeedBookConfig config,
            ArrayList<SyndFeed> feeds, String outputFilePath) {
        try {
            Book book = new Book();
            book.getMetadata().addTitle(config.getTitle());
            book.getMetadata().addAuthor(new Author(config.getAuthor()));

            for (SyndFeed feed : feeds) {
                StringBuilder sb = new StringBuilder();
                sb.append("<h1>");
                sb.append(feed.getTitle());
                sb.append("</h1>");
                if (feed.getAuthor() != null) {
                    sb.append("<p>");
                    sb.append(feed.getAuthor());
                    sb.append("</p>");
                }
                if (feed.getLink() != null) {
                    sb.append("<p>");
                    sb.append(feed.getLink());
                    sb.append("</p>");
                }
                TOCReference site = book
                        .addSection(feed.getTitle(), new Resource(sb.toString()
                                .getBytes("UTF-8"), new MediaType(
                                "application/xhtml+xml", "html")));

                @SuppressWarnings("rawtypes")
                Iterator iter = feed.getEntries().iterator();

                while (iter.hasNext()) {
                    SyndEntry entry = (SyndEntry) iter.next();
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("<h2>");
                    sb2.append(entry.getTitle());
                    sb2.append("</h2>");
                    if (entry.getAuthor() != null) {
                        sb2.append("<p>");
                        sb2.append(entry.getAuthor());
                        sb2.append("</p>");
                    }
                    if (entry.getLink() != null) {
                        sb2.append("<p>");
                        sb2.append(entry.getLink());
                        sb2.append("</p>");
                    }
                    sb2.append("<div>");
                    if (!entry.getContents().isEmpty()) {
                        sb2.append(entry.getContents().get(0));
                    } else {
                        sb2.append(entry.getDescription().getValue());
                    }
                    sb2.append("</div>");
                    book.addSection(site, entry.getTitle(), new Resource(sb2
                            .toString().getBytes("UTF-8"), new MediaType(
                            "application/xhtml+xml", "html")));
                }
            }

            EpubWriter epub = new EpubWriter();
            epub.write(book, new FileOutputStream(outputFilePath));
        } catch (IOException e) {
            return;
        }
    }

    public static void main(String[] args) {
        FeedBookConfig config = parseConfig("book.yml");
        ArrayList<SyndFeed> feeds = readFeeds(config);
        make(config, feeds, "book.epub");
    }
}
