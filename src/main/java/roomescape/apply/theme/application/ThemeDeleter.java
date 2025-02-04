package roomescape.apply.theme.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roomescape.apply.reservation.application.ReservationFinder;
import roomescape.apply.theme.application.exception.NotFoundThemeException;
import roomescape.apply.theme.application.exception.ThemeReferencedException;
import roomescape.apply.theme.domain.ThemeRepository;

@Service
public class ThemeDeleter {

    private final ThemeRepository themeRepository;
    private final ReservationFinder reservationFinder;

    public ThemeDeleter(ThemeRepository themeRepository, ReservationFinder reservationFinder) {
        this.themeRepository = themeRepository;
        this.reservationFinder = reservationFinder;
    }

    @Transactional
    public void deleteThemeBy(long id) {
        final long existId = themeRepository.findIdById(id).orElseThrow(NotFoundThemeException::new);
        boolean isReferenced = reservationFinder.findIdByThemeId(id).isPresent();
        if (isReferenced) {
            throw new ThemeReferencedException();
        }

        themeRepository.deleteById(existId);
    }
}
