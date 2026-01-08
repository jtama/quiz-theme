package io.github.jtama.quiz.theme;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.annotation.Nonnull;

import io.quarkiverse.roq.frontmatter.runtime.model.Page;
import io.quarkus.qute.TemplateExtension;

public class QuizTemplateExtensions {

    @TemplateExtension
    public static String collectionTitles(Page page) {
        return "['%s']".formatted(getModules(page)
                .map(Page::title)
                .map(value -> value.replaceAll("'", "\\\\'"))
                .collect(Collectors.joining("','")));
    }

    @TemplateExtension
    public static List<Page> allModules(Page page) {
        return getModules(page).toList();
    }

    @TemplateExtension
    public static long allModulesSize(Page page) {
        return getModules(page).count();
    }

    @TemplateExtension
    public static String nextModule(Page page) {
        List<Page> modules = getModules(page).toList();
        if (page.url().equals(page.site().url()))
            return modules.getFirst().url().relative();
        int index = modules.indexOf(page);
        return index > 0 && index + 1 <= modules.size() + 1 ? modules.get(index + 1).url().relative()
                : page.site().url().fromRoot("leaderboard").absolute();
    }

    @Nonnull
    private static Stream<Page> getModules(Page page) {
        return page.site().allPages().stream()
                .filter(item -> item.data().containsKey("tags") && item.data().getString("tags").contains("module"));
    }
}
