package com.example.springneedthisbackend.request;

import com.example.springneedthisbackend.model.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestReplyOffre {
    private String content;
    private Long requestId;
    private LocalDateTime createdAt;
    private String image;
    private Float price;
    private String title;
    private String link;
    private Double latitude;
    private Double longitude;

}
