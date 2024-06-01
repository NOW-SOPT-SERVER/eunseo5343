package org.sopt.spring.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.spring.common.auth.PrincipalHandler;
import org.sopt.spring.common.dto.SuccessMessage;
import org.sopt.spring.common.dto.SuccessStatusResponse;
import org.sopt.spring.service.BlogService;
import org.sopt.spring.service.dto.BlogCreateRequest;
import org.sopt.spring.service.dto.BlogTitleUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

//    @PostMapping("/blog")
//    public ResponseEntity<SuccessStatusResponse> createBlog(
//            @RequestHeader(name = "memberId") Long memberId,
//            @RequestBody BlogCreateRequest blogCreateRequest
//    ) {
//        return ResponseEntity.status(HttpStatus.CREATED).header(
//                        "Location",
//                        blogService.create(memberId, blogCreateRequest))
//                .body(SuccessStatusResponse.of(SuccessMessage.BLOG_CREATE_SUCCESS));
//    }

    private final PrincipalHandler principalHandler;

//    @PostMapping("/blog")
//    public ResponseEntity createBlog(
//            BlogCreateRequest blogCreateRequest
//    ) {
//        return ResponseEntity.created(URI.create(blogService.create(
//                principalHandler.getUserIdFromPrincipal(), blogCreateRequest))).build();
//    }

    @PostMapping("/blog")
    public ResponseEntity createBlog(
            @ModelAttribute BlogCreateRequest blogCreateRequest
    ) {
        return ResponseEntity.created(URI.create(blogService.create(
                principalHandler.getUserIdFromPrincipal(), blogCreateRequest))).build();
    }



    @PatchMapping("/blog/{blogId}/title")
    public ResponseEntity updateBlogTitle(
            @PathVariable Long blogId,
            @Valid @RequestBody BlogTitleUpdateRequest blogTitleUpdateRequest
    ) {
        blogService.updateTitle(blogId, blogTitleUpdateRequest);
        return ResponseEntity.noContent().build();
    }
}
