package com.eod.sitree.project.ui.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FocusPointUpdateResponseDto {

    private final List<String> focusPoints;
}
