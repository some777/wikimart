package com.mikhail.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement
public class Param {
    //Value
    private String sValue;

    //Attribute
    private String paramName;
    private String paramType;
    private String paramUnit;
    private String paramValue;

    public String getsValue() {
        return sValue;
    }

    @XmlValue
    public void setsValue(String sValue) {
        this.sValue = sValue;
    }

    public String getParamName() {
        return paramName;
    }

    @XmlAttribute(name = "name")
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamType() {
        return paramType;
    }

    @XmlAttribute(name = "type")
    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getParamUnit() {
        return paramUnit;
    }

    @XmlAttribute(name = "unit")
    public void setParamUnit(String paramUnit) {
        this.paramUnit = paramUnit;
    }

    public String getParamValue() {
        return paramValue;
    }

    @XmlAttribute(name = "value")
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Param)) return false;

        Param param = (Param) o;

        if (getsValue() != null ? !getsValue().equals(param.getsValue()) : param.getsValue() != null) return false;
        if (getParamName() != null ? !getParamName().equals(param.getParamName()) : param.getParamName() != null)
            return false;
        if (getParamType() != null ? !getParamType().equals(param.getParamType()) : param.getParamType() != null)
            return false;
        if (getParamUnit() != null ? !getParamUnit().equals(param.getParamUnit()) : param.getParamUnit() != null)
            return false;
        return getParamValue() != null ? getParamValue().equals(param.getParamValue()) : param.getParamValue() == null;

    }

    @Override
    public int hashCode() {
        int result = getsValue() != null ? getsValue().hashCode() : 0;
        result = 31 * result + (getParamName() != null ? getParamName().hashCode() : 0);
        result = 31 * result + (getParamType() != null ? getParamType().hashCode() : 0);
        result = 31 * result + (getParamUnit() != null ? getParamUnit().hashCode() : 0);
        result = 31 * result + (getParamValue() != null ? getParamValue().hashCode() : 0);
        return result;
    }
}
