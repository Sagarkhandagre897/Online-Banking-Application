package Entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@EntityScan
@Data
public class Account {

	    private int account_id;
	    private int user_id;
	    private String account_number;
	    private String account_name;
	    private String account_type;
	    private BigDecimal balance;
	    private LocalDateTime created_at;
	    private LocalDateTime updated_at;
	
}
