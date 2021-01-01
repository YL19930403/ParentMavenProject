package itcast.domain;


public class People {
    private Integer id;

    private String name;

    private Integer sex;

    private Integer phone_no;

    private Integer identity_type;

    private String identity_type_cn;

    public String getIdentity_type_cn() {
        if (identity_type == 1){
            return "身份证";
        } else if ( identity_type == 2) {
            return "护照";
        } else {
            return "军官证";
        }
    }

    public void setIdentity_type_cn(String identity_type_cn) {
        this.identity_type_cn = identity_type_cn;
    }

    public String getPeople_type_cn() {
       if (people_type == 1){
            return "儿童";
       } else {
           return "成人";
       }
    }

    public void setPeople_type_cn(String people_type_cn) {
        this.people_type_cn = people_type_cn;
    }

    private String identity_no;

    private Integer people_type;

    private String people_type_cn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(Integer phone_no) {
        this.phone_no = phone_no;
    }

    public Integer getIdentity_type() {
        return identity_type;
    }

    public void setIdentity_type(Integer identity_type) {
        this.identity_type = identity_type;
    }

    public String getIdentity_no() {
        return identity_no;
    }

    public void setIdentity_no(String identity_no) {
        this.identity_no = identity_no;
    }

    public Integer getPeople_type() {
        return people_type;
    }

    public void setPeople_type(Integer people_type) {
        this.people_type = people_type;
    }
}
