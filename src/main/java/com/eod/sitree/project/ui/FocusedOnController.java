package com.eod.sitree.project.ui;

import com.eod.sitree.auth.infra.resolver.MemberPrincipal;
import com.eod.sitree.auth.support.AuthNotRequired;
import com.eod.sitree.common.response.ResponseDto;
import com.eod.sitree.member.domain.model.Member;
import com.eod.sitree.project.service.FocusPointService;
import com.eod.sitree.project.ui.dto.request.FocusPointUpdateRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/focused-points")
public class FocusedOnController {

    private final FocusPointService focusPointService;

    @AuthNotRequired
    @GetMapping("/{focusPointId}")
    public ResponseDto<?> getFocusPointById(@PathVariable Long focusPointId) {
        var result = focusPointService.getFocusPointById(focusPointId);
        return ResponseDto.ok(result);
    }

    @PostMapping
    public ResponseDto<?> updateFocusPointById(
            @MemberPrincipal Member member,
            @RequestBody @Valid FocusPointUpdateRequestDto focusPointUpdateRequestDto
    ) {
        var result = focusPointService.updateFocusPoints(member, focusPointUpdateRequestDto);
        return ResponseDto.ok(result);
    }
}
