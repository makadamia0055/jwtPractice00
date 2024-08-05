package org.zerock.api00.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class APIUser {

    @Id
    private String mid;
    private String mpw;

    public void changePw(String mpw){
        this.mpw = mpw;
    }
}
