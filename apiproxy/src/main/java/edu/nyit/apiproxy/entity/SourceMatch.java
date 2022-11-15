package edu.nyit.apiproxy.entity;

/**
 *
 * The entity of the table source_match
 * @author wangtao
 * @date 2022/11/14 22:31
 */
public class SourceMatch {

    private int id;

    /**
     * the unique code of the server
     */
    private int code;

    /**
     * the url of the server
     */
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
