package com.bangle.domain.member.dto;

import com.bangle.domain.member.entity.Member;

public record MemberResponse(
	String nickname,
	float dust,
	String email,
	String privateKey,
	String role,
	String userId
) {

	public MemberResponse(Member member) {
		this(member.getNickname(), member.getDust(),member.getEmail(),member.getPrivateKey(),member.getRoles(),member.getUserId());
	}
}