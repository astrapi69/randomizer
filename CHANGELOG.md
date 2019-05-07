## Change log
----------------------

Version 5.9-SNAPSHOT
-------------

Version 5.8.1
-------------

CHANGED:

- update of jcommons-lang version to 5.2.1
- update of jobj-core version to 3.1

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

- update of parent version to 4.8
- update of jcommons-lang version to 5.2
- update of test-objects version to 5.1
- update of silly-collections version to 5.2
- replaced obsolet dependency jobject-core with new dependency jobj-core in version 3
- replaced obsolet dependency jobject-evaluate with new dependency jobj-contract-verifier in version 3 

Version 5.6
-------------

CHANGED:

- removed deprecated Constant class
- update of jobject-extensions version to 2.5
- update of jcommons-lang version to 5
- update of silly-collections version to 4.35
- update of resourcebundle-inspector version to 3
- remove of logging dependencies

Version 5.5
-------------

ADDED:
 
- new method created for create random hexadecimal string objects from a given length of characters
- new enum class created that will replace the Constant class

CHANGED:

- update of jobject-extensions version to 2
- update of jcommons-lang version to 5
- update of silly-collections version to 4.35
- update of resourcebundle-inspector version to 3

Version 5.4
-------------

CHANGED:

- update of parent version to 4
- update of jcommons-lang version to 4.34
- update of test-objects version to 4.28
- update of silly-collections version to 4.31
- update of vintage-time version to 4.12 
- update of resourcebundle-inspector version to 2.22
- update of jobject-extensions version to 1.11

Version 5.3
-------------

CHANGED:

- update of parent version to 3.11
- removed unneeded .0 at the end of version
- update of resourcebundle-inspector version to 2.21
- update of jcommons-lang version to 4.31.0
- update of silly-collections version to 4.28.0

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