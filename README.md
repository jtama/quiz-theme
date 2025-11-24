# ROQ Quiz theme

This theme allow to generate quiz site based only on YAML data files.

## Use it

### The pom

Generate you ROQ application and add this theme

<details>

<summary>`pom.xml`</summary>

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>org.acme</groupId>
    <artifactId>what-a-quiz</artifactId>
    <version>1.0.0-SNAPSHOT</version>

    <properties>
        <compiler-plugin.version>3.14.0</compiler-plugin.version>
        <maven.compiler.release>21</maven.compiler.release>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <quarkus.platform.artifact-id>quarkus-bom</quarkus.platform.artifact-id>
        <quarkus.platform.group-id>io.quarkus.platform</quarkus.platform.group-id>
        <quarkus.platform.version>3.29.2</quarkus.platform.version>
        <skipITs>true</skipITs>
        <surefire-plugin.version>3.5.4</surefire-plugin.version>
    </properties>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>${quarkus.platform.group-id}</groupId>
                <artifactId>${quarkus.platform.artifact-id}</artifactId>
                <version>${quarkus.platform.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <dependencies>
        <dependency>
            <groupId>io.quarkiverse.roq</groupId>
            <artifactId>quarkus-roq</artifactId>
            <version>1.10.2</version>
        </dependency>
        <dependency>
            <groupId>com.github.jtama.quiz</groupId>
            <artifactId>quiz-theme</artifactId>
            <version>1.0.0</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>${quarkus.platform.group-id}</groupId>
                <artifactId>quarkus-maven-plugin</artifactId>
                <version>${quarkus.platform.version}</version>
                <extensions>true</extensions>
                <executions>
                    <execution>
                        <goals>
                            <goal>build</goal>
                            <goal>generate-code</goal>
                            <goal>generate-code-tests</goal>
                            <goal>native-image-agent</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>${compiler-plugin.version}</version>
                <configuration>
                    <parameters>true</parameters>
                </configuration>
            </plugin>
            <plugin>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>${surefire-plugin.version}</version>
                <configuration>
                    <systemPropertyVariables>
                        <java.util.logging.manager>org.jboss.logmanager.LogManager</java.util.logging.manager>
                        <maven.home>${maven.home}</maven.home>
                    </systemPropertyVariables>
                </configuration>
            </plugin>
            <plugin>
                <artifactId>maven-failsafe-plugin</artifactId>
                <version>${surefire-plugin.version}</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>integration-test</goal>
                            <goal>verify</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <systemPropertyVariables>
                        <native.image.path>${project.build.directory}/${project.build.finalName}-runner</native.image.path>
                        <java.util.logging.manager>org.jboss.logmanager.LogManager</java.util.logging.manager>
                        <maven.home>${maven.home}</maven.home>
                    </systemPropertyVariables>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

```
</details>

### The layout

Here is the only needed work from you to use the theme.

Create a `content` folder with the following files (the file names don't matter).

<details>

<summary>`404.html`, because yes it's always a good idea.</summary>

```html
---
layout: :theme/default
---

Non non non petit malin
```

</details>

<details>

<summary>`index.html`</summary>

```html
---
name: Roq quizzes really stand out !
simple-name: Wow it's so amazing !
layout: :theme/startQuiz
---
```
</details>

<details>

<summary>`leaderboard.html`</summary>

```html
---
layout: :theme/leaderboard
---
```
</details>

### The questions

In the `content>modules` folder, each html file will be a quiz module. It's called html for now, but all the data lies in the **frontmatter** headers.

You can create a file called `roq.html` with the following content : 

```yaml
---
title: Toto
questions:
  - question: |
      Is `ROQ` a ***GREAT*** static site generator
    answer: |
      - A) Yes of course
      - B) The best !
      - C) I heard it was created by Chuck Norris

  - question: |
      Are theme a great add to ROQ
    answer: |
      Yes they are. They can provide templates, scripts and styles for your site

---
```

Questions AND answers are considered to be MarkDown and will be rendered as is.

## Overriding the colors.

Here are the followwing variables you can override by simple adding an `app.css` file to your `web` folder : 

```css
@layer base {
    :root {
        color-scheme: light dark;

        --primary: light-dark(#1e3a8a, #3b82f6);
        --primary-50: light-dark(#dbeafe, #1e293b);
        --primary-100: light-dark(#bfdbfe, #1e3a8a);
        --primary-600: light-dark(#1e3a8a, #60a5fa);
        --primary-700: light-dark(#1e40af, #93c5fd);
        --primary-800: light-dark(#1e3a8a, #bfdbfe);
        --primary-900: light-dark(#172554, #dbeafe);

        --gray-50: light-dark(#ffffff, #0f172a);
        --gray-100: light-dark(#f8fafc, #1e293b);
        --gray-200: light-dark(#e2e8f0, #334155);
        --gray-300: light-dark(#94a3b8, #64748b);

        --success: light-dark(#15803d, #22c55e);
        --success-600: light-dark(#15803d, #4ade80);
        --success-700: light-dark(#166534, #86efac);
        --error: light-dark(#b91c1c, #ef4444);
        --error-600: light-dark(#b91c1c, #f87171);
        --error-700: light-dark(#991b1b, #fca5a5);

        --bg: light-dark(#ffffff, #020617);
        --surface: light-dark(#ffffff, #0f172a);
        --surface-elevated: light-dark(#f8fafc, #1e293b);
        --border: light-dark(#94a3b8, #64748b);
        --border-light: light-dark(#cbd5e1, #334155);
        --border-focus: var(--primary);

        --text-primary: light-dark(#000000, #f8fafc);
        --text-secondary: light-dark(#1e293b, #cbd5e1);
        --text-muted: light-dark(#334155, #94a3b8);

        --radius: 12px;
        --radius-lg: 16px;
        --radius-xl: 20px;

        --shadow-sm: 0 2px 6px 0 rgb(0 0 0 / 0.2);
        --shadow: 0 4px 12px 0 rgb(0 0 0 / 0.25), 0 2px 6px -2px rgb(0 0 0 / 0.2);
        --shadow-md: 0 8px 20px -2px rgb(0 0 0 / 0.3), 0 4px 12px -4px rgb(0 0 0 / 0.25);
        --shadow-lg: 0 16px 40px -8px rgb(0 0 0 / 0.35), 0 8px 20px -8px rgb(0 0 0 / 0.3);

        --spacing-xs: 0.5rem;
        --spacing-sm: 0.75rem;
        --spacing: 1.25rem;
        --spacing-lg: 2rem;
        --spacing-xl: 2.5rem;
        --spacing-2xl: 3.5rem;
        --spacing-3xl: 5rem;
    }
}
```