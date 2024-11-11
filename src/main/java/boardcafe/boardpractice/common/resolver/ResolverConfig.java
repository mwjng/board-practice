package boardcafe.boardpractice.common.resolver;

import boardcafe.boardpractice.auth.presentation.AuthenticationPrincipalArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class ResolverConfig implements WebMvcConfigurer {

    private final AuthenticationPrincipalArgumentResolver authenticationPrincipalArgumentResolver;

    public ResolverConfig(final AuthenticationPrincipalArgumentResolver authenticationPrincipalArgumentResolver) {
        this.authenticationPrincipalArgumentResolver = authenticationPrincipalArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(authenticationPrincipalArgumentResolver);
    }
}
