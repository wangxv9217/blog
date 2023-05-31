package blog.example.modle.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name="blog")



public class BlogEntity {
	
	public BlogEntity(String blogTitle, String fileName, String blogDate, String message, Long userId) {
		this.blogTitle = blogTitle;
		this.blogImage = fileName;
		this.blogDate = blogDate;
		this.message = message;
		this.userId = userId;
	}
	
	@Id
	@Column(name="blog_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long blogId;
	
	@NonNull
	@Column(name="blog_image")
	private String blogImage;
	
	@NonNull
	@Column(name="blog_title")
	private String blogTitle;
	
	@NonNull
	@Column(name="blog_date")
	private String blogDate;
	
	@NonNull
	@Column(name="message")
	private String message;
	
	@Column(name="user_id")
	private Long userId;
		
}
