package com.mikhail.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "currency")
public class Currency {
    private String id;
    private String rate;

    public String getId() {
        return id;
    }

    @XmlAttribute
    public void setId(String id) {
        this.id = id;
    }

    public String getRate() {
        return rate;
    }

    @XmlAttribute
    public void setRate(String rate) {
        this.rate = rate;
    }
}
