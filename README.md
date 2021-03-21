# keep-changelog-maven-plugin

This is a maven plugin created for keep a changelog style and reduce merge request conflicts.

https://keepachangelog.com/en/1.0.0/

https://about.gitlab.com/blog/2018/07/03/solving-gitlabs-changelog-conflict-crisis/


TODO: 
- add generating changelog directory with .gitkepp and empty changelog file, if not exists with init command
- update README to include all important aspects: first steps (add to pom.xml etc.), available commands
- move current changelog directory to example
- add command to create archive-X.md from selected directory
- add tests
- add issue to snakeyaml-anno to include specific sl4j lib
- add javadocs


Usage:
- add a directory to your project with name `changelog`
- add a directory to your `changelog` directory with name `unreleased`
- add a file to your project with name `CHANGELOG.md`



