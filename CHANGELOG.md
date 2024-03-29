## Change log
----------------------

Version 10.3-SNAPSHOT
-------------



Version 10.2
-------------

CHANGED:

- update of gradle to new version 8.4
- update of com.github.ben-manes.versions.gradle.plugin to new version 0.49.0
- update of gradle-plugin dependency 'org.ajoberstar.grgit:grgit-gradle' to new version 5.2.1
- update of gradle-plugin dependency of 'com.diffplug.spotless:spotless-plugin-gradle' in version 6.22.0
- update of dependency jobj-core to new version 8.1
- update of dependency jobj-reflect to new major version 2.4
- update of dependency silly-math to new version 2.3
- update of dependency jobj-copy to new minor version 4.2
- update of test dependency equalsverifier to new version 3.15.3
- update of test dependency silly-strings to new version 9
- update of test dependency test-object to new minor version 8.2
- update of test dependency meanbean to the new milestone version 3.0.0-M9

Version 10.1
-------------

CHANGED:

- update of dependency jobj-reflect to new major version 2
- update of dependency jobj-copy to new minor version 4.1

Version 10
-------------

ADDED:

- new class module-info.java that provides this library as a module

CHANGED:

- update of jdk to version 17
- update of gradle to new version 8.4-rc-1
- update of com.github.ben-manes.versions.gradle.plugin to new version 0.48.0
- update of gradle-plugin dependency of 'com.diffplug.spotless:spotless-plugin-gradle' in version 6.21.0
- update of dependency jobj-core to new version 8
- update of test dependency equalsverifier to new version 3.15.1
- update of test dependency test-ng to new version 7.8.0

Version 9
-------------

ADDED:

- new dependency io.github.astrapi69:jobj-reflect in first major version 1

CHANGED:

- update of jdk to version 11
- update of gradle to new version 8.1.1
- update of gradle-plugin dependency of 'com.diffplug.spotless:spotless-plugin-gradle' in version 6.18.0
- update of dependency jobj-core to new version 7.1
- update of dependency silly-collections to new version 21
- update of dependency silly-math to new version 2.2
- update of test dependency equalsverifier to new version 3.14.1
- update of test dependency test-object to new minor version 7.2
- update of test dependency silly-strings to new version 8.2
- update of test dependency test-ng to new version 7.7.1

Version 8.6
-------------

ADDED:

- new method for generated a random neighbor Point around a given Point
- new method for generated a random neighbor Point around a given Point that allows negative values for x or y
- new gradle-plugin dependency 'com.diffplug.spotless:spotless-plugin-gradle' in minor version 6.5.0
- extracted sections from build.gradle to own gradle files for clearness

CHANGED:

- update of gradle to new version 7.4.2
- update of com.github.ben-manes.versions.gradle.plugin to new version 0.42.0
- update of dependency silly-collections to new version 18.2
- update of test dependency equalsverifier to new version 3.10
- update of test dependency test-ng to new version 7.5
- update of test dependency test-objects to new major version 6
- update of dependency jobj-copy to new version 3.7

Version 8.5
-------------

CHANGED:

- united all modules back to one module
- update of dependency jobj-core to new version 5.2
- update of test dependency equalsverifier to new version 3.8.2

Version 8.4
-------------

ADDED:

- new dependency jobj-copy in new version 3.4
- new method for create random object from a given object in factory class RandomObjectFactory
- new gradle-plugin dependency of 'org.ajoberstar.grgit:grgit-gradle' in version 4.4.1 for create git release tags


CHANGED:

- update of gradle to new version 7.3.3
- update of com.github.ben-manes.versions.gradle.plugin to new version 0.40.0
- update of dependency silly-collections to new version 18
- update of dependency vintage-time to new version 5.4
- update of dependency jobj-core to new version 5.1
- update of test dependency test-objects to new version 5.7
- update of test dependency silly-strings to new version 8.1
- update of test dependency equalsverifier to new version 3.8.1

Version 8.3
-------------

ADDED:

- new characters to the enum RandomCharacters class

CHANGED:

- update of gradle to new version 7.1
- changed all dependencies from groupid de.alpharogroup to new groupid io.github.astrapi69
- update gradle-plugin dependency of gradle.plugin.com.hierynomus.gradle.plugins:license-gradle-plugin to new version 0.16.1

Version 8.2
-------------

ADDED:

- new factory classes for generate long random string object
- new random characters values in enum class RandomCharacters

CHANGED:

- update of gradle to new version 6.9
- update of silly-collections to new version 8.6
- update of dependency jobj-core to new version 3.7
- update of test dependency test-objects to new version 5.4
- update of test dependency equalsverifier to new version 3.6.1
- update of com.github.ben-manes.versions.gradle.plugin to new version 0.39.0

Version 8.1
-------------

ADDED:

- new method in the SecureRandomBuilder with the seed argument as Date object

CHANGED:

- update of gradle to new version 6.8.3
- update of silly-collections to new version 8.4
- update of test dependency equalsverifier to new version 3.5.5
- moved to new package io.github.astrapi69

Version 8
-------------

ADDED:

- new factory classes for every primitive type created

CHANGED:

- update of silly-collections to new version 8.3
- deleted RandomPrimitivesFactory and RandomPrimitivesExtensions
- moved all factory methods from extension classes to factory classes and deleted extension classes

Version 6.9
-------------

CHANGED:

- update of gradle to new version 6.6.1
- update of silly-collections to new version 8.2
- update of silly-math to new version 1.3
- update of dependency jobj-core to new version 3.6
- update of test dependency testng to new version 7.3.0
- update of test dependency equalsverifier to new version 3.4.3
- extracted project properties to gradle.properties
- removed deprecated methods for Timestamp in class RandomDateExtensions
- removed deprecated methods for create random double and float between the range from start to end  in class RandomPrimitivesExtensions
- removed deprecated methods for create random String object in class RandomExtensions


Version 6.8
-------------

ADDED:

- new seed field in the SecureRandomBuilder class for init with a long from a Date object, for instance for a draw date of a lottery game
- new class RandomPrimitivesFactory that generates random primitives with SecureRandom argument
- new class RandomDateFactory that generates random date objects with SecureRandom argument
- new class RandomStringFactory that generates random String objects

CHANGED:

- update of test dependency testng to new version 7.1.1

Version 6.7
-------------

CHANGED:

- update of dependency vintage-time to new version 5.2
- update of dependency jobj-core to new version 3.5
- update of dependency silly-beans to new version 1.1
- update of dependency silly-math to new version 1.1
- update of dependency silly-strings to new version 5.5
- update of test dependency equalsverifier to new version 3.1.12
- update of test dependency test-objects to new version 5.3
- removed lombok dependency

Version 6.6
-------------

CHANGED:

- build system to gradle migrated and transformed to multi-module gradle project

Version 6.5
-------------

ADDED:

- new module project for address related classes

CHANGED:

- update of parent to new version 5.4
- moved all address related classes and files to its own module project
- update of silly-collections to new version 5.8
- update of silly-strings to new version 5.4
- update of equalsverifier to new version 3.1.11

Version 6.4
-------------

CHANGED:

- update of parent to new version 5.3
- update of silly-collections to new version 5.5.1
- update of jobj-core to new version 3.3
- update of equalsverifier to new version 3.1.10
- bug in RandomPrimitivesExtensions.randomIntBetween fixed and unit tests extended

Version 6.3
-------------

ADDED:

- new method to create random numbers over the RandomAlgorithm for the primitive types int, long, float and double
- unit tests for the new methods created

CHANGED:

- removed deprecated methods from RandomExtensions and RandomObjectsExtensions
- code coverage increased to 100 percent

Version 6.2
-------------

ADDED:

- new random method created for get a random int between the given range generated with with the Math.abs method.
- new class RandomNumberExtensions for generate random number objects
- new class RandomPrimitivesExtensions for generate random primitive type values
- new enum for define the random algorithm created

CHANGED:

- removed deprecated method from SecureRandomBean and added new unit tests for it
- refactored RandomExtensions to the appropriate named classes
- moved RandomObjectFactory and RandomObjectsExtensions moved to the appropriate package

Version 6.1
-------------

ADDED:

- new methods created for create random object from java.time package
- changed jdk from oraclejdk8 to openjdk8 in .travis.yml
- code coverage increased to 100 percent

CHANGED:

- build method from SecureRandomBuilder throws no more checked exceptions

Version 6.0.1
-------------

ADDED:

- new method for create random Date without arguments
- new method for set random values to an existing object

CHANGED:

- removed deprecated class DataGenerator
- adapted method newRandomValue for type Date
- update of jobj-core to new version 3.2.1

Version 6
-------------

CHANGED:

- update of parent to new version 5
- update of jobj-core to new version 3.2

Version 5.8.3
-------------

ADDED:

- new dependency silly-strings in test scope

CHANGED:

- remove of dependency resourcebundle-inspector
- update of test-objects to new version 5.2

Version 5.8.2
-------------

CHANGED:

- update of test-objects to new version 5.0.1
- update of silly-collections to new version 5.2.1
- remove unused dependency jcommons-lang

Version 5.8.1
-------------

CHANGED:

- update of jcommons-lang to new version 5.2.1
- update of jobj-core to new version 3.1

Version 5.8
-------------

ADDED:

- new factory class for create new random objects with optional exclude fields
- new factory class for create new SecureRandom objects
- new methods created for random BigInteger and BigDecimal

Version 5.7
-------------

ADDED:

- new dependency silly-math in version 1 added
- new method for create random object over reflection
- new method for create random string with default parameter
- new method for create random short without arguments
- new method for create random double without arguments
- new method for create random UUID objects

CHANGED:

- update of parent to new version 4.8
- update of jcommons-lang to new version 5.2
- update of test-objects to new version 5.1
- update of silly-collections to new version 5.2
- replaced obsolet dependency jobject-core with new dependency jobj-core in version 3
- replaced obsolet dependency jobject-evaluate with new dependency jobj-contract-verifier in version 3

Version 5.6
-------------

CHANGED:

- removed deprecated Constant class
- update of jobject-extensions to new version 2.5
- update of jcommons-lang to new version 5
- update of silly-collections to new version 4.35
- update of resourcebundle-inspector to new version 3
- remove of logging dependencies

Version 5.5
-------------

ADDED:

- new method created for create random hexadecimal string objects from a given length of characters
- new enum class created that will replace the Constant class

CHANGED:

- update of jobject-extensions to new version 2
- update of jcommons-lang to new version 5
- update of silly-collections to new version 4.35
- update of resourcebundle-inspector to new version 3

Version 5.4
-------------

CHANGED:

- update of parent to new version 4
- update of jcommons-lang to new version 4.34
- update of test-objects to new version 4.28
- update of silly-collections to new version 4.31
- update of vintage-time to new version 4.12
- update of resourcebundle-inspector to new version 2.22
- update of jobject-extensions to new version 1.11

Version 5.3
-------------

CHANGED:

- update of parent to new version 3.11
- removed unneeded .0 at the end of version
- update of resourcebundle-inspector to new version 2.21
- update of jcommons-lang to new version 4.31.0
- update of silly-collections to new version 4.28.0

Version 5.2.0
-------------

ADDED:

- new method that can include and exclude the start and the end number from randomIntBetween

CHANGED:

- update dependency of jobject-extensions from 1.9.2 to 1.10.0
- code coverage increased to 100 percent

Version 5.1.0
-------------

ADDED:

- initial version
- moved all random relevant projects from mystic-crypt to this project

Notable links:
[keep a changelog](http://keepachangelog.com/en/1.0.0/) Don’t let your friends dump git logs into changelogs
