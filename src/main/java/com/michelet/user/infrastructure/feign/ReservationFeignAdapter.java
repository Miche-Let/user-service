package com.michelet.user.infrastructure.feign;

import com.michelet.user.application.port.ReservationPort;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationFeignAdapter implements ReservationPort {
    private final ReservationFeignClient reservationFeignClient;

    @Override
    public boolean hasActiveReservation(UUID userId) {
        return reservationFeignClient.hasActiveReservation(userId).exists();
    }
}
