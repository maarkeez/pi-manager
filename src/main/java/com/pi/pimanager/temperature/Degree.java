package com.pi.pimanager.temperature;

import lombok.*;

import static lombok.AccessLevel.PRIVATE;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE)
class Degree {

    private double degrees;

}
