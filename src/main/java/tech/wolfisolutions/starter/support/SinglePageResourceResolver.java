package tech.wolfisolutions.starter.support;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.resource.ResourceResolver;
import org.springframework.web.servlet.resource.ResourceResolverChain;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class SinglePageResourceResolver implements ResourceResolver {

    private final Resource index = new ClassPathResource("/static/index.html");
    private final List<String> allowedExtensions = AllowedResolvableExtension.getResolvableExtensions();
    private final List<String> ignoredEndpoints = List.of("api", "actuator");

    @Override
    public Resource resolveResource(HttpServletRequest request, String path, List<? extends Resource> locations, ResourceResolverChain chain) {
        return resolve(path, locations);
    }

    @Override
    public String resolveUrlPath(String path, List<? extends Resource> locations, ResourceResolverChain chain) {
        Resource resolved = resolve(path, locations);

        if (Objects.isNull(resolved)) {
            return null;
        }

        try {
            return resolved.getURL().toString();
        } catch (IOException ex) {
            return resolved.getFilename();
        }
    }

    private Resource resolve(String path, List<? extends Resource> locations) {
        if (isIgnoredEndpoint(path)) {
            return null;
        }

        if (isAllowedExtension(path)) {
            return locations.stream()
                    .map(location -> createRelativeResource(path, location))
                    .filter(resource -> Objects.nonNull(resource) && resource.exists())
                    .findFirst()
                    .orElse(null);
        }

        return this.index;
    }

    private Resource createRelativeResource(String path, Resource resource) {
        try {
            return resource.createRelative(path);
        } catch (IOException ex) {
            return null;
        }
    }

    private boolean isIgnoredEndpoint(String path) {
        return this.ignoredEndpoints.stream().anyMatch(path::startsWith);
    }

    private boolean isAllowedExtension(String path) {
        String extension = StringUtils.getFilenameExtension(path);
        return this.allowedExtensions.stream().anyMatch(ext -> ext.equals(extension));
    }
}
