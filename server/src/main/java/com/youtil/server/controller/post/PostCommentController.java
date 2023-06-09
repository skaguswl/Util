package com.youtil.server.controller.post;

import com.youtil.server.common.CommonResponse;
import com.youtil.server.dto.post.PostCommentResponse;
import com.youtil.server.dto.post.PostCommentSaveRequest;
import com.youtil.server.dto.post.PostCommentUpdateRequest;
import com.youtil.server.security.CurrentUser;
import com.youtil.server.security.UserPrincipal;
import com.youtil.server.service.post.PostCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/comments")
@Api(tags = {"포스트 댓글 컨트롤러"})
public class PostCommentController {

    @Autowired
    PostCommentService postCommentService;


    @ApiOperation(value = "해당 게시물의 댓글 리스트 조회", notes = "게시물 postId를 입력받은 후 게시물 목록을 조회한다. (최신 날짜순)")
    @GetMapping("/{postId}")
    public ResponseEntity<CommonResponse> findPostCommentList(@PathVariable Long postId,
                                                              @RequestParam(required=false, defaultValue = "1") int offset,
                                                              @RequestParam(value = "size", required = false, defaultValue = "10") int size){


        return ResponseEntity.ok().body(CommonResponse.of(
                HttpStatus.OK, "해당 게시물의 댓글 목록 조회 성공", postCommentService.findPostCommentList(postId, offset, size)));
    }

    @ApiIgnore
    @ApiOperation(value = "댓글 등록", notes = "댓글을 등록한다")
    @PostMapping("/{postId}")
    public ResponseEntity<CommonResponse> createPost(@ApiIgnore @CurrentUser UserPrincipal user, @PathVariable Long postId, @RequestBody @Valid PostCommentSaveRequest request) throws Exception {
        return ResponseEntity.ok().body(CommonResponse.of(
                HttpStatus.CREATED, "등록 성공", postCommentService.createPostComment(user.getId(), postId, request)));
    }
    @ApiIgnore
    @ApiOperation(value = "댓글 수정", notes = "댓글을 수정한다")
    @PutMapping("/{commentId}")
    public ResponseEntity<CommonResponse> updatepost(@ApiIgnore @CurrentUser UserPrincipal user, @PathVariable Long commentId,
                                                     @RequestBody @Valid PostCommentUpdateRequest request) throws Exception {
        return ResponseEntity.ok().body(CommonResponse.of(
                HttpStatus.CREATED, "수정 성공", postCommentService.updatePostComment(user.getId(), commentId, request)));
    }
    @ApiIgnore
    @ApiOperation(value = "댓글 삭제", notes = "단일 댓글을 삭제한다")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommonResponse> deletepost(@ApiIgnore @CurrentUser UserPrincipal user, @PathVariable Long commentId) {
        return ResponseEntity.ok().body(CommonResponse.of(
                HttpStatus.NO_CONTENT, "삭제 성공", postCommentService.deletePostComment(user.getId(), commentId)));
    }
}
