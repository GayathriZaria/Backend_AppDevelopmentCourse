package com.codeBeaters.event.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizerRequest {
    private String organizerName;
    private float organizerSalary;
    private Long yearsofExp;
    private String organizerDept;
    private String organizerSpec;
}
