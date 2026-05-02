package com.itravel.platform.modules.booking.application.port.out.event;

public interface BookingEventPublisher {
    void publish(Object event);
}
