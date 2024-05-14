package iat.alumni.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "users")
public class User {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "user_name")
	private String userName;

	@NotBlank(message = "Email is required")
	@Column(name = "email")
	private String email;
	
	@NotBlank(message = "Password is required")
	@Column(name = "password")
	private String password;
	
	@Column(name = "gender")
	private String gender;

	@DateTimeFormat(pattern="MM/dd/yyyy")
	@Past(message="Date of Birth should be Past")
	@Column(name = "birth_date")
	private LocalDate dateOfBirth;
	
	@Column(name = "major")
	private String major;
	
	@Column(name = "degree")
	private String degree;
	
	@Column(name = "job_title")
	private String jobTitle;
	
	@Column(name = "profile_picture")
	private String profilePic;
	
	@Column(name = "bio")
	private String bio;
	
	@Column(name = "education_background")
	private String education;
	
	@Column(name = "course_name")
	private String courseName;

	@Transient
	private MultipartFile file;
	
	@Transient
	public String getPhotosImagePath() {
		if (profilePic == null || userId == null) return null;
		
		return "/user-photos/" + userId + "/" + profilePic;
	}

	@OneToMany(mappedBy="user", fetch = FetchType.EAGER)
	List<Article> articles;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<JoinEvent> joinedEvents;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "user_role",
			joinColumns = @JoinColumn (name = "user_id"),
			inverseJoinColumns = @JoinColumn (name = "role_id")
			)
	private Set<Role> roles = new HashSet<>();
	
	public List<String> getRoles() {
		List<String> retList = new ArrayList<> ();
		roles.forEach(role -> retList.add(role.getName()));
		return retList;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public List<JoinEvent> getJoinedEvents() {
		return joinedEvents;
	}

	public void setJoinedEvents(List<JoinEvent> joinedEvents) {
		this.joinedEvents = joinedEvents;
	}

	public Set<Event> getParticipatedEvents() {
		return participatedEvents;
	}

	public void setParticipatedEvents(Set<Event> participatedEvents) {
		this.participatedEvents = participatedEvents;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@ManyToMany(mappedBy = "participants")
    private Set<Event> participatedEvents = new HashSet<>();
	   
}