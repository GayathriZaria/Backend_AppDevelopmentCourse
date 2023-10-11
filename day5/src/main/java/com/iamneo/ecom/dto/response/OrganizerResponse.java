package com.iamneo.ecom.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizerResponse {
    private Long pid;
    private String organizerName;
    private float organizerSalary;
    private Long organizerEvent;
}
