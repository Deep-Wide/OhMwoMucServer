package net.ohmwomuc.domain.muamuc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.ohmwomuc.core.exception.CustomException;
import net.ohmwomuc.core.exception.CustomExceptionCode;
import net.ohmwomuc.core.security.dto.User;
import net.ohmwomuc.core.security.service.SecurityService;
import net.ohmwomuc.domain.muamuc.dto.Muamuc;
import net.ohmwomuc.domain.muamuc.service.MuamucService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;

@RequestMapping("/api/muamuc")
@RestController
@RequiredArgsConstructor
@Tag(name = "Muamuc", description = "Muamuc컨트롤러")
public class MuamucController {

    private final MuamucService muamucService;
    private final SecurityService securityService;

    @GetMapping("")
    @Operation(summary = "Muamuc 게시물 태그별 전체 리스트 조회")
    public ResponseEntity<List<Muamuc.DomainResponse>> getMuamucList(@RequestParam(name = "tag", required = true) Integer tagId, @RequestParam(name = "searchKeyword", required = false) String searchKeyword) {
        List<Muamuc.Domain> muamucList = muamucService.getMuamucList(Muamuc.Condition.builder()
                .tag(net.ohmwomuc.domain.muamuc.dto.Tag.getById(tagId).orElse(null))
                .searchKeyword(searchKeyword)
                .build());

        return ResponseEntity.ok(muamucList.stream()
                .map(Muamuc.Domain::toResponse)
                .collect(Collectors.toList()));
    }

    @PostMapping("")
    @Operation(summary = "Muamuc 게시물 작성")
    public ResponseEntity<Muamuc.DomainResponse> addMuamuc(@RequestBody Muamuc.DomainRequest muamuc) {
//        securityService.getLoginUser().orElseThrow(() -> new CustomException(CustomExceptionCode.USER_UNAUTHORIZED));
        Muamuc.DomainResponse response = muamucService.addMuamucDomain(muamuc.toDomain()).toResponse();

        return ResponseEntity.status(CREATED).body(response);
    }
}
