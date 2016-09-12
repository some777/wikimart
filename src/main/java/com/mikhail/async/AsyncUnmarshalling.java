package com.mikhail.async;

import com.mikhail.model.YmlCatalog;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;


public class AsyncUnmarshalling implements Callable {
    private  String fileName;

    public AsyncUnmarshalling(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public YmlCatalog call() throws Exception {
        YmlCatalog ymlCatalogNew = unmarshalling(fileName);

        return ymlCatalogNew;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private YmlCatalog unmarshalling(String fName){
        JAXBContext jc = null;
        Unmarshaller u = null;
        YmlCatalog ymlCatalog = null;
        try {
            jc = JAXBContext.newInstance(YmlCatalog.class);

            // unmarshal from file
            u = jc.createUnmarshaller();
            ymlCatalog = (YmlCatalog)u.unmarshal( new File(fName) );
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return ymlCatalog;
    }
}
