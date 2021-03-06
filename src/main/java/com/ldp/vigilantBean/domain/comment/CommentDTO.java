package com.ldp.vigilantBean.domain.comment;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import lombok.*;

import javax.validation.constraints.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    Long productId;

    @NotBlank(message = "validation.comment.blank")
    @Size(max = 1000, message = "validation.comment.tooBig")
    String content;

    @Min(value = 0, message = "validation.comment.negativeStars")
    @Max(value = 10, message = "validation.comment.tooMuchStars")
    Byte stars;

}
