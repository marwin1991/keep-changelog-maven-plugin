# keep-changelog-maven-plugin
`CHANGELOG.md` is one of the most important files in a repository. It allows others to find out about 
the most important changes in the project in short time.
To achieve this, `CHANGELOG.md` should be created be in accordance with the rules, however there is no one
standard agreed by community.

Another big problem with `CHANGELOG.md` is a problem with merge conflicts. Probably you, as a developer also
encounter it, when someone merged changes to `CHANGELOG.md` before you.

**To solve these problems, this maven plugin was created. It allows to keep a changelog style and reduce merge request conflicts by keeping every change
in a separate YAML file and generate `CHANGELOG.md` during release.**

This plugin is also helpful to create reliable release notes during your release process. 

### Reference

The convention is maintained according to the principles set out in the [Keep a Changelog](https://keepachangelog.com/en/1.0.0/) and with some additions as a result from experience in developing various types of projects.

The same problem with merge conflicts with `CHANGELOG.md` was described by GitLab. [LINK](https://about.gitlab.com/blog/2018/07/03/solving-gitlabs-changelog-conflict-crisis/)

### Example
The example project with this plugin usage you can find in `example` directory.

## Usage
### Starting
Add a plugin to your `pom.xml`. For multi-module projects with one `CHANGELOG.md` add it in main `pom.xml`.

```xml
    <build>
        <plugins>
            <plugin>
                <groupId>io.github.marwin1991</groupId>
                <artifactId>keep-changelog-maven-plugin</artifactId>
                <version>0.1.0</version>
            </plugin>
        </plugins>
    </build>
```

[in-progress] Use this command from your terminal to create important directories and empty CHANGELOG.md
```shell
mvn keep-changelog:init
```

If you already had a `CHANGELOG.md` file you can move it to `changelog/archive.md` file. The name of the archive file have to start from `archive` phrase (f.e. `archive-1.0.0.md`).

After using `init` command or just creating `changelog/unreleased` directory your project is ready, and you can start adding new changelog entries by creating YAML files.

**IMPORTANT:** If you develop on two main branches like f.e 1.1.X and 1.2.X **do not** merge 1.1.X branch to 1.2.X before release otherwise YAML files will merge in one big version. 
_In future there is a plan to support `unreleased*` directories names like `unreleased-1.1`_

### Adding new change
TODO

### YAML format
TODO

### Generating `CHANGELOG.md`
TODO

### `CHANGELOG.md` overview
TODO

### Releasing the version
TODO

### Archives
TODO


## TODO: 
- update README to include all important aspects: first steps (add to pom.xml etc.), available commands
- move current changelog directory to example
- add command to create release with a version from pom.xml as directory name and release-date.txt file
- add command to create archive-X.md from selected directory
- add tests
- add issue to snakeyaml-anno to include specific sl4j lib
- add javadocs
- support  `unreleased*` directories names like `unreleased-1.1`
