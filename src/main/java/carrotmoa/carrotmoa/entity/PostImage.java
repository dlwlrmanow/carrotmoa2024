package carrotmoa.carrotmoa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "post_image")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostImage extends BaseEntity {

    @Column(name = "post_id")
    private Long postId;

    @Column(name = "image_url")
    private String imageUrl;

}
