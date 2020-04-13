package data;

import model.BlogPost;
import model.BlogPostManager;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class RestBlogPostManager implements BlogPostManager {

    private static String API_BLOG_POST_URL = "http://localhost:8080/api/blog";

    @Override
    public List<BlogPost> GetAll() {
        try {
//            List<BlogPost> posts = new ArrayList<>();
//
//            posts.add(new BlogPost(1,"First One","Bla Blsdsadsadsada 2",  LocalDate.parse("2020-01-01"),"Bero",true));
//            posts.add(new BlogPost(2,"Second One","s Bla 2",  LocalDate.now(),"Perica",true));
//            posts.add(new BlogPost(3,"Bla bla","xxxxxxxx",  LocalDate.parse("2019-12-01"),"Janko",true));
//
//            return posts;
           return Arrays.asList(Objects.requireNonNull(new RestTemplate().getForEntity(API_BLOG_POST_URL, BlogPost[].class).getBody()));
        } catch (Exception ex) {
            LogErrorMessage(ex);
            return new ArrayList<>();
        }
    }

    @Override
    public BlogPost GetById(long blogPostId) {
        try {
            return Objects.requireNonNull(new RestTemplate().getForEntity(API_BLOG_POST_URL + "/" + blogPostId, BlogPost.class).getBody());
        } catch (Exception ex) {
            LogErrorMessage(ex);
            return null;
        }
    }

    @Override
    public BlogPost Create(BlogPost blogPost) {
        try {
            return Objects.requireNonNull(new RestTemplate().postForEntity(API_BLOG_POST_URL, blogPost, BlogPost.class).getBody());
        } catch (Exception ex) {
            LogErrorMessage(ex);
            return null;
        }
    }

    @Override
    public boolean Update(BlogPost blogPost) {
        try {
            new RestTemplate().put(API_BLOG_POST_URL + "/" + blogPost.getId(), blogPost);
            return true;
        } catch (Exception ex) {
            LogErrorMessage(ex);
            return false;
        }
    }

    @Override
    public boolean Delete(long blogPostId) {
        try {
            new RestTemplate().delete(API_BLOG_POST_URL + "/" + blogPostId);
            return true;
        } catch (Exception ex) {
            LogErrorMessage(ex);
            return false;
        }
    }

    private void LogErrorMessage(Exception exception){
        System.out.println(exception.getMessage());
    }
}
