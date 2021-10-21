package com.tech.userapi.repository.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String phone;
    @Column(name = "city_code")
    private String cityCode;
    @Column(name = "country_code")
    private String countryCode;
    @Getter(onMethod_ = @JsonIgnore)
    @ToString.Exclude
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;
}
