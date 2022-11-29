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
     * the name of the server
     */
    private String serviceName;

    /**
     * the ip of the server
     */
    private String serviceIp;

    /**
     * the port of the server
     */
    private String servicePort;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceIp() {
        return serviceIp;
    }

    public void setServiceIp(String serviceIp) {
        this.serviceIp = serviceIp;
    }

    public String getServicePort() {
        return servicePort;
    }

    public void setServicePort(String servicePort) {
        this.servicePort = servicePort;
    }
}
