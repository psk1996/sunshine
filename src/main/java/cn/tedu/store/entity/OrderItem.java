package cn.tedu.store.entity;

import java.io.Serializable;

public class OrderItem extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id ;
	private Integer oid ;
	private Long goodsId ;
	private String goodsTitle ;
	private String goodsImage ;
	private Long goodsPrice ;
	private Integer goodsNum ;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsTitle() {
		return goodsTitle;
	}
	public void setGoodsTitle(String goodsTitle) {
		this.goodsTitle = goodsTitle;
	}
	public String getGoodsImage() {
		return goodsImage;
	}
	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}
	public Long getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(Long goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public Integer getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", oid=" + oid + ", goodsId=" + goodsId + ", goodsTitle=" + goodsTitle
				+ ", goodsImage=" + goodsImage + ", goodsPrice=" + goodsPrice + ", goodsNum=" + goodsNum + "]";
	}
}
