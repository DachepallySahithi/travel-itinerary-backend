package com.travel.itinerary.service;

import com.travel.itinerary.dto.ReviewRequest;
import com.travel.itinerary.dto.ReviewResponse;
import com.travel.itinerary.model.entity.Review;
import com.travel.itinerary.model.entity.User;
import com.travel.itinerary.repository.ReviewRepository;
import com.travel.itinerary.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    public List<ReviewResponse> getAllReviews()
    {
        List<Review> reviews=reviewRepository.findAllByOrderByCreatedAtDesc();
        return mapToResponse(reviews);
    }
    public ReviewResponse submitResponse(ReviewRequest request, String userName){
        Review review=new Review();
        review.setReviewerName(request.getReviewerName());
        review.setRating(request.getRating());
        User user=userRepository.findByUsername(userName).orElse(null);
        if(user==null)
            throw new RuntimeException();
        review.setComment(request.getComment());
        review.setUser(user);
        review.setTitle(request.getTitle());
        review.setDestination(request.getDestination());
        Review review1=reviewRepository.save(review);
        return extracted(review1);
    }
    public List<ReviewResponse> mapToResponse(List<Review> reviews){
        List<ReviewResponse> reviewResponses=new ArrayList<>();
        for (Review review:reviews){
            reviewResponses.add(extracted(review));
        }
        return reviewResponses;
    }

    private ReviewResponse extracted(Review review) {
        ReviewResponse reviewResponse= new ReviewResponse();
        reviewResponse.setReviewerName(review.getReviewerName());
        reviewResponse.setId(review.getId());
        reviewResponse.setComment(review.getComment());
        reviewResponse.setRating(review.getRating());
        reviewResponse.setDestination(review.getDestination());
        reviewResponse.setCreatedAt(review.getCreatedAt());
        reviewResponse.setTitle(reviewResponse.getTitle());
        return reviewResponse;
    }
}