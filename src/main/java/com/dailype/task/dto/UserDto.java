package com.dailype.task.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class UserDto {
    @NotBlank(message="UserName must not be empty")
    private String userName;
    @NotNull
    @Pattern(regexp="(\\+91|0)[0-9]{10}", message = "Invalid Mobile Number")
    private String mobNum;
    @NotNull(message = "PAN number")
    @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]$", message = "Invalid PAN number")
    private String panNum;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
