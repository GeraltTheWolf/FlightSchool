package hr.rezek.flightschool.jobs;

import hr.rezek.flightschool.model.BlogPost;
import hr.rezek.flightschool.repository.BlogPostRepository;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

public class BlogPostJob extends QuartzJobBean {
    private Logger log = LoggerFactory.getLogger(BlogPostRepository.class);

    private final BlogPostRepository blogPostRepository;

    public BlogPostJob(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) {

        List<BlogPost> blogPosts = blogPostRepository.findAllByOrderByLastEditedOnAsc();

        if (blogPosts.size() > 0) {
            log.info("Total blog posts in database");
            log.info(String.valueOf(blogPosts.size()));
            log.info("Deleting last entered blog");

            BlogPost bp = blogPosts.get(0);
            log.info(bp.getTitle() + " - " + bp.getLastEditedOn());

            blogPostRepository.removeByUserId(bp.getUserId());

            log.info("Blog deleted");
        } else {
            log.info("No blog posts in database. Nothing to erase.");
        }
    }
}
