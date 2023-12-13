package io.philo.app;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class JEntityDto {

    private final String description;

    @QueryProjection
    public JEntityDto(JEntity entity) {
        this.description = entity.getDescription();
    }
}
