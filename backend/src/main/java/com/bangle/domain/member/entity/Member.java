package com.bangle.domain.member.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bangle.domain.author.entity.Author;
import com.bangle.domain.member.dto.JoinRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
@Table(indexes = {
	@Index(name = "idx_user_id", columnList = "user_id"),
	@Index(name = "idx_nickname", columnList = "nickname")
})
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	@Column(name = "public_key")
	private String publicKey;

	private String email;

	private String nickname;

	private String provider;

	private String roles;

	private int dust;

	@Column(name = "user_id")
	private String userId;

	@OneToOne(mappedBy = "member")
	private Author author;

	public List<String> getRoleList() {
		if (this.roles.length() > 0) {
			return Arrays.asList(this.roles.split(","));
		}
		return new ArrayList<>();
	}

	public void join(JoinRequest joinForm) {
		this.nickname = joinForm.nickname();
		this.publicKey = joinForm.publicKey();
	}

	public void joinAuthor(JoinRequest joinForm, Author saveAuthor) {
		this.nickname = joinForm.nickname();
		this.publicKey = joinForm.publicKey();
		this.roles = "ROLE_AUTHOR";
		this.author = saveAuthor;
	}

	public void changeNickname(String nickname) {
		this.nickname = nickname;
	}

	public void updateDust(int amount) { this.dust += amount; }

	public void buyBook(int cost) {
		if (dust < cost) {
			throw new IllegalArgumentException("잔액 부족");
		}
		dust -= cost;
	}

	public void publishBook(int cost) {
		if (dust < cost) {
			throw new IllegalArgumentException("잔액 부족");
		}
		dust -= cost;
	}

	public void upgradeAuthor() {
		this.roles = "ROLE_AUTHOR";
	}

	public boolean isAuthor() {
		return roles.equals("ROLE_AUTHOR");
	}
}
