package edu.nyit.apiproxy.entity;

/**
 * @author wangtao
 * @date 2022/11/3 18:32
 */
public class BlockList {


    private  int id;

    private String keywords;

    private int categoryId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
