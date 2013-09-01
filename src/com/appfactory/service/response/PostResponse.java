package com.appfactory.service.response;


import com.appfactory.model.Article;

public class PostResponse extends BaseServiceResponse {
	private Article posts;

	public Article getPosts() {
		return posts;
	}

	public void setPosts(Article posts) {
		this.posts = posts;
	}

}
