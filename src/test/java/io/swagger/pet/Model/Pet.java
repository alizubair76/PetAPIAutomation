package io.swagger.pet.Model;

import java.util.List;

public class Pet {

	private Long id;
	private String name;
	private Attribute category;
	private List<String> photoUrls = null;
	private List<Attribute> tags = null;
	private String status;

	public Long getId() { return id;	}
	public void setId(Long id) {	this.id = id; }

	public Attribute getCategory() { return category; }
	public void setCategory(Attribute category) { this.category = category; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public List<String> getPhotoUrls() { return photoUrls; }
	public void setPhotoUrls(List<String> photoUrls) { this.photoUrls = photoUrls; }

	public List<Attribute> getTags() { return tags;	}
	public void setTags(List<Attribute> tags) {	this.tags = tags;}

	public String getStatus() {	return status;}
	public void setStatus(String status) { this.status = status;}
}