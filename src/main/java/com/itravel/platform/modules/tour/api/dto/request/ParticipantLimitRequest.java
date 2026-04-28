package com.itravel.platform.modules.tour.api.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParticipantLimitRequest{
    Integer minParticipants;
    Integer maxParticipants;
}