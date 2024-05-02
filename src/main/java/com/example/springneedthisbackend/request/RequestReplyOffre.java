package com.example.springneedthisbackend.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestReplyOffre {
    private String content;
    private Long requestId;
    private LocalDateTime createdAt;
    private String image;
    private Float price;
}
