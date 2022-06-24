package com.choc.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LoginInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long logininfoid;
	private String email;
	private String address;
	private LocalDateTime regdate;
}