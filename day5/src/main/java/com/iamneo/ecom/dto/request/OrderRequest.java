package com.iamneo.ecom.dto.request;

import java.util.List;

import com.iamneo.ecom.dto.info.OrganizerInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private String orderAddress;
    private String paymentMode;
    private Long uid;
    private List<OrganizerInfo> organizers;
}
