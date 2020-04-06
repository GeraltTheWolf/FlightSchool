package model;

import java.util.List;
import java.util.UUID;

public interface BlogPostManager {
    List<BlogPost> GetAll();

    BlogPost GetByUid(UUID blogPostUid);

    BlogPost Create(BlogPost blogPost);

    void Update(BlogPost blogPost);

    void Delete(UUID blogPostUid);
}
