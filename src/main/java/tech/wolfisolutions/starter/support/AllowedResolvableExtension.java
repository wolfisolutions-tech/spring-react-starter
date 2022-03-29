package tech.wolfisolutions.starter.support;

import java.util.Arrays;
import java.util.List;

public enum AllowedResolvableExtension {

    HTML("html"),
    JS("js"),
    JSON("json"),
    CSV("csv"),
    CSS("css"),
    PNG("png"),
    SVG("svg"),
    EOT("eot"),
    TTF("ttf"),
    WOFF("woff"),
    APP_CACHE("appcache"),
    JPG("jpg"),
    JPEG("jpeg"),
    GIF("gif"),
    ICO("ico"),
    MAP("map"),
    WOFF2("woff2"),
    WASM("wasm");

    private final String extension;

    AllowedResolvableExtension(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return this.extension;
    }

    public static List<String> getResolvableExtensions() {
        return Arrays.stream(AllowedResolvableExtension.values())
                .map(AllowedResolvableExtension::getExtension)
                .toList();
    }
}
