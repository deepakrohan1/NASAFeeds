package com.example.rohan.nasafeeds;

import java.io.Serializable;

/**
 * Created by rohan on 10/10/15.
 */
public class Feed implements Serializable {
    /** <item> <title>Morning Aurora From the Space Station</title>
     <link>http://www.nasa.gov/image-feature/morning-aurora-from-the-space-station</link>
     <description>NASA astronaut Scott Kelly captured this photograph from the International Space Station on Oct. 7, 2015. Sharing with his social media followers, Kelly wrote, &quot;The daily morning dose of #aurora to help wake you up. #GoodMorning from @Space_Station! #YearInSpace&quot;</description>
     <enclosure url="http://www.nasa.gov/sites/default/files/thumbnails/image/iss045e048728.jpg" length="2146311" type="image/jpeg" />
     <guid isPermaLink="false">http://www.nasa.gov/image-feature/morning-aurora-from-the-space-station</guid>
     <pubDate>Fri, 09 Oct 2015 09:39 EDT</pubDate>
     <source url="http://www.nasa.gov/rss/dyn/lg_image_of_the_day.rss">NASA Image of the Day</source>
     </item>
     **/

    private String title, date, link, description, imageLink;
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Feed{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", id=" + id +
                '}';
    }
}
