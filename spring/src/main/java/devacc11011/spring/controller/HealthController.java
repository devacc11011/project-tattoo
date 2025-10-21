package devacc11011.spring.controller;

import devacc11011.spring.dto.HealthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class HealthController {

	@GetMapping("/health")
	public ResponseEntity<HealthResponse> health() {
		HealthResponse response = new HealthResponse(
			"UP",
			System.currentTimeMillis(),
			"1.0.0"
		);
		return ResponseEntity.ok(response);
	}
}
