package com.eod.sitree.project.ui.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FocusPointResponseDto {

    private final List<String> focusPoints;
}
