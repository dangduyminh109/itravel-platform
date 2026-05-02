package com.itravel.platform.modules.booking.domain.passenger;

import com.itravel.platform.common.domain.enums.Gender;
import com.itravel.platform.modules.booking.domain.exception.InvalidDateOfBirthException;
import com.itravel.platform.modules.booking.domain.exception.InvalidGenderException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Passenger {
    PassengerId id;
    FullName fullName;
    LocalDate dateOfBirth;
    Gender gender;
    String identityNumber;
    PassengerType passengerType;

    private Passenger(
            PassengerId id,
            FullName fullName,
            LocalDate dateOfBirth,
            Gender gender,
            String identityNumber,
            PassengerType passengerType
    ) {
        this.id = id;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.identityNumber = identityNumber;
        this.passengerType = passengerType;
    }

    public static Passenger classify(
            FullName fullName,
            LocalDate dateOfBirth,
            Gender gender,
            String identityNumber,
            LocalDate departureDate
    ) {
        if (dateOfBirth == null) {
            throw new InvalidDateOfBirthException();
        }
        if (gender == null) {
            throw new InvalidGenderException();
        }
        if (departureDate == null) {
            throw new InvalidDateOfBirthException();
        }

        PassengerType type = classifyType(dateOfBirth, departureDate);
        return new Passenger(PassengerId.generate(), fullName, dateOfBirth, gender, identityNumber, type);
    }

    public static Passenger fromExisting(
            PassengerId id,
            FullName fullName,
            LocalDate dateOfBirth,
            Gender gender,
            String identityNumber,
            PassengerType passengerType
    ) {
        return new Passenger(id, fullName, dateOfBirth, gender, identityNumber, passengerType);
    }

    private static PassengerType classifyType(LocalDate dateOfBirth, LocalDate departureDate) {
        long ageAtDeparture = ChronoUnit.YEARS.between(dateOfBirth, departureDate);
        if (ageAtDeparture >= 12) {
            return PassengerType.ADULT;
        } else if (ageAtDeparture >= 2) {
            return PassengerType.CHILD;
        } else {
            return PassengerType.INFANT;
        }
    }

    public boolean matchesType(PassengerType expectedType) {
        return this.passengerType == expectedType;
    }
}
