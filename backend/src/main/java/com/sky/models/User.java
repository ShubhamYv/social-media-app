package com.sky.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "USER")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(unique = true)
	private String email;

	private String password;

	private String gender;

	@ElementCollection
	@CollectionTable(name = "FOLLOWERS", joinColumns = @JoinColumn(name = "USER_ID"))
	@Column(name = "FOLLOWER_ID")
	private List<Integer> followers = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "FOLLOWINGS", joinColumns = @JoinColumn(name = "USER_ID"))
	@Column(name = "FOLLOWING_ID")
	private List<Integer> followings = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Post> savedPost = new ArrayList<>();

}
