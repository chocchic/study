package io.github.chocchic.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonVO {
	private Long num;
	private String name;
	private String nickname;
	private LocalDateTime birthTime;
}
