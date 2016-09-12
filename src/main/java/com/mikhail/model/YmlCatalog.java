package com.mikhail.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "yml_catalog")
public class YmlCatalog {
    private Shop shop;
    private String date;

    public Shop getShop() {
        return shop;
    }

    @XmlElement(name = "shop")
    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public String getDate() {
        return date;
    }

    @XmlAttribute
    public void setDate(String date) {
        this.date = date;
    }
}
