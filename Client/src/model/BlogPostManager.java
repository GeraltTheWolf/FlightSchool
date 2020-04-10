package model;

import java.util.List;
import java.util.UUID;

public interface BlogPostManager {
    List<BlogPost> GetAll();

    BlogPost GetById(long blogPostId);

    BlogPost Create(BlogPost blogPost);

    boolean Update(BlogPost blogPost);

    boolean Delete(long blogPostId);
}
