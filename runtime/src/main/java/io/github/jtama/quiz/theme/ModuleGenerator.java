package io.github.jtama.quiz.theme;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import io.quarkiverse.roq.util.PathUtils;
import io.quarkus.logging.Log;
import io.quarkus.runtime.StartupEvent;
import io.vertx.core.json.JsonObject;

@ApplicationScoped
public class ModuleGenerator {
    @Inject
    @Named("modules")
    JsonObject allModules;

    // front matter with dynamics values from the YAML conferences data.
    String frontMatter = """
            ---
            title: "%s"
            layout: :theme/module
            tags: module
            questions: %s
            ---""";

    void onStart(@Observes StartupEvent ev) throws IOException {
        Log.info("🚀 Modules pages generation...");
        Map<String, Object> mapOfAllModules = allModules.getMap();

        for (var entry : mapOfAllModules.entrySet()) {
            JsonObject module = (JsonObject) entry.getValue();
            Path dir = Path.of("./content/modules/", PathUtils.slugify(module.getString("title"), false, false));
            if (!Files.isDirectory(dir)) {
                Files.createDirectories(dir);
            }
            Path file = Path.of(dir + "/index.markdown");
            if (!Files.exists(file)) {
                Files.createDirectories(dir);
                Files.write(file,
                        frontMatter.formatted(module.getString("title"),
                                module.getJsonArray("questions")).getBytes(),
                        StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING,
                        StandardOpenOption.WRITE);
            }
        }
        Log.info("✅ Module pages generated ✅");
    }
}
