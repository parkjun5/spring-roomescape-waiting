package roomescape.apply.theme.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roomescape.apply.theme.application.exception.NotFoundThemeException;
import roomescape.apply.theme.domain.Theme;
import roomescape.apply.theme.domain.ThemeRepository;
import roomescape.apply.theme.ui.dto.ThemeResponse;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ThemeFinder {

    private final ThemeRepository themeRepository;

    public ThemeFinder(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    public List<ThemeResponse> findAll() {
        return themeRepository.findAll()
                .stream()
                .map(ThemeResponse::from)
                .toList();
    }

    public Theme findOneById(long themeId) {
        return themeRepository.findOneById(themeId).orElseThrow(NotFoundThemeException::new);
    }
}
