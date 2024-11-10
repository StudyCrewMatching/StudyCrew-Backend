package com.studycrew.studycrew_backend.domain.profile.user.dto;

import com.studycrew.studycrew_backend.domain.profile.user.User;
import com.studycrew.studycrew_backend.domain.tag.position.PositionType;
import lombok.AccessLevel;
import lombok.Builder;

public class UserSimpleProfileDto {

    private String username;

    private PositionType position;

    @Builder(access = AccessLevel.PRIVATE)
    private UserSimpleProfileDto(String username, PositionType position) {
        this.username = username;
        this.position = position;
    }

    public static UserSimpleProfileDto of(User user) {
        return UserSimpleProfileDto.builder()
                .username(user.getUsername())
//                .position(user.getPosition())
                .build();
    }
}
