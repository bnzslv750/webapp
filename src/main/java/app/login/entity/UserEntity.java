package app.login.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="user_t")
public class UserEntity {
	@Id
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;

}
