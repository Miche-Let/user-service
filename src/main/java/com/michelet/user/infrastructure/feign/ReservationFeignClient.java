package com.michelet.user.infrastructure.feign;

import com.michelet.user.infrastructure.feign.dto.ReservationActiveResponse;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "reservation-service")
public interface ReservationFeignClient {
    @GetMapping("/internal/reservations/active")
    ReservationActiveResponse hasActiveReservation(@RequestParam("userId") UUID userId);
}
