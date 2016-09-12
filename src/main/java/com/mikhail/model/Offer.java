package com.mikhail.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement(name = "offer")
public class Offer{
    //Attribute
    private Integer id;
    private Boolean available;
    private String type;

    //Element
    private String url;
    private String price;
    private String currencyId;
    private String categoryId;
    private String pictureUrl;
    private String delivery;
    private String localDeliveryCost;
    private String typePrefix;
    private String vendor;
    private String vendorCode;
    private String model;
    private String description;
    private String manufacturerWarranty;
    private List<Param> params = new ArrayList<Param>();

    private String status = "";
    private String isPictureUrlValid = "";

    public Integer getId() {
        return id;
    }

    @XmlAttribute
    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getAvailable() {
        return available;
    }

    @XmlAttribute
    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getType() {
        return type;
    }

    @XmlAttribute
    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    @XmlElement
    public void setUrl(String url) {
        this.url = url;
    }

    public String getPrice() {
        return price;
    }

    @XmlElement
    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    @XmlElement
    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    @XmlElement
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    @XmlElement(name = "picture")
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getDelivery() {
        return delivery;
    }

    @XmlElement
    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getLocalDeliveryCost() {
        return localDeliveryCost;
    }

    @XmlElement(name = "local_delivery_cost")
    public void setLocalDeliveryCost(String localDeliveryCost) {
        this.localDeliveryCost = localDeliveryCost;
    }

    public String getTypePrefix() {
        return typePrefix;
    }

    @XmlElement
    public void setTypePrefix(String typePrefix) {
        this.typePrefix = typePrefix;
    }

    public String getVendor() {
        return vendor;
    }

    @XmlElement
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    @XmlElement
    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getModel() {
        return model;
    }

    @XmlElement
    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    @XmlElement
    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturerWarranty() {
        return manufacturerWarranty;
    }

    @XmlElement(name = "manufacturer_warranty")
    public void setManufacturerWarranty(String manufacturerWarranty) {
        this.manufacturerWarranty = manufacturerWarranty;
    }

    public List<Param> getParams() {
        return params;
    }

    @XmlElement(name="param")
    public void setParams(List<Param> params) {
        this.params = params;
    }

    public String getStatus() {
        return status;
    }

    @XmlTransient
    public void setStatus(String status) {
        this.status = status;
    }

    public String getPictureUrlValid() {
        return isPictureUrlValid;
    }

    @XmlTransient
    public void setPictureUrlValid(String pictureUrlValid) {
        isPictureUrlValid = pictureUrlValid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Offer)) return false;

        Offer offer = (Offer) o;

        if (getId() != null ? !getId().equals(offer.getId()) : offer.getId() != null) return false;

        if (getAvailable() != null ? !getAvailable().equals(offer.getAvailable()) : offer.getAvailable() != null) {
            setStatus("u");
            return true;
        }

        if (getType() != null ? !getType().equals(offer.getType()) : offer.getType() != null){
            setStatus("u");
            return true;
        }

        if (getUrl() != null ? !getUrl().equals(offer.getUrl()) : offer.getUrl() != null){
            setStatus("u");
            return true;
        }

        if (getPrice() != null ? !getPrice().equals(offer.getPrice()) : offer.getPrice() != null){
            setStatus("u");
            return true;
        }

        if (getCurrencyId() != null ? !getCurrencyId().equals(offer.getCurrencyId()) : offer.getCurrencyId() != null){
            setStatus("u");
            return true;
        }

        if (getCategoryId() != null ? !getCategoryId().equals(offer.getCategoryId()) : offer.getCategoryId() != null){
            setStatus("u");
            return true;
        }


        if (getPictureUrl() != null ? !getPictureUrl().equals(offer.getPictureUrl()) : offer.getPictureUrl() != null){
            setStatus("u");
            return true;
        }


        if (getDelivery() != null ? !getDelivery().equals(offer.getDelivery()) : offer.getDelivery() != null){
            setStatus("u");
            return true;
        }


        if (getLocalDeliveryCost() != null ? !getLocalDeliveryCost().equals(offer.getLocalDeliveryCost()) : offer.getLocalDeliveryCost() != null){
            setStatus("u");
            return true;
        }


        if (getTypePrefix() != null ? !getTypePrefix().equals(offer.getTypePrefix()) : offer.getTypePrefix() != null){
            setStatus("u");
            return true;
        }

        if (getVendor() != null ? !getVendor().equals(offer.getVendor()) : offer.getVendor() != null){
            setStatus("u");
            return true;
        }

        if (getVendorCode() != null ? !getVendorCode().equals(offer.getVendorCode()) : offer.getVendorCode() != null){
            setStatus("u");
            return true;
        }

        if (getModel() != null ? !getModel().equals(offer.getModel()) : offer.getModel() != null){
            setStatus("u");
            return true;
        }
        if (getDescription() != null ? !getDescription().equals(offer.getDescription()) : offer.getDescription() != null){
            setStatus("u");
            return true;
        }

        if (getManufacturerWarranty() != null ? !getManufacturerWarranty().equals(offer.getManufacturerWarranty()) : offer.getManufacturerWarranty() != null){
            setStatus("u");
            return true;
        }

        Boolean value = getParams() != null ? getParams().equals(offer.getParams()) : offer.getParams() == null;
        if(!value){
            setStatus("u");
            return true;
        } else {
            setStatus("");
            return true;
        }

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getAvailable() != null ? getAvailable().hashCode() : 0);
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getUrl() != null ? getUrl().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getCurrencyId() != null ? getCurrencyId().hashCode() : 0);
        result = 31 * result + (getCategoryId() != null ? getCategoryId().hashCode() : 0);
        result = 31 * result + (getPictureUrl() != null ? getPictureUrl().hashCode() : 0);
        result = 31 * result + (getDelivery() != null ? getDelivery().hashCode() : 0);
        result = 31 * result + (getLocalDeliveryCost() != null ? getLocalDeliveryCost().hashCode() : 0);
        result = 31 * result + (getTypePrefix() != null ? getTypePrefix().hashCode() : 0);
        result = 31 * result + (getVendor() != null ? getVendor().hashCode() : 0);
        result = 31 * result + (getVendorCode() != null ? getVendorCode().hashCode() : 0);
        result = 31 * result + (getModel() != null ? getModel().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getManufacturerWarranty() != null ? getManufacturerWarranty().hashCode() : 0);
        result = 31 * result + (getParams() != null ? getParams().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (isPictureUrlValid != null ? isPictureUrlValid.hashCode() : 0);
        return result;
    }
}
