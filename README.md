# randomizer

<div align="center">

[![Build Status](https://travis-ci.org/astrapi69/randomizer.svg?branch=develop)](https://travis-ci.org/astrapi69/randomizer) 
[![Coverage Status](https://coveralls.io/repos/github/astrapi69/randomizer/badge.svg?branch=develop)](https://coveralls.io/github/astrapi69/randomizer?branch=develop) 
[![Open Issues](https://img.shields.io/github/issues/astrapi69/randomizer.svg?style=flat)](https://github.com/astrapi69/randomizer/issues) 
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.astrapi69/randomizer/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.astrapi69/randomizer-core)
[![Javadocs](http://www.javadoc.io/badge/io.github.astrapi69/randomizer.svg)](http://www.javadoc.io/doc/io.github.astrapi69/randomizer-core)
[![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat)](http://opensource.org/licenses/MIT)
[![Donate](https://img.shields.io/badge/donate-❤-ff2244.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=GVBTWLRAZ7HB8)

</div>

Generate random data
> Please support this project by simply putting a Github <!-- Place this tag where you want the button to render. -->
                                                         <a class="github-button" href="https://github.com/astrapi69/randomizer" data-icon="octicon-star" aria-label="Star astrapi69/randomizer on GitHub">Star ⭐</a>. Share this library with friends on Twitter and everywhere else you can.
If you love this project [![donation](https://img.shields.io/badge/donate-❤-ff2244.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=GVBTWLRAZ7HB8)
   

## Note

No animals were harmed in the making of this library.

# Donations

This project is kept as an open source product and relies on contributions to remain being developed. 
If you like this project, please consider a donation through paypal: <a href="https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=GVBTWLRAZ7HB8" target="_blank">
<img src="https://www.paypalobjects.com/en_US/GB/i/btn/btn_donateCC_LG.gif" alt="PayPal this" title="PayPal – The safer, easier way to pay online!" border="0" />
</a>

or over bitcoin or bitcoin-cash with:

1Jzso5h7U82QCNmgxxSCya1yUK7UVcSXsW

or over ether with:

0xaB6EaE10F352268B0CA672Dd6e999C86344D49D8

or over the donation buttons at the top.

## License

The source code comes under the liberal MIT License, making randomizer great for all types of applications.

## Javadoc

[![Javadocs](http://www.javadoc.io/badge/io.github.astrapi69/randomizer.svg)](http://www.javadoc.io/doc/io.github.astrapi69/randomizer-core) randomizer-core

[![Javadocs](http://www.javadoc.io/badge/io.github.astrapi69/randomizer.svg)](http://www.javadoc.io/doc/io.github.astrapi69/randomizer-api) randomizer-api

[![Javadocs](http://www.javadoc.io/badge/io.github.astrapi69/randomizer.svg)](http://www.javadoc.io/doc/io.github.astrapi69/randomizer-data) randomizer-data

## Maven dependency

Maven dependency is now on sonatype.
Check out [sonatype repository](https://oss.sonatype.org/index.html#nexus-search;gav~io.github.astrapi69~randomizer~~~) for latest snapshots and releases.

Add the following maven dependency to your project `pom.xml` if you want to import the core functionality of randomizer:

Than you can add the dependency to your dependencies:


	<properties>
			...
		<!-- RANDOMIZER versions -->
		<randomizer.version>8.1</randomizer.version>
		<randomizer-api.version>${randomizer.version}</randomizer-api.version>
		<randomizer-address.version>${randomizer.version}</randomizer-address.version>
		<randomizer-core.version>${randomizer.version}</randomizer-core.version>
		<randomizer-data.version>${randomizer.version}</randomizer-data.version>
			...
	</properties>
	
You can add the following dependencies to your project for use the functionality of randomizer.

Add the following maven dependency to your project `pom.xml` if you want to import the functionality of randomizer-api:

		<dependencies>
			...
			<!-- RANDOMIZER DEPENDENCY -->
			<dependency>
				<groupId>io.github.astrapi69</groupId>
				<artifactId>randomizer-api</artifactId>
				<version>${randomizer-api.version}</version>
			</dependency>
			...
		</dependencies>
		
Add the following maven dependency to your project `pom.xml` if you want to import the functionality of randomizer-address:

		<dependencies>
			...
			<!-- RANDOMIZER DEPENDENCY -->
			<dependency>
				<groupId>io.github.astrapi69</groupId>
				<artifactId>randomizer-address</artifactId>
				<version>${randomizer-address.version}</version>
			</dependency>
			...
		</dependencies>

Add the following maven dependency to your project `pom.xml` if you want to import the functionality of randomizer-core:

		<dependencies>
			...
			<!-- RANDOMIZER DEPENDENCY -->
			<dependency>
				<groupId>io.github.astrapi69</groupId>
				<artifactId>randomizer-core</artifactId>
				<version>${randomizer-core.version}</version>
			</dependency>
			...
		</dependencies>

Add the following maven dependency to your project `pom.xml` if you want to import the functionality of randomizer-data:

		<dependencies>
			...
			<!-- RANDOMIZER DEPENDENCY -->
			<dependency>
				<groupId>io.github.astrapi69</groupId>
				<artifactId>randomizer-data</artifactId>
				<version>${randomizer-data.version}</version>
			</dependency>
			...
		</dependencies>
			
## gradle dependency

You can first define the version in the ext section and add than the following gradle dependency to 
your project `build.gradle` if you want to import the core functionality of randomizer:

```
define version in file gradle.properties

randomizerVersion=8.1
```

or in build.gradle ext area

```
ext {
			...
    randomizerVersion = "8.1"
			...
}
```

and than add the dependency to the dependencies area

```
dependencies {
			...
    implementation("io.github.astrapi69:randomizer-api:$randomizerVersion")
    implementation("io.github.astrapi69:randomizer-address:$randomizerVersion")
    implementation("io.github.astrapi69:randomizer-core:$randomizerVersion")
    implementation("io.github.astrapi69:randomizer-data:$randomizerVersion")
			...
}
```

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

## Contacting the Developer

Do not hesitate to contact the randomizer developers with your questions, concerns, comments, bug reports, or feature requests.
- Feature requests, questions and bug reports can be reported at the [issues page](https://github.com/astrapi69/randomizer/issues).

# Similar projects

Here is a list of awesome projects:

 * [random-beans](https://github.com/benas/random-beans) Because life is too short to generate random Java beans by hand..
 * [junit-quickcheck](https://github.com/pholser/junit-quickcheck) Property-based testing, JUnit-style
 * [jfairy](https://github.com/Devskiller/jfairy) Java fake data generator 

## Credits

|**Travis CI**|
|     :---:      |
|[![Travis CI](https://travis-ci.com/images/logos/TravisCI-Full-Color.png)]|
|[![Build Status](https://travis-ci.org/astrapi69/randomizer.svg?branch=develop)](https://travis-ci.org/astrapi69/randomizer)|
|Special thanks to [Travis CI](https://travis-ci.org) for providing a free continuous integration service for open source projects|
|     <img width=1000/>     |

|**Nexus Sonatype repositories**|
|     :---:      |
|[![sonatype repository](https://img.shields.io/nexus/r/https/oss.sonatype.org/io.github.astrapi69/randomizer-api.svg?style=for-the-badge)](https://oss.sonatype.org/index.html#nexus-search;gav~io.github.astrapi69~randomizer-api~~~) randomizer-api|
|[![sonatype repository](https://img.shields.io/nexus/r/https/oss.sonatype.org/io.github.astrapi69/randomizer-core.svg?style=for-the-badge)](https://oss.sonatype.org/index.html#nexus-search;gav~io.github.astrapi69~randomizer-core~~~) randomizer-core|
|[![sonatype repository](https://img.shields.io/nexus/r/https/oss.sonatype.org/io.github.astrapi69/randomizer-data.svg?style=for-the-badge)](https://oss.sonatype.org/index.html#nexus-search;gav~io.github.astrapi69~randomizer-data~~~) randomizer-data|
|Special thanks to [sonatype repository](https://www.sonatype.com) for providing a free maven repository service for open source projects|
|     <img width=1000/>     |

|**coveralls.io**|
|     :---:      |
|[![Coverage Status](https://coveralls.io/repos/github/astrapi69/randomizer/badge.svg?branch=develop)](https://coveralls.io/github/astrapi69/randomizer?branch=develop)|
|Special thanks to [coveralls.io](https://coveralls.io) for providing a free code coverage for open source projects|
|     <img width=1000/>     |

|**javadoc.io**|
|     :---:      |
|[![Javadoc](http://www.javadoc.io/badge/io.github.astrapi69/randomizer-api.svg)](http://www.javadoc.io/doc/io.github.astrapi69/randomizer-api) randomizer-api|
|[![Javadoc](http://www.javadoc.io/badge/io.github.astrapi69/randomizer-core.svg)](http://www.javadoc.io/doc/io.github.astrapi69/randomizer-core) randomizer-core|
|[![Javadoc](http://www.javadoc.io/badge/io.github.astrapi69/randomizer-data.svg)](http://www.javadoc.io/doc/io.github.astrapi69/randomizer-data) randomizer-data|
|Special thanks to [javadoc.io](http://www.javadoc.io) for providing a free javadoc documentation for open source projects|
|     <img width=1000/>     |
