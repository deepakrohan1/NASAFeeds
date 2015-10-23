package com.example.rohan.nasafeeds;

import android.util.Log;
import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by rohan on 10/10/15.
 */
public class FeedParser {
    static public class NasaFeedParser extends DefaultHandler {
        ArrayList<Feed> feedsList;
        Feed feeds;
        StringBuilder sb;

        public ArrayList<Feed> getFeedsList() {
            Log.d("Demo", "Insidegetter");
            if (feedsList.size() == 0) {
                Log.d("Demo", "Empty");
            }
            Log.d("Demo", feedsList.size() + "");
            return feedsList;

        }

        static public ArrayList<Feed> NasaParser(InputStream in) throws IOException, SAXException {
            Log.d("Demo", "Inside Parser");
            NasaFeedParser parser = new NasaFeedParser();
            Xml.parse(in, Xml.Encoding.UTF_8, parser);
            for (Feed f : parser.getFeedsList()) {
                Log.d("DEmo", f.toString());
            }
            return parser.getFeedsList();
        }

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            feedsList = new ArrayList<Feed>();
            sb = new StringBuilder();

        }

        /**
         * <item> <title>Morning Aurora From the Space Station</title>
         * <link>http://www.nasa.gov/image-feature/morning-aurora-from-the-space-station</link>
         * <description>NASA astronaut Scott Kelly captured this photograph from the International Space Station on Oct. 7, 2015. Sharing with his social media followers, Kelly wrote, &quot;The daily morning dose of #aurora to help wake you up. #GoodMorning from @Space_Station! #YearInSpace&quot;</description>
         * <enclosure url="http://www.nasa.gov/sites/default/files/thumbnails/image/iss045e048728.jpg" length="2146311" type="image/jpeg" />
         * <guid isPermaLink="false">http://www.nasa.gov/image-feature/morning-aurora-from-the-space-station</guid>
         * <pubDate>Fri, 09 Oct 2015 09:39 EDT</pubDate>
         * <source url="http://www.nasa.gov/rss/dyn/lg_image_of_the_day.rss">NASA Image of the Day</source>
         * </item>
         **/

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if ((localName.equals("item"))|| (localName.equals("channel"))) {
                feeds = new Feed();
            } else if (localName.equals("enclosure")) {
                feeds.setLink(attributes.getValue("url"));
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            if (localName.equals("item")) {
                feedsList.add(feeds);
            }else if(localName.equals("link")){
                feeds.setLink(sb.toString().trim());
            } else if ((localName.equals("title"))) {
                feeds.setTitle(sb.toString().trim());
            } else if ((localName.equals("description"))) {
                feeds.setDescription(sb.toString().trim());
            } else if ((localName.equals("pubDate"))){
                feeds.setDate(sb.toString().trim());
            }
            sb.setLength(0);

        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
//            super.characters(ch, start, length);
            sb.append(ch, start, length);
        }
    }
}
