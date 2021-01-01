package itcast.domain;
import cn.itcast.utils.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

public class Product {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 产品编号
     */
    private String product_no;

    /**
     * 产品名称
     */
    private String product_name;

    /**
     * 出发城市
     */
    private String city_name;

    /**
     * 出发时间(时间戳)
     */
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:ii:ss")
    private Date departure_time;

    /**
     * 出发时间（日期格式化）
     */
    private String departure_time_cn;

    /**
     * 产品价格
     */
    private BigDecimal product_price;

    /**
     * 产品描述
     */
    private String product_desc;

    /**
     * 状态 0-关闭 1-开启
     */
    private Integer product_status;

    /**
     * 状态中文 0-关闭 1-开启
     */
    private String product_status_cn;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 更新时间
     */
    private Date update_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProduct_no() {
        return product_no;
    }

    public void setProduct_no(String product_no) {
        this.product_no = product_no;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getDeparture_time_cn() {
        return Optional.ofNullable(departure_time).map(s -> {
            Date d = new Date(departure_time.getTime());
            return DateUtils.date2String(d, "yyy-MM-dd HH:mm:ss");
        }).orElse("时间戳为空");

//        if (departure_time != null) {
//            Date d = new Date(departure_time.getTime());
//            return DateUtils.date2String(d, "yyy-MM-dd HH:mm:ss");
//        }
//        return "";
    }

    public String getProduct_status_cn() {
        return (product_status == 1) ? "开启" : "关闭";
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public Date getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(Date departure_time) {
        this.departure_time = departure_time;
    }

    public BigDecimal getProduct_price() {
        return product_price;
    }

    public void setProduct_price(BigDecimal product_price) {
        this.product_price = product_price;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }

    public Integer getProduct_status() {
        return product_status;
    }

    public void setProduct_status(Integer product_status) {
        this.product_status = product_status;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", product_no='" + product_no + '\'' +
                ", product_name='" + product_name + '\'' +
                ", city_name='" + city_name + '\'' +
                ", departure_time=" + departure_time +
                ", product_price=" + product_price +
                ", product_desc='" + product_desc + '\'' +
                ", product_status=" + product_status +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }
}
