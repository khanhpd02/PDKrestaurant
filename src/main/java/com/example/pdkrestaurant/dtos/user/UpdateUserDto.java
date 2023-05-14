package com.example.pdkrestaurant.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDto {
  //  private String email;

    private String password;

    private String name;
    private String dienThoai;
//
//    private List<String> roles = new ArrayList<>();
}
