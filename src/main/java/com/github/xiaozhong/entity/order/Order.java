package com.github.xiaozhong.entity.order;

/**
 * @author zhougaolei
 * @date At 2019/3/8
 */
public class Order {

    private Long id;

    private Integer price;

    private String orderDesc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", price=" + price +
                ", orderDesc='" + orderDesc + '\'' +
                '}';
    }
}
