package com.xu.yan.employee_management.model;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class User {
    private Long id;
    private String username;
    private String password;
}
