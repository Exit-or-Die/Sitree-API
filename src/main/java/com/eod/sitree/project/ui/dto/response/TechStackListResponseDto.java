package com.eod.sitree.project.ui.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TechStackListResponseDto {
    private final List<String> techStacks;
}
