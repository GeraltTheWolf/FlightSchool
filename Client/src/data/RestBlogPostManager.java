package data;

import model.BlogPost;
import model.BlogPostManager;
import model.UserManager;

import java.util.List;
import java.util.UUID;

public class RestBlogPostManager implements BlogPostManager {
    @Override
    public List<BlogPost> GetAll() {
        return null;
    }

    @Override
    public BlogPost GetByUid(UUID blogPostUid) {
        return null;
    }

    @Override
    public BlogPost Create(BlogPost blogPost) {
        return null;
    }

    @Override
    public void Update(BlogPost blogPost) {

    }

    @Override
    public void Delete(UUID blogPostUid) {

    }
}
