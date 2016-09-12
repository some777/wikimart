package com.mikhail.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "shop")
public class Shop {
    private String name;
    private String company;
    private String url;
    private String localDeliveryCost;
    private List<Currency> currencies;
    private List<Category> categories;
    private List<Offer> offers;

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    @XmlElement
    public void setCompany(String company) {
        this.company = company;
    }

    public String getUrl() {
        return url;
    }

    @XmlElement
    public void setUrl(String url) {
        this.url = url;
    }

    public String getLocalDeliveryCost() {
        return localDeliveryCost;
    }

    @XmlElement(name = "local_delivery_cost")
    public void setLocalDeliveryCost(String localDeliveryCost) {
        this.localDeliveryCost = localDeliveryCost;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    @XmlElementWrapper(name="currencies")
    @XmlElement(name="currency")
    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public List<Category> getCategories() {
        return categories;
    }

    @XmlElementWrapper(name="categories")
    @XmlElement(name="category")
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    @XmlElementWrapper(name="offers")
    @XmlElement(name="offer")
    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }
}
