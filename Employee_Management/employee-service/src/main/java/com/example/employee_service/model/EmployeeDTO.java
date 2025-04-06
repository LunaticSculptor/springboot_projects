package com.example.employee_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Integer employeeId;
    private String employeeName;
    private ManagerDTO manager;
    private List<ReviewDTO> reviews;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ManagerDTO {
        private Integer managerId;
        private String managerName;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewDTO {
        private String reviewerName;
        private BigDecimal rating;
        private String comments;
        private LocalDateTime reviewDate;
    }
}
