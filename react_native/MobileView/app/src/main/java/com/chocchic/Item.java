package com.chocchic;

import java.io.Serializable;

public class Item implements Serializable {
    public Long getItemid() {
        return itemid;
    }

    public void setItemid(Long itemid) {
        this.itemid = itemid;
    }

    private Long itemid;

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    private String itemname;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    private Integer price;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureurl() {
        return pictureurl;
    }

    public void setPictureurl(String pictureurl) {
        this.pictureurl = pictureurl;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String description;
    private String pictureurl;

    public String getEmail() {
        return email;
    }

    private String email;

    @Override
    public String toString() {
        return "Item{" +
                "itemid=" + itemid +
                ", itemname='" + itemname + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", pictureurl='" + pictureurl + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
