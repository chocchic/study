package com.choc.dto;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.choc.model.LoginInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfoDTO {
		private Long logininfoid;
		private String email;
		private Double longitude;
		private Double latitude;
		private String address;
		private LocalDateTime regdate;
}