package com.michelet.user.application.port;

import java.util.UUID;

public interface ReservationPort {
    boolean hasActiveReservation(UUID userId);
}
