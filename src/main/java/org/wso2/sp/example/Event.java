package org.wso2.sp.example;

import org.codehaus.jackson.map.annotate.JsonRootName;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;

/**
 * Created by chaminda on 8/6/17.
 */
//@XmlType(propOrder = {"message", "method", "headers"})
/*@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "event", propOrder = {
        "message",
        "method",
        "headers"
})*/

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonRootName(value = "event")
public class Event implements Serializable, Comparable<Event> {

//Working code for an event as {"message":"Hi SP","method":"POST", "headers":"cclassName:com.wso2.sp.test.VerifyXML"}
    //also working with {"event":{"message":"Hi SP","method":"POST", "headers":"className:com.wso2.sp.test.VerifyXML"}}

    public Event() {

    }

    public Event(String message, String method, String headers) {
        this.message = message;
        this.method = method;
        this.headers = headers;
    }

    @XmlElement(required = true)
    private String message;
    @XmlElement(required = true)
    private String method;
    @XmlElement(required = true)
    private String headers;

    public String getMessage() {
        return message;
    }


    public String getMethod() {
        return method;
    }


    public String getHeaders() {
        return headers;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public void setMethod(String method) {
        this.method = method;
    }


    public void setHeaders(String headers) {
        this.headers = headers;
    }


    @Override
    public int compareTo(Event o) {
        return 0;
    }
}
