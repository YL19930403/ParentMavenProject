package itcast.domain;


import cn.itcast.utils.DateUtils;

import java.util.Date;
import java.util.List;

public class Order {
    private Integer id;

    private String order_no;

    private Date order_time;

    private String order_time_cn;

    private Integer people_count;

    private String order_desc;

    private Integer pay_type;

    private String pay_type_cn;

    private Integer order_status;

    private String order_status_cn;

    private Integer product_id;

    // 产品-订单 一对一关系
    private Product product;

    private Integer member_id;

    private Member member;

    // 订单-旅客 一对多关系
    private List<People> peoples;

    public void setPay_type_cn(String pay_type_cn) {
        this.pay_type_cn = pay_type_cn;
    }

    public String getOrder_time_cn() {
        if (order_time != null) {
            Date d = new Date(order_time.getTime());
            return DateUtils.date2String(d, "yyy-MM-dd HH:mm:ss");
        }
        return "";
    }

    public void setOrder_time_cn(String order_time_cn) {
        this.order_time_cn = order_time_cn;
    }

    public void setOrder_status_cn(String order_status_cn) {
        this.order_status_cn = order_status_cn;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Member getMember() {
        return member;
    }

    public List<People> getPeoples() {
        return peoples;
    }

    public void setPeoples(List<People> peoples) {
        this.peoples = peoples;
    }

    public String getOrder_status_cn() {
        if (order_status == 1){
            return "已支付";
        } else {
            return "未支付";
        }
    }



    public String getPay_type_cn() {
        if (pay_type == 1){
            return "支付宝";
        }else if (pay_type == 2){
            return "微信";
        } else {
            return "其他";
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public Date getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Date order_time) {
        this.order_time = order_time;
    }

    public Integer getPeople_count() {
        return people_count;
    }

    public void setPeople_count(Integer people_count) {
        this.people_count = people_count;
    }

    public String getOrder_desc() {
        return order_desc;
    }

    public void setOrder_desc(String order_desc) {
        this.order_desc = order_desc;
    }

    public Integer getPay_type() {
        return pay_type;
    }

    public void setPay_type(Integer pay_type) {
        this.pay_type = pay_type;
    }

    public Integer getOrder_status() {
        return order_status;
    }

    public void setOrder_status(Integer order_status) {
        this.order_status = order_status;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getMember_id() {
        return member_id;
    }

    public void setMember_id(Integer member_id) {
        this.member_id = member_id;
    }
}
