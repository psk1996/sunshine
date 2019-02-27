package cn.tedu.store.entity;

public class Logo {
    //
    private Integer id;
    //
    private String logoName;
    //
    private String URL;
    //
    private Integer paixu;

    //状态 0‘显示’1‘不显示’
    private Integer zhuangtai;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogoName() {
        return logoName;
    }

    public void setLogoName(String logoName) {
        this.logoName = logoName;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public Integer getPaixu() {
        return paixu;
    }

    public void setPaixu(Integer paixu) {
        this.paixu = paixu;
    }

    public Integer getZhuangtai() {
        return zhuangtai;
    }

    public void setZhuangtai(Integer zhuangtai) {
        this.zhuangtai = zhuangtai;
    }

    @Override
    public String toString() {
        return "Logo{" +
                "id=" + id +
                ", logoName='" + logoName + '\'' +
                ", URL='" + URL + '\'' +
                ", paixu=" + paixu +
                ", zhuangtai=" + zhuangtai +
                '}';
    }
}
