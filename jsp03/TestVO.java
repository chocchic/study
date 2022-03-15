package web.jsp03.bean;

import java.sql.Timestamp;

// 회원 한명의 정보를 모두 담을 수 있는 클래스 생성 ( 캡술화 )
// 회원 한 명의 데이터를 낱개의 변수에 담아서 데이터를 보내는 것이 아니라 TestVO 객체 안에 정리해 담아서 객체로 주고 받기 위해
public class TestVO {
	private String id;
	private String pw;
	private String email;
	private String gender;
	private Integer age;
	private String music;
	private String movies;
	private String sports;
	private String travel;
	private String job;
	private String bio;
	private Timestamp reg;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getMusic() {
		return music;
	}
	public void setMusic(String music) {
		this.music = music;
	}
	public String getMovies() {
		return movies;
	}
	public void setMovies(String movies) {
		this.movies = movies;
	}
	public String getSports() {
		return sports;
	}
	public void setSports(String sports) {
		this.sports = sports;
	}
	public String getTravel() {
		return travel;
	}
	public void setTravel(String travel) {
		this.travel = travel;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public Timestamp getReg() {
		return reg;
	}
	public void setReg(Timestamp reg) {
		this.reg = reg;
	}
	 
}
