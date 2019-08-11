package sa.gov.moe.he.moeportal.DataType;

/**
 * Created by mazoo_000 on 08/04/2015.
 */
public class News {
    String Txt;
    String Url;
    private String content;

    public News(String txt, String url) {
        Txt = txt;
        Url = url;
        setContent("");
    }

    public News() {
    }

    public String getTxt() {
        return Txt;
    }

    public String getUrl() {
        return Url;
    }

    public void setTxt(String txt) {
        Txt = txt;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
