package com.choc.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.choc.dto.LoginInfoDTO;
import com.choc.model.LoginInfo;
import com.choc.persistence.LoginInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginInfoServiceImpl implements LoginInfoService {
	private final LoginInfoRepository loginInfoRepository;
	
	@Override
	public Long registerLoginInfo(LoginInfoDTO dto) {
		//현재 날짜 및 시간을 설정
		dto.setRegdate(LocalDateTime.now());
		
		//위도와 경도 가져오기
		Double longitude = dto.getLongitude();
		Double latitude = dto.getLatitude();
		
		System.out.println("Service:" + dto);
		//위도 와 경도가 전송된 경우에만 작업을 수행
		if(longitude != null && latitude != null) {
			try {
				//전송받을 URL 생성
				URL url = new URL("https://dapi.kakao.com/v2/local/geo/coord2address.json?" + 
				"x=" + longitude + "&y=" + latitude + "&input_coord=WGS84");
				
				HttpURLConnection con = (HttpURLConnection)url.openConnection();
				con.setConnectTimeout(30000);
				con.setUseCaches(false);
				//헤더 설정 - 최근의 Open API는 아이디 와 비밀번호 대신에 
				//API Key를 이용해서 인증을 받습니다.
				con.setRequestProperty(
						"Authorization","KakaoAK 22a3921506dda23553e01dfcb1a67896");
				
				BufferedReader br = new BufferedReader(
						new InputStreamReader(con.getInputStream()));
				StringBuilder sb = new StringBuilder();
				while(true) {
					String line = br.readLine();
					if(line == null) {
						break;
					}
					sb.append(line + "\n");
				}
				br.close();
				con.disconnect();
				System.out.println("카카오에서 받은 데이터:" + sb.toString());
				
				if(sb.toString().length() > 0) {
					JSONObject object = new JSONObject(sb.toString());
					//검색된 데이터 개수 가져오기
					JSONObject meta = object.getJSONObject("meta");
					Integer total_count = meta.getInt("total_count");
					if(total_count > 0) {
						JSONArray documents = object.getJSONArray("documents");
						//첫번째 데이터 가져오기
						JSONObject first = documents.getJSONObject(0);
						try {
							JSONObject road_address = first.getJSONObject("road_address");
							String address_name = road_address.getString("address_name");
							System.out.println("주소:" + address_name);
							//주소 설정
							dto.setAddress(address_name);
						}catch(Exception e) {
							JSONObject address = first.getJSONObject("address");
							String address_name = address.getString("address_name");
							System.out.println("주소:" + address_name);
							//주소 설정
							dto.setAddress(address_name);
						}
					}
							
				}
			}catch(Exception e) {
				System.out.println("데이터 가져오기 에러" + e.getLocalizedMessage());
			}
		}
		//데이터 변환 후 삽입
		LoginInfo loginInfo = dtoToEntity(dto);
		loginInfoRepository.save(loginInfo);
		return loginInfo.getLogininfoid();
	}

}