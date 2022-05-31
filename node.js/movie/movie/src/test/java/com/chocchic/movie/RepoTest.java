package com.chocchic.movie;

import java.util.UUID;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.chocchic.movie.model.Member;
import com.chocchic.movie.model.Movie;
import com.chocchic.movie.model.MovieImage;
import com.chocchic.movie.repository.MemberRepository;
import com.chocchic.movie.repository.MovieImageRepository;
import com.chocchic.movie.repository.MovieRepository;

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
	
	@Test
	//@Transactional
	//@Commit
	public void insertMember() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			Member member = Member.builder().email("test"+i+"@aaa.com").pw("1234").nickname("tt"+i).build();
			mem.save(member);
		});
		
	}
}
