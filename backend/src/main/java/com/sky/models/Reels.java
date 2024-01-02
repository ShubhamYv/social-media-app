package com.sky.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Reels {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REELS_ID")
	private Integer id;

	private String title;
	private String video;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

}
