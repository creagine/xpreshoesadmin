package com.creaginetech.xpreshoesadmin.Model;

public class Shipper {

    String shipperName,shipperEmail,role;

    public Shipper() {
    }

    public Shipper(String shipperName, String shipperEmail, String role) {
        this.shipperName = shipperName;
        this.shipperEmail = shipperEmail;
        this.role = role;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public String getShipperEmail() {
        return shipperEmail;
    }

    public void setShipperEmail(String shipperEmail) {
        this.shipperEmail = shipperEmail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
