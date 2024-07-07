package app.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="target_t")
public class TargetEntity {
	@Id
	@Column(name="username")
	private String username;
	@Column(name="target")
	private int target;
}
