package com.docker.sample.qrcode.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Merchant {
    public Merchant() {
    }
    private String merchantID = null;
    private String merchantName = null;

  
    public String getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
    }
    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

  

    @Override
    public String toString() {
        return "MerchantID:"+merchantID;
    }

    /**
     * Constructor for mapping a user to a CardHolder Object
     * @param merchant
     */
    public Merchant(Merchant merchant){
        this.setMerchantID(merchant.getMerchantID());
        this.setMerchantName(merchant.getMerchantName());
       

    }


}
