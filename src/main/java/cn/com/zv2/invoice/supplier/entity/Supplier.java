package cn.com.zv2.invoice.supplier.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lb
 * @date 2019/10/14 1:08
 */
public class Supplier {

    public static final Integer SUPPLIER_DELIVERED_IS_YES = 1;
    public static final Integer SUPPLIER_DELIVERED_IS_NO = 0;

    public static final String SUPPLIER_DELIVERED_IS_YES_VIEW = "送货";
    public static final String SUPPLIER_DELIVERED_IS_NO_VIEW = "自提";

    public static final Map<Integer, String> deliveredMap = new HashMap<>();

    static {
        deliveredMap.put(SUPPLIER_DELIVERED_IS_YES, SUPPLIER_DELIVERED_IS_YES_VIEW);
        deliveredMap.put(SUPPLIER_DELIVERED_IS_NO, SUPPLIER_DELIVERED_IS_NO_VIEW);
    }

    private Long id;
    private String name;
    private String address;
    private String telephone;
    private String contact;
    private Integer delivered;
    private String deliveredView;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Integer getDelivered() {
        return delivered;
    }

    public void setDelivered(Integer delivered) {
        this.delivered = delivered;
        this.deliveredView = deliveredMap.get(delivered);
    }

    public String getDeliveredView() {
        return deliveredView;
    }

}
