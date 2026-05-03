package com.itravel.platform.modules.booking.application.port.out.booking.event;

public interface BookingEventPublisher {
    void publish(Object event);
}
