package com.youtil.server.controller.tag;

import com.youtil.server.common.CommonResponse;
import com.youtil.server.common.exception.ResourceNotFoundException;
import com.youtil.server.dto.category.CategorySaveRequest;
import com.youtil.server.dto.tag.TagSaveRequest;
import com.youtil.server.dto.tag.TagUpdateRequest;
import com.youtil.server.security.CurrentUser;
import com.youtil.server.security.UserPrincipal;
import com.youtil.server.service.tag.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping("/tags")
@Api(tags = {"테그 컨트롤러"})
public class TagController {
    @Autowired
    TagService tagService;

    // 관심 테그
    @ApiOperation(value = "관심 테그 등록", notes = "관심 테그를 등록한다")
    @PostMapping("/likes")
    public ResponseEntity<CommonResponse> findOrCrateTagLike(@CurrentUser UserPrincipal user, @RequestBody @Valid TagSaveRequest request) throws Exception {

        return ResponseEntity.ok().body(CommonResponse.of(
                HttpStatus.CREATED, "등록 성공", tagService.findOrCrateTagLike(user.getId(), request)));
    }
    @ApiOperation(value = "관심 테그 조회", notes = "관심 테그를 조회한다")
    @GetMapping("/likes")
    public ResponseEntity<CommonResponse> getTagLike(@CurrentUser UserPrincipal user){
        return ResponseEntity.ok().body(CommonResponse.of(
                HttpStatus.OK, "관심 테그 조회 성공", tagService.getTagLike(user.getId())));
    }

    @ApiOperation(value = "관심 테그 삭제", notes = "관심 테그를 삭제한다")
    @DeleteMapping("/likes")
    public ResponseEntity<CommonResponse> deleteTagLike(@CurrentUser UserPrincipal user) {
        return ResponseEntity.ok().body(CommonResponse.of(
                HttpStatus.NO_CONTENT, "관심 테그 삭제 성공", tagService.deleteTagLike(user.getId())));
    }

    @ApiOperation(value = "관심 테그 수정", notes = "관심 테그를 수정한다")
    @PutMapping("/likes")
    public ResponseEntity<CommonResponse> updateTagLike(@CurrentUser UserPrincipal user, @RequestBody @Valid TagSaveRequest request) throws Exception {

        return ResponseEntity.ok().body(CommonResponse.of(
                HttpStatus.CREATED, "관심 테그 수정 성공", tagService.updateTagLike(user.getId(), request)));
    }
    // 그냥 테그
    @ApiOperation(value = "테그 조회", notes = "테그를 조회한다")
    @GetMapping
    public ResponseEntity<CommonResponse> getTag(){
        return ResponseEntity.ok().body(CommonResponse.of(
                HttpStatus.OK, "테그 조회 성공", tagService.getTag()));
    }

    @ApiOperation(value = "테그 삭제", notes = "테그를 삭제한다")
    @DeleteMapping("/{tagId}")
    public ResponseEntity<CommonResponse> deleteTag(@PathVariable Long tagId) {
        return ResponseEntity.ok().body(CommonResponse.of(
                HttpStatus.NO_CONTENT, "카테고리 삭제 성공", tagService.deleteTag(tagId)));
    }

    @ApiOperation(value = "테그 수정", notes = "해당 테그를 수정한다")
    @PutMapping("/{tagId}")
    public ResponseEntity<CommonResponse> updateTag(@PathVariable Long tagId,
                                                    @RequestBody @Valid TagUpdateRequest request) throws Exception {
        return ResponseEntity.ok().body(CommonResponse.of(
                HttpStatus.CREATED, "테그 수정 성공", tagService.updateTag(tagId, request)));
    }
}
