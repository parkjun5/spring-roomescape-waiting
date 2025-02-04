package roomescape.support;

import roomescape.apply.reservation.domain.Reservation;
import roomescape.apply.reservation.ui.dto.ReservationAdminRequest;
import roomescape.apply.reservation.ui.dto.ReservationRequest;
import roomescape.apply.reservation.ui.dto.ReservationSearchParams;
import roomescape.apply.reservationtime.domain.ReservationTime;
import roomescape.apply.reservationtime.ui.dto.ReservationTimeRequest;
import roomescape.apply.theme.domain.Theme;
import roomescape.apply.theme.ui.dto.ThemeRequest;

public class ReservationsFixture {

    private ReservationsFixture() {

    }

    public static ReservationTime reservationTime() {
        return reservationTime("10:00");
    }

    public static ReservationTime reservationTime(String time) {
        return ReservationTime.of(time);
    }

    public static Theme theme(String name, String description) {
        return Theme.of(name,
                description,
                "https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg");
    }


    public static Theme theme() {
        return theme("레벨2 탈출", "우테코 레벨2를 탈출하는 내용입니다.");
    }

    public static Reservation reservation(ReservationTime time, Theme theme, String date, Long memberId) {
        return Reservation.of("테스트_예약자", date, time, theme, memberId);
    }

    public static ReservationTimeRequest reservationTimeRequest() {
        return new ReservationTimeRequest("10:00");
    }

    public static ThemeRequest themeRequest() {
        return new ThemeRequest("레벨2 탈출",
                "우테코 레벨2를 탈출하는 내용입니다.",
                "https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg");
    }

    public static ReservationRequest reservationRequest(long timeId, long themeId) {
        return new ReservationRequest("2099-01-12", timeId, themeId);
    }

    public static ReservationAdminRequest reservationAdminRequest(long timeId, long themeId, long memberId) {
        return new ReservationAdminRequest("2099-01-12", timeId, themeId, memberId);
    }

    public static ReservationSearchParams reservationSearchParams(Long memberId) {
        return reservationSearchParams(null, memberId);
    }

    public static ReservationSearchParams reservationSearchParams(Long themeId, Long memberId) {
        return new ReservationSearchParams(themeId, memberId, null, null);
    }

    public static ReservationSearchParams reservationSearchParams(String dateFrom) {
        return reservationSearchParams(dateFrom, null);
    }

    public static ReservationSearchParams reservationSearchParamsDateTo(String dateTo) {
        return reservationSearchParams(null, dateTo);
    }

    public static ReservationSearchParams reservationSearchParams(String dateFrom, String dateTo) {
        return new ReservationSearchParams(null, null, dateFrom, dateTo);
    }

    public static ReservationSearchParams reservationSearchParams(Long themeId,
                                                                  Long memberId,
                                                                  String dateFrom,
                                                                  String dateTo
    ) {
        return new ReservationSearchParams(themeId, memberId, dateFrom, dateTo);
    }

    public static ReservationSearchParams reservationSearchParamsThemeId(Long themeId) {
        return reservationSearchParams(themeId, null);
    }
}
