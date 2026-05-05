package com.michelet.user.infrastructure.feign;

import com.michelet.user.application.port.ReservationPort;
import com.michelet.user.domain.exception.UserErrorCode;
import com.michelet.user.domain.exception.UserException;
import com.michelet.user.infrastructure.feign.dto.ReservationActiveResponse;
import feign.FeignException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationFeignAdapter implements ReservationPort {
    private final ReservationFeignClient reservationFeignClient;

    @Override
    public boolean hasActiveReservation(UUID userId) {
        try {
            ReservationActiveResponse response = reservationFeignClient.hasActiveReservation(userId);
            if (response == null) {
                throw new UserException(UserErrorCode.RESERVATION_SERVICE_UNAVAILABLE);
            }
            return response.exists();
        } catch (FeignException e) {
            throw new UserException(UserErrorCode.RESERVATION_SERVICE_UNAVAILABLE);
        }    }
}
