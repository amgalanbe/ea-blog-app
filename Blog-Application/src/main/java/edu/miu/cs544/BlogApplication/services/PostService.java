package edu.miu.cs544.BlogApplication.services;


import edu.miu.cs544.BlogApplication.entity.Post;

import java.util.List;


public interface PostService {
    public long save(Post post);
    public List<Post> findAll();
    public Post findById(long id);
    public void update(Post post);
    public void deleteById(long id);
}
