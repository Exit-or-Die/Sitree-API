package com.eod.sitree.member.ui.dto.response;

import java.util.List;
import lombok.Getter;

@Getter
public class TechStackListResponseDto {

    private final List<String> techStacks;

    public TechStackListResponseDto(List<String> techStacks) {
        this.techStacks = techStacks;
    }
}
