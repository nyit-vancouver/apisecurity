package edu.nyit.orderservice.entity;

/**
 * @author wangtao
 * @date 2022/11/30 15:32
 */
public class OrderInfo {

    private String id;

    private String productName;

    private String productDesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
}
