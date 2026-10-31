# randomizer

<div style="text-align: center">

[![Java CI with Gradle](https://github.com/astrapi69/randomizer/actions/workflows/gradle.yml/badge.svg)](https://github.com/astrapi69/randomizer/actions/workflows/gradle.yml)
[![codecov](https://codecov.io/gh/astrapi69/randomizer/branch/develop/graph/badge.svg)](https://codecov.io/gh/astrapi69/randomizer)
[![Open Issues](https://img.shields.io/github/issues/astrapi69/randomizer.svg?style=flat)](https://github.com/astrapi69/randomizer/issues)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.astrapi69/randomizer)](https://central.sonatype.com/artifact/io.github.astrapi69/randomizer)
[![Javadocs](https://www.javadoc.io/badge/io.github.astrapi69/randomizer.svg)](https://www.javadoc.io/doc/io.github.astrapi69/randomizer)
[![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat)](http://opensource.org/licenses/MIT)
[![Donate](https://img.shields.io/badge/donate-❤-ff2244.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=GVBTWLRAZ7HB8)

</div>

Generate random data: numbers, strings, dates and objects, for tests and sample data.

**randomizer is not meant for secrets.** For passwords, keys, salts, nonces or tokens, use
`java.security.SecureRandom` directly. Before 10.5, a `SecureRandom` this library built without an
explicit seed was seeded with the current time in milliseconds
([GHSA-395w-fmf5-xfj3](https://github.com/astrapi69/randomizer/security/advisories/GHSA-395w-fmf5-xfj3));
see [SECURITY.md](SECURITY.md).

> Please support this project by simply putting a Github <!-- Place this tag where you want the button to render. -->
                                                         <a class="github-button" href="https://github.com/astrapi69/randomizer" data-icon="octicon-star" aria-label="Star astrapi69/randomizer on GitHub">Star ⭐</a>
>
> Share this library with friends on Twitter and everywhere else you can
>
> If you love this project [![donation](https://img.shields.io/badge/donate-❤-ff2244.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=GVBTWLRAZ7HB8)

## Note

No animals were harmed in the making of this library.

## gradle dependency

You can first define the version in the ext section and add than the following gradle dependency to
your project `build.gradle` if you want to import the core functionality of randomizer:

define version in file gradle.properties
```
randomizerVersion=10.6
```

or in build.gradle ext area

```
    randomizerVersion = "10.6"
```

then add the dependency to the dependencies area

```
    implementation("io.github.astrapi69:randomizer:$randomizerVersion")
```

## Maven dependency

Releases are on Maven Central:
[io.github.astrapi69:randomizer](https://central.sonatype.com/artifact/io.github.astrapi69/randomizer).

Add the following maven dependency to your project `pom.xml` if you want to import the core functionality of randomizer:

Than you can add the dependency to your dependencies:


    <properties>
            ...
        <!-- RANDOMIZER versions -->
        <randomizer.version>10.6</randomizer.version>
            ...
    </properties>

You can add the following dependencies to your project for use the functionality of randomizer.

Add the following maven dependency to your project `pom.xml` if you want to import the
functionality of randomizer:

        <dependencies>
            ...
            <!-- RANDOMIZER DEPENDENCY -->
            <dependency>
                <groupId>io.github.astrapi69</groupId>
                <artifactId>randomizer</artifactId>
                <version>${randomizer.version}</version>
            </dependency>
            ...
        </dependencies>

## Support this project

If randomizer is useful to you, a star on [GitHub](https://github.com/astrapi69/randomizer) helps others find it.
Sharing it or reporting a bug helps just as much.

If you would like to contribute financially, the channels are on the repository's Sponsor button, which
reads [.github/FUNDING.yml](.github/FUNDING.yml): GitHub Sponsors, Liberapay, Ko-fi and PayPal.

## License

The source code comes under the liberal MIT License, making randomizer great for all types of applications.

## Javadoc

[![javadoc](https://javadoc.io/badge2/io.github.astrapi69/randomizer/javadoc.svg)](https://javadoc.io/doc/io.github.astrapi69/randomizer) randomizer

## Semantic Versioning

The versions of randomizer are maintained with the Semantic Versioning guidelines.

Release version numbers will be incremented in the following format:

`<major>.<minor>.<patch>`

For detailed information on versioning you can visit the [wiki page](https://github.com/lightblueseas/mvn-parent-projects/wiki/Semantic-Versioning).

## Want to Help and improve it? ###

The source code for randomizer are on GitHub. Please feel free to fork and send pull requests!

Create your own fork of [astrapi69/randomizer/fork](https://github.com/astrapi69/randomizer/fork)

To share your changes, [submit a pull request](https://github.com/astrapi69/randomizer/pull/new/develop).

Don't forget to add new units tests on your changes.

The tests state properties of the generators where they can ([jqwik](https://jqwik.net), the
`*Properties` classes), and [PIT](https://pitest.org) measures them: the build fails below a
mutation score of 70 %; on 2026-10-17 it measured 94 % (345 of 367 mutants killed). How to run
it, the numbers by class and the mutants left: [docs/MUTATION_TESTING.md](docs/MUTATION_TESTING.md).

## Contacting the Developer

Do not hesitate to contact the randomizer developers with your questions, concerns, comments, bug reports, or feature requests.
- Feature requests, questions and bug reports can be reported at the [issues page](https://github.com/astrapi69/randomizer/issues).

# Similar projects

Here is a list of awesome projects:

 * [random-beans](https://github.com/benas/random-beans) Because life is too short to generate random Java beans by hand..
 * [junit-quickcheck](https://github.com/pholser/junit-quickcheck) Property-based testing, JUnit-style
 * [jfairy](https://github.com/Devskiller/jfairy) Java fake data generator

## Credits

|**Maven Central**|
|     :---:      |
|[![Maven Central](https://img.shields.io/maven-central/v/io.github.astrapi69/randomizer?style=for-the-badge)](https://central.sonatype.com/artifact/io.github.astrapi69/randomizer) randomizer|
|Special thanks to [sonatype](https://www.sonatype.com) for providing Maven Central for open source projects|
|     <img width=1000/>     |

|**codecov.io**|
|     :---:      |
|[![codecov](https://codecov.io/gh/astrapi69/randomizer/branch/develop/graph/badge.svg)](https://codecov.io/gh/astrapi69/randomizer)|
|Special thanks to [codecov.io](https://codecov.io) for providing a free code coverage for open source projects|
|     <img width=1000/>     |

|**javadoc.io**|
|     :---:      |
|[![Javadoc](https://www.javadoc.io/badge/io.github.astrapi69/randomizer.svg)](https://www.javadoc.io/doc/io.github.astrapi69/randomizer) randomizer|
|Special thanks to [javadoc.io](https://www.javadoc.io) for providing a free javadoc documentation for open source projects|
|     <img width=1000/>     |

## Git hooks

The repository's git hooks live in `.githooks`: `commit-msg` where present, and `pre-commit`, which refuses a
commit whose files Spotless would format. Activate them once per clone or worktree:

```bash
git config core.hooksPath .githooks
```
