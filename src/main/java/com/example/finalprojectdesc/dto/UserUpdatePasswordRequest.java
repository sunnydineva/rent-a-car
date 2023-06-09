package com.example.finalprojectdesc.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdatePasswordRequest {

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE, message = "Email should have proper email format")
    @Schema(description = "The user's email address.", example = "lillyivanova@gmail.com")
    private String email;

    @NotBlank(message = "Current password is required field")
    @Schema(description = "The user's current password.", example = "oldPassword!1")
    private String currentPassword;

    @NotBlank(message = "New password is required")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,}$", message = """
            Your new password should have:
            at least one upper case English letter
            at least one lower case English letter
            at least one digit
            at least one special character
            minimum eight in length""")
    @Size(min = 8, max = 100)
    @Schema(description = "The user's new password.", example = "newPassword123!")
    private String newPassword;

    @NotBlank(message = "Please reenter your new password")
    private String newPasswordConfirm;
}

