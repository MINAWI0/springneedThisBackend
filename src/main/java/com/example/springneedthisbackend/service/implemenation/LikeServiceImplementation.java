package com.example.springneedthisbackend.service.implemenation;



import com.example.springneedthisbackend.exception.RequestException;
import com.example.springneedthisbackend.exception.UserException;
import com.example.springneedthisbackend.model.AppUser;
import com.example.springneedthisbackend.model.Like;
import com.example.springneedthisbackend.model.Request;
import com.example.springneedthisbackend.repo.LikeRepository;
import com.example.springneedthisbackend.repo.RequestRepository;
import com.example.springneedthisbackend.service.LikeService;
import com.example.springneedthisbackend.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LikeServiceImplementation implements LikeService {

    @Autowired
    private LikeRepository likeRepsitory;
    @Autowired
    private RequestService requestService;
    @Autowired
    private RequestRepository requestRepository;

    @Override
    public Like likeRequest(Long requestId, AppUser appUser) throws UserException, RequestException {
            Like isLikeExistOnRequest = likeRepsitory.isLikeExiste(appUser.getId(),requestId);
            if(isLikeExistOnRequest != null){
                likeRepsitory.deleteById(isLikeExistOnRequest.getId());
                return isLikeExistOnRequest;
            }
            Request request = requestService.findRequestById(requestId);
            Like like = new Like();
            like.setRequest(request);
            like.setUser(appUser);
            Like savedLike = likeRepsitory.save(like);
            request.getLikes().add(savedLike);
            requestRepository.save(request);

        return savedLike;
    }

    @Override
    public List<Like> getAllLikes(Long requestId) throws RequestException {
        Request request = requestService.findRequestById(requestId);
        List<Like> likes = likeRepsitory.findByRequestId(requestId);
        return likes;
    }
}
