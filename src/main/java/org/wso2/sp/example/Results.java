package org.wso2.sp.example;

import org.wso2.sp.example.Result;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

/**
 * Created by chaminda on 8/3/17.
 */
@XmlRootElement
public class Results {

    @XmlElement(name = "result")
    private Collection<Result> results;

    public Results(){

    }

    public Results(Collection<Result> results){
        this.results=results;
    }

    public Collection<Result> results() {
        return results;
    }
}

