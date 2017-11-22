package cn.javaxxw.model;

import java.math.BigDecimal;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2017-11-22 17:12
 **/
public class Order {


    private String orderNo;

    private BigDecimal price;

    private String title;

    private String desc;


    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNo='" + orderNo + '\'' +
                ", price=" + price +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
