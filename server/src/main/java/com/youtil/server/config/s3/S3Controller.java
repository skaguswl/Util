package com.youtil.server.config.s3;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/upload")
public class S3Controller {

    private final S3Uploader s3Uploader;

    @ApiIgnore
    @ApiOperation(value = "포스트에서 파일, 섬네일 업로드", notes="파일을 업로드하고 주소를 반환한다")
    @PostMapping("/posts")
    public String uploadPostFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
       return s3Uploader.upload(multipartFile, "static/post");
    }

    @ApiIgnore
    @ApiOperation(value = "회고록에서 파일 업로드", notes="파일을 업로드하고 주소를 반환한다")
    @PostMapping("/reviews")
    public String uploadReviewFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        return s3Uploader.upload(multipartFile, "static/review");
    }

    @ApiIgnore
    @ApiOperation(value = "프로필 이미지 업로드", notes = "파일을 업로드하고 주소를 반환한다.")
    @PostMapping("/users")
    public String uploadProfileFile(@RequestParam("file") MultipartFile multipartFile) throws IOException{
        return s3Uploader.upload(multipartFile, "static/user");
    }

    @ApiIgnore
    @ApiOperation(value = "목표 이미지 업로드", notes = "파일을 업로드하고 주소를 반환한다.")
    @PostMapping("/goals")
    public String uploadGoalFile(@RequestParam("file") MultipartFile multipartFile) throws IOException{
        return s3Uploader.upload(multipartFile, "static/goal");
    }

    @ApiIgnore
    @ApiOperation(value = "파일 삭제", notes="풀주소를 주면 해당 파일을 삭제한다")
    @DeleteMapping()
    public String deleteFile(@RequestParam("file") String path) throws UnsupportedEncodingException {
        String source = URLDecoder.decode(path.replace("https://utilbucket.s3.ap-northeast-2.amazonaws.com/", "") , "UTF-8");
        s3Uploader.delete(source);
        return "삭제 완료";
    }
}
