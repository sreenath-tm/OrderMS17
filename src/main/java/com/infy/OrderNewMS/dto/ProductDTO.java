package com.infy.OrderNewMS.dto;


public class ProductDTO {
	
		int prodId;
		String brand;
		String category;
		String description;
		String image;
		String productname;
		String subcategory;
		int sellerid;
		int stock;
		float price;
		Integer rating;
		public ProductDTO()
		{
		super();	
		}
		public ProductDTO(int prodId,String brand,String description,String image,String productname,String subcategory,int sellerid,int stock,long price,Integer rating)
		{
			this();
			this.prodId=prodId;
			this.brand=brand;
			this.description=description;
			this.image=image;
			this.productname=productname;
			this.subcategory=subcategory;
			this.sellerid=sellerid;
			this.stock=stock;
			this.price=price;
			this.rating=rating;
		}
		public int getProdId() {
			return prodId;
		}
		public void setProdId(int prodId) {
			this.prodId = prodId;
		}
		public String getBrand() {
			return brand;
		}
		public void setBrand(String brand) {
			this.brand = brand;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		public String getProductname() {
			return productname;
		}
		public void setProductname(String productname) {
			this.productname = productname;
		}
		public String getSubcategory() {
			return subcategory;
		}
		public void setSubcategory(String subcategory) {
			this.subcategory = subcategory;
		}
		public int getSellerid() {
			return sellerid;
		}
		public void setSellerid(int sellerid) {
			this.sellerid = sellerid;
		}
		public int getStock() {
			return stock;
		}
		public void setStock(int stock) {
			this.stock = stock;
		}
		
		
		public float getPrice() {
			return price;
		}
		public void setPrice(float price) {
			this.price = price;
		}
		public Integer getRating() {
			return rating;
		}
		public void setRating(Integer rating) {
			this.rating = rating;
		}

	}

