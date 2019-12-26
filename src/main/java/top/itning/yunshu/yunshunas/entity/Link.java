package top.itning.yunshu.yunshunas.entity;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author itning
 * @date 2019/7/16 23:46
 */
public class Link {
    private static final String WINDOWS_SYSTEM = "win";

    private String name;
    private String link;
    private static String SPLIT_REGEX;

    static {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith(WINDOWS_SYSTEM)) {
            SPLIT_REGEX = "\\\\";
        } else {
            SPLIT_REGEX = "/";
        }
    }

    public static List<Link> build(String location) throws UnsupportedEncodingException {
        String[] locationArray = location.split(SPLIT_REGEX);
        List<Link> linkList = new ArrayList<>(locationArray.length);
        if (locationArray.length == 0) {
            Link link = new Link();
            link.setName(location);
            link.setLink(location);
            linkList.add(link);
            return linkList;
        }
        String last = locationArray[0];
        for (int i = 0; i < locationArray.length; i++) {
            Link link = new Link();
            link.setName(locationArray[i]);
            link.setLink(URLEncoder.encode(last, "UTF-8")
                    .replaceAll("\\+", "%20")
                    .replaceAll("\\%21", "!")
                    .replaceAll("\\%27", "'")
                    .replaceAll("\\%28", "(")
                    .replaceAll("\\%29", ")")
                    .replaceAll("\\%7E", "~"));
            if ((i + 1) != locationArray.length) {
                last += File.separator + locationArray[i + 1];
            }
            linkList.add(link);
        }
        return linkList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Link{" +
                "name='" + name + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
