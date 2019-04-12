package com.creaginetech.xpreshoesadmin.Model;

public class Shop {

    private String shopEmail, shopPassword, shopName, shopSearchname, shopImage;

    public Shop() {
    }

    public Shop(String shopEmail, String shopPassword, String shopName, String shopSearchname, String shopImage) {
        this.shopEmail = shopEmail;
        this.shopPassword = shopPassword;
        this.shopName = shopName;
        this.shopSearchname = shopSearchname;
        this.shopImage = shopImage;
    }

    public String getShopEmail() {
        return shopEmail;
    }

    public void setShopEmail(String shopEmail) {
        this.shopEmail = shopEmail;
    }

    public String getShopPassword() {
        return shopPassword;
    }

    public void setShopPassword(String shopPassword) {
        this.shopPassword = shopPassword;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopSearchname() {
        return shopSearchname;
    }

    public void setShopSearchname(String shopSearchname) {
        this.shopSearchname = shopSearchname;
    }

    public String getShopImage() {
        return shopImage;
    }

    public void setShopImage(String shopImage) {
        this.shopImage = shopImage;
    }
}
