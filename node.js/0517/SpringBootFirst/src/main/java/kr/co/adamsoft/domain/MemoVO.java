package kr.co.adamsoft.domain;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemoVO {
	private Long mno;
	private String memoText;
}
