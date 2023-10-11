package com.iamneo.ecom.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iamneo.ecom.dto.info.OrganizerInfo;
import com.iamneo.ecom.dto.request.OrderRequest;
import com.iamneo.ecom.dto.response.CountResponse;
import com.iamneo.ecom.dto.response.OrderResponse;
import com.iamneo.ecom.model.Order;
import com.iamneo.ecom.model.OrderMapping;
import com.iamneo.ecom.model.Organizer;
import com.iamneo.ecom.model.User;
import com.iamneo.ecom.repository.OrderRepository;
import com.iamneo.ecom.repository.OrganizerRepository;
import com.iamneo.ecom.repository.UserRepository;
import com.iamneo.ecom.service.OrderService;
import com.iamneo.ecom.service.OrganizerService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepository;
    private final OrganizerRepository organizerRepository;
    private final OrderRepository orderRepository;
    private final OrganizerService organizerService;

    @Override
    public boolean saveOrder(OrderRequest request) {
        User user = userRepository.findByUid(request.getUid());
        List<OrganizerInfo> organizerInfoList = request.getOrganizers();
        long orderTotal = calculateOrderTotal(organizerInfoList);

        if (orderTotal <= 0) {
            throw new IllegalArgumentException("Order total must be greater than zero.");
        }

        try {
            Order order = createOrder(request, user, orderTotal, organizerInfoList);
            orderRepository.save(order);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private Order createOrder(OrderRequest request, User user, long orderTotal, List<OrganizerInfo> organizerInfoList) {
        Order order = Order.builder()
                .orderDate(new Date())
                .orderAddress(request.getOrderAddress())
                .paymentMode(request.getPaymentMode())
                .user(user)
                .orderTotal(orderTotal)
                .orderMappings(request.getOrganizers().stream()
                        .map(organizerRequest -> {
                            Organizer organizer = organizerService.getOrganizerModelId(organizerRequest.getPid());
                            if (organizer != null) {
                                return OrderMapping.builder().organizer(organizer).build();
                            } else {
                                throw new IllegalArgumentException("Invalid organizer ID: " + organizerRequest.getPid());
                            }
                        })
                        .collect(Collectors.toList()))
                .build();

        updateOrganizerQuantities(organizerInfoList);

        return order;
    }

    private List<Organizer> updateOrganizerQuantities(List<OrganizerInfo> organizerInfoList) {
        List<Organizer> updatedOrganizers = new ArrayList<>();

        for (OrganizerInfo organizerInfo : organizerInfoList) {
            Long organizerId = organizerInfo.getPid();
            Long quantity = organizerInfo.getEvent();

            Organizer organizer = getOrganizerOrThrow(organizerId);

            if (organizer.getOrganizerEvent() < quantity) {
                throw new IllegalArgumentException("Insufficient quantity for organizer ID: " + organizerId);
            }

            Organizer updatedOrganizer = createUpdatedOrganizer(organizer, quantity);
            organizerRepository.save(updatedOrganizer);
            updatedOrganizers.add(updatedOrganizer);
        }

        return updatedOrganizers;
    }

    private Organizer getOrganizerOrThrow(Long organizerId) {
        return organizerRepository.findById(organizerId)
                .orElseThrow(() -> new IllegalArgumentException("Organizer not found for ID: " + organizerId));
    }

    private Organizer createUpdatedOrganizer(Organizer organizer, Long quantity) {
        Organizer updatedOrganizer = new Organizer();
        updatedOrganizer.setPid(organizer.getPid());
        updatedOrganizer.setOrganizerName(organizer.getOrganizerName());
        updatedOrganizer.setOrganizerSalary(organizer.getOrganizerSalary());
        updatedOrganizer.setOrganizerEvent(organizer.getOrganizerEvent() - quantity);
        return updatedOrganizer;
    }

    private long calculateOrderTotal(List<OrganizerInfo> organizerInfoList) {
        return organizerInfoList.stream()
                .mapToLong(organizerInfo -> {
                    Organizer organizer = getOrganizerOrThrow(organizerInfo.getPid());
                    if (organizer.getOrganizerEvent() < organizerInfo.getEvent()) {
                        throw new IllegalArgumentException(
                                "Insufficient quantity for organizer ID: " + organizerInfo.getPid());
                    }
                    return (long) (organizer.getOrganizerSalary() * organizerInfo.getEvent());
                })
                .sum();
    }

    @Override
    public List<OrderResponse> getOrders(Long uid) {
        return convertToOrderResponse(orderRepository.findAllByUserUid(uid));
    }

    public List<OrderResponse> convertToOrderResponse(List<Order> orders) {
        return orders.stream()
                .map(order -> {
                    OrderResponse.OrderResponseBuilder builder = OrderResponse.builder()
                            .oid(order.getOid())
                            .orderDate(order.getOrderDate())
                            .orderTotal(order.getOrderTotal())
                            .orderAddress(order.getOrderAddress())
                            .paymentMode(order.getPaymentMode());

                    List<Organizer> organizers = order.getOrderMappings().stream()
                            .map(OrderMapping::getOrganizer)
                            .collect(Collectors.toList());

                    builder.organizers(organizers);

                    return builder.build();
                })
                .collect(Collectors.toList());
    }

    @Override
    public CountResponse orderCount() {
        long count = orderRepository.count();
        return CountResponse.builder().count(count).build();
    }

}
