package com.iamneo.ecom.dto.request;

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
    private Long organizerEvent;
    private Long cid;
}
