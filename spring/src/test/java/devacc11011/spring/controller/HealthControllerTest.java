package devacc11011.spring.controller;

import devacc11011.spring.dto.HealthResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HealthControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void health_응답이_정상적으로_반환된다() {
		// when
		ResponseEntity<HealthResponse> response = restTemplate.getForEntity(
			"/api/health",
			HealthResponse.class
		);

		// then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getStatus()).isEqualTo("UP");
		assertThat(response.getBody().getVersion()).isEqualTo("1.0.0");
		assertThat(response.getBody().getTimestamp()).isGreaterThan(0);
	}

	@Test
	void health_응답에_타임스탬프가_포함된다() {
		// given
		long beforeRequest = System.currentTimeMillis();

		// when
		ResponseEntity<HealthResponse> response = restTemplate.getForEntity(
			"/api/health",
			HealthResponse.class
		);

		// then
		long afterRequest = System.currentTimeMillis();
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getTimestamp())
			.isGreaterThanOrEqualTo(beforeRequest)
			.isLessThanOrEqualTo(afterRequest);
	}
}
