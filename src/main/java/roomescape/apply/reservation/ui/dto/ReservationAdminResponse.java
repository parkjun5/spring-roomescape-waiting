package roomescape.apply.reservation.ui.dto;

import roomescape.apply.member.ui.dto.MemberResponse;
import roomescape.apply.reservation.domain.Reservation;
import roomescape.apply.reservationtime.domain.ReservationTime;
import roomescape.apply.reservationtime.ui.dto.ReservationTimeResponse;
import roomescape.apply.theme.domain.Theme;
import roomescape.apply.theme.ui.dto.ThemeResponse;

public record ReservationAdminResponse(
        long id,
        MemberResponse member,
        String date,
        ThemeResponse theme,
        ReservationTimeResponse time
) {
    public static ReservationAdminResponse from(Reservation reservation,
                                                Theme theme,
                                                ReservationTime reservationTime,
                                                MemberResponse memberResponse
    ) {
        return new ReservationAdminResponse(reservation.getId(), memberResponse, reservation.getReservationDate().value(),
                ThemeResponse.from(theme), ReservationTimeResponse.from(reservationTime));
    }
}
