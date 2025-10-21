package devacc11011.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HealthResponse {
	private String status;
	private long timestamp;
	private String version;
}
