package blog.example.modle.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name="account")


public class UserEntity {
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	
	@NonNull
	@Column(name="user_name")
	private String userName;
	
	@NonNull
	@Column(name="password")
	private String password;
	
}
