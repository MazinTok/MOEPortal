package sa.gov.moe.he.moeportal;


import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;

import sa.gov.moe.he.moeportal.DataType.News;


/**
 * Created by mazoo_000 on 24/03/2015.
 */


public class SoapWS2 {

    String namespace ="http://tempuri.org/";
    private String url = "http://rasd.mohe.gov.sa/_layouts/RasdEventWebService/EventsSvc.asmx";


    String SOAP_ACTION ;
    SoapObject request = null;
    SoapSerializationEnvelope envelope;
    HttpTransportSE androidHttpTransport;

    SoapWS2() {
    }


    /**
     * Set Envelope
     */
    protected List<News> SetEnvelope(String Action) {

        List NewsList = new ArrayList<>();
        try {

            // Creating SOAP envelope
            envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            envelope.setAddAdornments(false);
            androidHttpTransport = new HttpTransportSE(url);
            androidHttpTransport.debug = true;

        } catch (Exception e) {
            System.out.println("Soap Exception---->>>" + e.toString());
        }

        try {

            //SOAP calling webservice

            androidHttpTransport.call(Action, envelope);

            //Got Webservice response

            if (envelope.bodyIn instanceof SoapObject) { // SoapObject = SUCCESS
                SoapObject soapObject = (SoapObject) envelope.bodyIn;
                // result = parseSOAPResponse(soapObject);
                SoapObject respond = (SoapObject) soapObject.getProperty("GetRasdEventsResult");
                for (int i=0 ; i <  respond.getPropertyCount() ; i++)
                {
                    News NewsTemp = new News();
                    SoapObject cityForecastNode = (SoapObject) respond.getProperty(i);
                    SoapPrimitive soapurl = (SoapPrimitive) cityForecastNode.getProperty("URL");
                    SoapPrimitive soaptit = (SoapPrimitive) cityForecastNode.getProperty("Title");


                    NewsTemp.setUrl("http://rasd.mohe.gov.sa/"+(String) soapurl.toString());// cityForecastNode.getProperty("URL"));
                   NewsTemp.setTxt((String) soaptit.toString());


                    NewsList.add(NewsTemp);
                }
     } else if (envelope.bodyIn instanceof SoapFault) { // SoapFault = FAILURE
                SoapFault soapFault = (SoapFault) envelope.bodyIn;
                throw new Exception(soapFault.getMessage());
            }

            return NewsList;

        } catch (Exception e) {
            // TODO: handle exception
            Log.d("fucckk",e.toString());
            Log.d("dsd",androidHttpTransport.requestDump);
            Log.d("dsd2",androidHttpTransport.responseDump);
            return null;
        }
    }

    // MethodName variable is define for which webservice function  will call
    public List<News> CallSOAP(int Month )
    {

        String MethodName = "GetRasdEvents";
        try {
            SOAP_ACTION = namespace + MethodName;

            //Adding values to request object
            request = new SoapObject(namespace, MethodName);

            String site = "http://rasd.mohe.gov.sa/";
            String Year = "2015";

            //Adding Double value to request object
            PropertyInfo parm =new PropertyInfo();
            parm.setName("siteColUrl");
            parm.setValue(site);
            parm.setType(String.class);

            PropertyInfo parm2 =new PropertyInfo();
            parm2.setName("Year");
            parm2.setValue(Year);
            parm2.setType(Integer.class);


            PropertyInfo parm3 =new PropertyInfo();
            parm3.setName("Month");
            parm3.setValue(Month);
            parm3.setType(Integer.class);


            request.addProperty(parm);

            request.addProperty(parm2);

            request.addProperty(parm3);

            return SetEnvelope(SOAP_ACTION);


        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }

    }

    public List<News> GetNews()
    {
        List<News> ListItems = new ArrayList<>();

        //TODO return the orignal data3
        ListItems =  CallSOAP(0);

        return ListItems;

    }

}
