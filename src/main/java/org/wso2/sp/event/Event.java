/*
 * Copyright (c) 2016, WSO2 Inc. (http://wso2.com) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wso2.sp.event;

import org.codehaus.jackson.map.annotate.JsonRootName;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**.
 * Event class.
 */
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
