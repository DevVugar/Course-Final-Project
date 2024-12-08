package com.example.finalproject.model.dto.request;

import lombok.Data;

@Data
public class ResetPasswordDto {
  private Long userId;
  private String oldPassword;
  private String newPassword;
  private String retryNewPassword;

}
