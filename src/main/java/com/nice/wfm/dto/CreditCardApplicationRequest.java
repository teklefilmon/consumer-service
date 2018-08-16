package com.nice.wfm.dto;

/**
 * Created on 8/15/18.
 * Author: filmon
 * Nice Systems Ltd.
 */

public class CreditCardApplicationRequest{

    private String ssn;
    private CardType cardType;

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public enum CardType {
        GOLD
    }
}
