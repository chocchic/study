package com.chocchic.movie;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import com.chocchic.movie.model.Member;
import com.chocchic.movie.model.Movie;
import com.chocchic.movie.model.MovieImage;
import com.chocchic.movie.model.Review;
import com.chocchic.movie.repository.MemberRepository;
import com.chocchic.movie.repository.MovieImageRepository;
import com.chocchic.movie.repository.MovieRepository;
import com.chocchic.movie.repository.ReviewRepository;

@SpringBootTest
public class RepoTest {
	
	@Autowired
	private MovieRepository m;
	
	@Autowired
	private MovieImageRepository mm;
	
	//@Test
	@Transactional // 한번에 여러개의 데이터를 삽입하므로 모두 성공하거나 실패하도록 하기 위해 추가
	@Commit
	public void insertMovie() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			Movie movie = Movie.builder().title("Movie..."+i).build();
			m.save(movie);
			
			int count = (int)(Math.random() * 5) + 1;
			for(int j = 0; j < count; j++) {
				MovieImage movieImg = MovieImage.builder().uuid(UUID.randomUUID().toString()).movie(movie).imgName("test"+j+".jpg").build();
				mm.save(movieImg);
			}
		});
	}
	
	@Autowired
	private MemberRepository mem;
	
	//@Test
	@Transactional
	@Commit
	public void insertMember() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			Member member = Member.builder().email("test"+i+"@aaa.com").pw("1234").nickname("tt"+i).build();
			mem.save(member);
		});
		
	}
	
	@Autowired
	private ReviewRepository r;
	
	//@Test
	@Transactional
	@Commit
	public void insert() {
		IntStream.range(1, 200).forEach(i -> {
			// 존재하는 영화 번호
			Long mno = (long)(Math.random() * 100) + 1;
			// 회원 번호
			Long mid = (long)(Math.random() * 100) + 1;
			
			Movie movie = Movie.builder().mno(mno).build();
			Member member = Member.builder().mid(mid).build();
			
			Review movieReview = Review.builder().movie(movie).member(member).score((int)(Math.random() * 5) + 1).text("영화리뷰..."+i).build();
			
			r.save(movieReview);
					
		});
	}

	
	//@Test
	public void testListPage() {
		PageRequest pageRequest = PageRequest.of(0,10,Sort.by(Sort.Direction.DESC,"mno"));
		Page<Object []> result = m.getListPage(pageRequest);
		for(Object[] obj : result.getContent()) {
			System.out.println(Arrays.toString(obj));
		}
	}
	
	//@Test
	public void testGetMovieWithAll() {
		List<Object[]> result = m.getMovieAll(92L);
		for(Object[] objs : result) {
			System.out.println(Arrays.toString(objs));
		}
	}
	
	//@Test
	public void testGetMovieReviews() {
		Movie movie = Movie.builder().mno(9L).build();
		List<Review> list = r.findByMovie(movie);
		for(Review review : list){
			System.out.println(review.getMember().getEmail());
		}
	}
	
	// 2개의 삭제 구문을 사용하므로 @Transactional와 @Commit을 이용해서 동시에 수행되거나 아니면 하나도 되지 않도록 처리를 해주어야 합니다.
	@Test
	@Transactional
	@Commit
	public void testDeleteMember() {
		Long mid = 91L;
		Member member = Member.builder().mid(mid).build();
		r.deleteByMember(member);
		mem.deleteById(mid);
	}
}
