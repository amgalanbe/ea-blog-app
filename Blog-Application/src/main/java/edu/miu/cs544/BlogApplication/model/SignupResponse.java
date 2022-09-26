package edu.miu.cs544.BlogApplication.model;

import edu.miu.cs544.BlogApplication.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupResponse {
    private String message;
    private UserDto user;
}
