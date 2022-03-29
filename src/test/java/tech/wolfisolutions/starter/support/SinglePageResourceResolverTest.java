package tech.wolfisolutions.starter.support;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.resource.ResourceResolverChain;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SinglePageResourceResolverTest {

    private HttpServletRequest request;
    private ResourceResolverChain chain;

    @BeforeEach
    public void setup() {
        request = Mockito.mock(HttpServletRequest.class);
        chain = Mockito.mock(ResourceResolverChain.class);
    }

    @Test
    @DisplayName("Should Return Default Index Page")
    public void testShouldReturnDefaultIndexPage() {
        ClassPathResource resource = new ClassPathResource("static/index.html");
        SinglePageResourceResolver resolver = new SinglePageResourceResolver();
        Resource resolved = resolver.resolveResource(request, "xxx", List.of(Mockito.mock(Resource.class)), chain);
        assertNotNull(resolved);
        assertEquals(resolved, resource);
        assertEquals("static/index.html", ((ClassPathResource) resolved).getPath());
    }

    @Test
    @DisplayName("Should Return Valid File In Support Extension")
    public void testShouldReturnValidFileWithSupportedExtension() {
        ClassPathResource resource = new ClassPathResource("static/");
        SinglePageResourceResolver resolver = new SinglePageResourceResolver();
        Resource resolved = resolver.resolveResource(request, "example.json", List.of(resource), chain);
        assertNotNull(resolved);
        assertEquals("static/example.json", ((ClassPathResource) resolved).getPath());
    }

    @Test
    @DisplayName("Should Return Index With Invalid Extension")
    public void testShouldReturnIndexWithInvalidExtension() {
        ClassPathResource resource = new ClassPathResource("static/");
        SinglePageResourceResolver resolver = new SinglePageResourceResolver();
        Resource resolved = resolver.resolveResource(request, "example.mdx", List.of(resource), chain);
        assertNotNull(resolved);
        assertEquals("static/index.html", ((ClassPathResource) resolved).getPath());
    }

    @Test
    @DisplayName("Should Return Null If Valid Endpoint")
    public void testShouldReturnNullIfValidEndpoint() {
        ClassPathResource resource = new ClassPathResource("static/");
        SinglePageResourceResolver resolver = new SinglePageResourceResolver();
        Resource resolved = resolver.resolveResource(request, "api", List.of(resource), chain);
        assertNull(resolved);
    }
}