package com.ldp.vigilantBean.domain.comment;

import com.ldp.vigilantBean.domain.appUser.AppUser;
import com.ldp.vigilantBean.domain.product.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NamedQueries({
        @NamedQuery(
                name = Comment.GET_ALL_COMMENTS,
                query = "from Comment as comment " +
                        "where comment.product.productId = :productId"
        )
})
@Entity
@Table(name = "comment")
@Getter
@Setter
public class Comment {

   public static final String GET_ALL_COMMENTS = "Comment.getAllComments";

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "comment_id")
   private Long commentId;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "app_user_id")
   private AppUser appUser;

   @ManyToOne
   @JoinColumn(name = "product_id")
   private Product product;

   @Column(name = "content")
   private String content;

   @Column(name = "stars")
   private Byte stars;

   @Temporal(TemporalType.DATE)
   private Date creationDate;

}
