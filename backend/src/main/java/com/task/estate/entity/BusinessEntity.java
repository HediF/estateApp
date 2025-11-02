package com.task.estate.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The BusinessEntity class represents common attributes for entities in the
 * application.
 */
@MappedSuperclass
@Getter
@Setter
public abstract class BusinessEntity implements Serializable {

	/**
	 * The unique identifier for the entity.
	 */
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * The timestamp when the entity was created.
	 */
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE", nullable = false)
	@NotNull
	private Date createDate;

	/**
	 * The timestamp when the entity was last modified.
	 */
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFY_DATE", nullable = false)
	@NotNull
	private Date modifyDate;

	@PrePersist
	public void prePersistCreatedAt() {
		this.createDate = new Date();
	}

}
