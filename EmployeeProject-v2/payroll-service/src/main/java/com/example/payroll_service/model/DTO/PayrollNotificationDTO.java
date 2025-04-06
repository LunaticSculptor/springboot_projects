package com.example.payroll_service.model.DTO;

import com.example.payroll_service.model.Payroll;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class PayrollNotificationDTO {

    private Long payrollId;
    private Long employeeId;

//    public enum Status {
//        PENDING,
//        PROCESSED,
//        FAILED
//    }
//    @Enumerated(EnumType.STRING)
//    private Payroll.Status status;
    private Payroll.Status status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    private LocalDateTime payDate;
}
