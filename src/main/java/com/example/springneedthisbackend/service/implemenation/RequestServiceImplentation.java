package com.example.springneedthisbackend.service.implemenation;

import com.example.springneedthisbackend.exception.RequestException;
import com.example.springneedthisbackend.exception.UserException;
import com.example.springneedthisbackend.model.AppUser;
import com.example.springneedthisbackend.model.Like;
import com.example.springneedthisbackend.model.Request;
import com.example.springneedthisbackend.repo.LikeRepository;
import com.example.springneedthisbackend.repo.RequestRepository;
import com.example.springneedthisbackend.repo.UserRepository;
import com.example.springneedthisbackend.request.RequestReplyOffre;
import com.example.springneedthisbackend.service.AppUserService;
import com.example.springneedthisbackend.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class RequestServiceImplentation implements RequestService {

    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppUserService appUserService;

    @Override
    public Request createRequest(Request request, AppUser appUser) throws UserException, RequestException {
        System.out.println( "the user making request"+appUser);
        
        Request req = new Request();
        req.setContent(request.getContent());
        req.setCreatedAt(LocalDateTime.now());
        req.setImage(request.getImage());
        req.setPrice(request.getPrice());
        req.setLocation(request.getLocation());
        req.setMaxPrice(request.getMaxPrice());
        req.setPrice(request.getPrice());
        req.setLatitude(request.getLatitude());
        req.setLongitude(request.getLongitude());
        req.setMinPrice(request.getMinPrice());
        req.setCategory(request.getCategory());
        req.setAppUser(appUser);
        System.out.println("req.getAppUser() = " + req.getAppUser());
        req.setReplyType(false);
        req.setRequestType(true);
        req.setVideo(req.getVideo());
        req.setClosed(false);
        return requestRepository.save(req);
    }

    @Override
    public List<Request> findAllRequest() {
        return requestRepository.findAllByRequestTypeTrueOrderByCreatedAtDesc();
    }

    @Override
    public Request reRequest(Long RequestId, AppUser appUser) throws UserException, RequestException {
        Request request = findRequestById(RequestId);
        if (request.getReRequestUser().contains(appUser)) {
            request.getReRequestUser().remove(appUser);
        } else {
            request.getReRequestUser().add(appUser);
        }
        return requestRepository.save(request);
    }

    @Override
    public Request findRequestById(Long RequestId) throws RequestException {

        System.out.println("inside the find by request id !!!!! ");
        System.out.println(RequestId);

        if (RequestId == null) {
            throw new IllegalArgumentException("Request ID must not be null");
        }
        Request request = requestRepository.findById(RequestId).orElseThrow(() -> new RequestException("Request not found" + RequestId));
        return request;
    }

    @Override
    public void deleteRequestById(Long request, Long appUserId) throws RequestException, UserException {
        Request req = findRequestById(request);
        if (!appUserId.equals(req.getAppUser().getId())) {
            throw new UserException("enable de delete other user Request");
        }
        likeRepository.deleteAll(req.getLikes());
        req.setClosedBy(null);
        requestRepository.deleteById(req.getId());
        System.out.println(" =deltttettetetetttttttttttttttttttttttttttttttttttt");

    }

    @Override
    public Request removeFromReRequest(Long requestId, AppUser user) throws RequestException, UserException {
        return null;
    }

    @Override
    public Request createdReply(RequestReplyOffre offre, AppUser appUser) throws RequestException{
        System.out.println("inside created reply !!!!! ");
        Request replyFor = findRequestById(offre.getRequestId());
        System.out.println("replyFor hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh  = " + replyFor);
        System.out.println(offre.getRequestId());
        Request req = new Request();
        req.setContent(offre.getContent());
        req.setCreatedAt(LocalDateTime.now());
        req.setImage(offre.getImage());
        req.setTitle(offre.getTitle());
        req.setLink(offre.getLink());
        req.setLatitude(offre.getLatitude());
        req.setLongitude(offre.getLongitude());
        req.setPrice(offre.getPrice());
        req.setAppUser(appUser);
        req.setReplyType(true);
        req.setRequestType(false);
        req.setReplyFor(replyFor);

        Request savedReply = requestRepository.save(req); // it was replyfor for befor

        replyFor.getReplyRequests().add(savedReply);
        requestRepository.save(replyFor);
        //        req.getReplyRequests().add(savedReply);
        return replyFor;

    }
    @Override
    public List<Request>  getAppUserRequest(AppUser appUser){
//        System.out.println( "belive"+requestRepository.findByReRequestUserContainsOrAppUser_IdAndRequestTypeTrueOrderByCreatedAtDesc(appUser , appUser.getId()));
//        return requestRepository.findByReRequestUserContainsOrAppUser_IdAndRequestTypeTrueOrderByCreatedAtDesc(appUser , appUser.getId());
        return requestRepository.findAllRequestsByAppUserOrderByCreatedAtDesc(appUser);
    }

    @Override
    public  List<Request> findByLikesContainsAppUser(AppUser appUser){
        return requestRepository.findByLikesAppUser_Id(appUser.getId());
    }
    @Override
    public void closeRequest(Long requestId , Long sellerId) throws RequestException, UserException {
        Request request = findRequestById(requestId);
        AppUser appUser = appUserService.findUserById(sellerId);
        request.setClosed(true);
        request.setClosedBy(appUser);
        requestRepository.save(request);
    }
    @Override
    public List<Request> findAllClosedRequestsAndRepliesByUserId(Long userId) {
        return requestRepository.findAllClosedRequestsAndRepliesByUserId(userId);
    }
}
