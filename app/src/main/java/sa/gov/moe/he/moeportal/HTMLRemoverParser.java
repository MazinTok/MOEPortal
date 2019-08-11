package sa.gov.moe.he.moeportal;

/**
 * Created by mazoo_000 on 14/04/2015.
 */

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import sa.gov.moe.he.moeportal.DataType.News;

public class HTMLRemoverParser {

    HTMLRemoverBean objBean;
    Vector<HTMLRemoverBean> vectParse;

    int mediaThumbnailCount;
    boolean urlflag;
    int count = 0;

    // for https workarawnd
    private void trustEveryone() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier(){
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }});
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager(){
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {}
                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {}
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }}}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(
                    context.getSocketFactory());
        } catch (Exception e) { // should never happen
            e.printStackTrace();
        }
    }
    public List<News> HTMLRemoverParser(String Url) {
        try {

            List<News> LNews = new ArrayList<>();

            vectParse = new Vector<HTMLRemoverBean>();
            URL url = new URL(Url);
            trustEveryone();
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

           // System.out.println("Connection is : " + con);

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            //System.out.println("Reader :" + reader);

            String inputLine;
            String fullStr = "";
            while ((inputLine = reader.readLine()) != null)
                fullStr = fullStr.concat(inputLine + "\n");

            InputStream istream = url.openStream();

            DocumentBuilder builder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();
//==============

            //=============================
           Document doc = builder.parse(istream);

            doc.getDocumentElement().normalize();


            NodeList nList = doc.getElementsByTagName("item");

            System.out.println();

            for (int i = 0; i < nList.getLength(); i++) {

                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    News temp = new News();
                    Element eElement = (Element) nNode;

                    objBean = new HTMLRemoverBean();
                    vectParse.add(objBean);

                    objBean.title = getTagValue("title", eElement);

                    objBean.link = getTagValue("link", eElement);
                    objBean.pubdate = getTagValue("pubDate", eElement);

                    temp.setTxt( objBean.title);
                    // System.out.println("Description is : " + ObjNB.description);
                    temp.setUrl( objBean.link);
                    LNews.add(temp);

                }
            }
            return LNews;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<News> HTMLRemoverParserEvents(String Url) {
        try {

            List<News> LNews = new ArrayList<>();

            vectParse = new Vector<HTMLRemoverBean>();
            URL url = new URL(Url);
            URLConnection con = url.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));

            String inputLine;
            String fullStr = "";
            while ((inputLine = reader.readLine()) != null)
                fullStr = fullStr.concat(inputLine + "\n");

            InputStream istream = url.openStream();

            DocumentBuilder builder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();

            Document doc = builder.parse(istream);

            doc.getDocumentElement().normalize();


            NodeList nList = doc.getElementsByTagName("item");

            System.out.println();

            for (int i = 0; i < nList.getLength(); i++) {

                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    News temp = new News();
                    Element eElement = (Element) nNode;

                    objBean = new HTMLRemoverBean();
                    vectParse.add(objBean);

                    objBean.title = getTagValue("description", eElement);
                    objBean.link = getTagValue("link", eElement);
                    objBean.pubdate = getTagValue("pubDate", eElement);

                    temp.setTxt( objBean.title);
                    // System.out.println("Description is : " + ObjNB.description);
                    temp.setUrl( objBean.link);
                    LNews.add(temp);

                }
            }

            return LNews;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getTagValue(String sTag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
                .getChildNodes();

        Node nValue = (Node) nlList.item(0);

        return nValue.getNodeValue();

    }

    public static void main(String[] args) {
        new HTMLRemoverParser();
    }

}