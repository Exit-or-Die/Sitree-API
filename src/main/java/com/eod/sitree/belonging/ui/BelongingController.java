package com.eod.sitree.belonging.ui;

import com.eod.sitree.auth.support.AuthNotRequired;
import com.eod.sitree.belonging.domain.model.Belonging;
import com.eod.sitree.belonging.domain.model.BelongingWithPoint;
import com.eod.sitree.belonging.service.BelongingService;
import com.eod.sitree.belonging.ui.dto.request.BelongingRankingRequestDto;
import com.eod.sitree.belonging.ui.dto.response.BelongingRankingPageResponseDto;
import com.eod.sitree.belonging.ui.dto.response.BelongingRankingResponseDto;
import com.eod.sitree.common.response.ResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/belongings")
@RequiredArgsConstructor
public class BelongingController {

    private final BelongingService belongingService;

    @AuthNotRequired
    @GetMapping("/search")
    public ResponseDto<List<Belonging>> searchByName(@RequestParam(defaultValue = "") String name) {

        return ResponseDto.ok(belongingService.searchByName(name));
    }

    @AuthNotRequired
    @GetMapping("/ranking")
    public ResponseDto<BelongingRankingPageResponseDto> rankBelongings(BelongingRankingRequestDto request) {

        return ResponseDto.ok(new BelongingRankingPageResponseDto(belongingService.searchRanking(request)));
    }
}
