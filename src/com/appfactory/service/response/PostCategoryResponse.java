package com.appfactory.service.response;

import java.util.List;

import com.appfactory.model.Article;

public class PostCategoryResponse extends BaseServiceResponse {
	private List<Article> posts;

	public List<Article> getPosts() {
		return posts;
	}

	public void setPosts(List<Article> posts) {
		this.posts = posts;
	}

}
