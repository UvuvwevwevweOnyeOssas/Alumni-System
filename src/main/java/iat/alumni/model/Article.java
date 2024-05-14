package iat.alumni.model;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "articles")
public class Article {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "article_id")
	private Integer articleId;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "photo", nullable = true, length = 64)
	private String photo;

	@Transient
	private MultipartFile articleFile;
	
	@Transient
	public String getPhotosImagePath() {
		if (photo == null || articleId == null) return null;
		return "/article-photos/" + articleId + "/" + photo;
	}
	
	@NotBlank(message = "Description is required")
	@Size(max = 10000, message = "Description must be less than or equal to 10000 characters")
	@Column(name = "description")
	private String description;
	
	@Column(name = "date")
	private LocalDate date;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
	private List<Rating> ratings;
	
	@Transient
	public Double getAverageRating() {
	    if (ratings == null || ratings.isEmpty()) {
	        return 0.0;
	    }

	    List<Integer> ratingValues = ratings.stream()
	            .map(Rating::getRating)
	            .collect(Collectors.toList());

	    double sum = ratingValues.stream().mapToInt(Integer::intValue).sum();
	    return sum / ratingValues.size();
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public MultipartFile getArticleFile() {
		return articleFile;
	}

	public void setArticleFile(MultipartFile articleFile) {
		this.articleFile = articleFile;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	 
}
