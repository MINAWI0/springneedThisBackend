package com.example.springneedthisbackend.controller;


import com.example.springneedthisbackend.dto.RequestDto;
import com.example.springneedthisbackend.dto.mapper.RequestMapper;
import com.example.springneedthisbackend.exception.RequestException;
import com.example.springneedthisbackend.exception.UserException;
import com.example.springneedthisbackend.model.AppUser;
import com.example.springneedthisbackend.model.Request;
import com.example.springneedthisbackend.request.RequestReplyOffre;
import com.example.springneedthisbackend.response.ApiResponse;
import com.example.springneedthisbackend.service.AppUserService;
import com.example.springneedthisbackend.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/request")
public class RequestController {
    @Autowired
    private RequestService requestService;
    @Autowired
    private AppUserService appUserService;


    @PostMapping("/create_request")
    public ResponseEntity<RequestDto> createRequest
            (@RequestBody Request request , @RequestHeader("Authorization") String jwt )
            throws UserException, RequestException {
        AppUser appUser = appUserService.findUserProfileByJwt(jwt);
        System.out.println("appUser = " + appUser);
        Request req = requestService.createRequest(request,appUser);
        RequestDto requestDto = RequestMapper.torequestDto(req,appUser);
        System.out.println(new ResponseEntity<>(requestDto, HttpStatus.CREATED));
        return new ResponseEntity<>(requestDto, HttpStatus.CREATED);
    }
    @PostMapping("/reply_request")
    public ResponseEntity<RequestDto> createOffre
            (@RequestBody RequestReplyOffre replyOffre , @RequestHeader("Authorization") String jwt )
            throws UserException, RequestException {
        AppUser appUser = appUserService.findUserProfileByJwt(jwt);
        Request req = requestService.createdReply(replyOffre,appUser);
        RequestDto requestDto = RequestMapper.torequestDto(req,appUser);
        return new ResponseEntity<>(requestDto, HttpStatus.CREATED);

    }
    @PutMapping("/{requestId}/re_request")
    public ResponseEntity<RequestDto> reRequest
            (@PathVariable Long requestId, @RequestHeader("Authorization") String jwt )
            throws UserException, RequestException {
        AppUser appUser = appUserService.findUserProfileByJwt(jwt);
        Request req = requestService.reRequest(requestId,appUser);
        RequestDto requestDto = RequestMapper.torequestDto(req,appUser);
        return new ResponseEntity<>(requestDto, HttpStatus.OK);

    }
    @GetMapping("/get/{requestId}")
    public ResponseEntity<RequestDto> findRequestById
            (@PathVariable Long requestId, @RequestHeader("Authorization") String jwt )
            throws UserException, RequestException {
        AppUser appUser = appUserService.findUserProfileByJwt(jwt);
        Request req = requestService.findRequestById(requestId);
        RequestDto requestDto = RequestMapper.torequestDto(req,appUser);
        return new ResponseEntity<>(requestDto, HttpStatus.OK);

    }
    @DeleteMapping("/{requestId}")
    public ResponseEntity<ApiResponse> deleteRequest
            (@PathVariable Long requestId, @RequestHeader("Authorization") String jwt )
            throws UserException, RequestException {
        AppUser appUser = appUserService.findUserProfileByJwt(jwt);
        requestService.deleteRequestById(requestId,appUser.getId());
        ApiResponse response = new ApiResponse("request has been deleted succefulyy" , true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<RequestDto>> getAllRequests
            (@RequestHeader("Authorization") String jwt )
            throws UserException, RequestException {
        AppUser appUser = appUserService.findUserProfileByJwt(jwt);
        System.out.println(appUser);
        List<Request> reqs = requestService.findAllRequest();
        List<RequestDto> requestDtos = RequestMapper.toRequestDtos(reqs,appUser);
        return new ResponseEntity<>(requestDtos, HttpStatus.OK);
    }
    @GetMapping("/Appuser/{userId}")
    public ResponseEntity<List<RequestDto>> getAllUserRequests
            (@RequestHeader("Authorization") String jwt , @PathVariable Long userId)
            throws UserException, RequestException {
        AppUser appUser = appUserService.findUserProfileByJwt(jwt);
        List<Request> reqs = requestService.getAppUserRequest(appUser);
        List<RequestDto> requestDtos = RequestMapper.toRequestDtos(reqs,appUser);
        return new ResponseEntity<>(requestDtos, HttpStatus.OK);
    }
    @GetMapping("/Appuser/{userId}/likes")
    public ResponseEntity<List<RequestDto>> findRequestLikedByUser
            (@RequestHeader("Authorization") String jwt , @PathVariable Long userId)
            throws UserException, RequestException {
        AppUser appUser = appUserService.findUserProfileByJwt(jwt);
        List<Request> reqs = requestService.findByLikesContainsAppUser(appUser);
        List<RequestDto> requestDtos = RequestMapper.toRequestDtos(reqs,appUser);
        return new ResponseEntity<>(requestDtos, HttpStatus.OK);
    }




}
