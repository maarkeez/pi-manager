package com.pi.temperature.model;

import static lombok.AccessLevel.PRIVATE;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE)
public class Degree {

    private double degrees;

}
