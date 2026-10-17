# Mutation testing

Coverage says which lines the tests run. Mutation testing says whether they would notice
a change in what those lines do: [PIT](https://pitest.org) changes the compiled code one
small step at a time (a `<` becomes `<=`, a `+` becomes `-`, a return value becomes 0)
and runs the tests against each change, the mutant. A mutant the tests fail on is killed;
one they pass survives.

## Setup

| What | Value |
| --- | --- |
| Tool | PIT 1.30.0, pitest-junit5-plugin 1.2.3, gradle-pitest-plugin 1.19.0 |
| Mutators | `DEFAULTS` (named in `gradle/mutation-testing.gradle`) |
| Scope | `io.github.astrapi69.random.*`, all of `src/main/java` |
| Tests | the JUnit Jupiter tests and the jqwik properties, both on the JUnit Platform |
| Floor | `mutationThreshold = 70` in `build.gradle` |
| CI | `.github/workflows/gradle.yml` runs `./gradlew pitest` after the build and keeps `mutation-context.json` as an artifact |

PIT's JUnit 5 plugin discovers the jqwik properties like any other test: a mutant a
property kills carries a `killingTest` of the form
`[engine:jqwik]/[class:...]/[property:...]` in `mutations.xml`.

PIT runs the properties without shrinking (`jvmArgs = ["-Djqwik.shrinking.default=OFF"]`
in `gradle/mutation-testing.gradle`): when a mutant makes a property fail, jqwik would
rerun it many times to find a smaller failing input, which can outlast PIT's timeout and
turn a killed mutant into a timed out one. `./gradlew test` still shrinks.

## How to run

```
./gradlew pitest --rerun
```

`gradle.properties` switches the Gradle build cache on, and the `pitest` task is
cacheable: without `--rerun` an unchanged build restores the last report from the cache
instead of measuring. The report lands in `build/reports/pitest/index.html` (by class and
line) and `build/reports/pitest/mutations.xml` (one element per mutant with its status and
the test that killed it); the log ends with `Generated N mutations Killed K (P%)`. A run
takes about 25 seconds.

## Properties instead of samples

Most tests in this library were written as samples: draw a value, check that it is not
null. Such a test passes for a generator that returns 0, ignores its range or forgets a
bound. The tests added for mutation testing state properties instead: what every value
must keep, for every input [jqwik](https://jqwik.net) generates.

- **Every value**: lies in the range, has the length, consists of the characters asked
  for, lies between the dates.
- **The values together**: spread over the range (one below its middle, one above), do
  not repeat, reach every value of a small range and none outside it, the same seed gives
  the same sequence.
- **Every input of the random source**: `StubSecureRandom` hands a generator one given
  `nextInt()` or `nextLong()`, so a property can try `Integer.MIN_VALUE`, which a real
  generator draws once in 2^32 calls.

Everything is reproducible:

- Every property names the fixed seed `Seeds.SEED`; the inputs are the same on every run
  and for every mutant.
- A generator that takes a `SecureRandom` gets a SHA1PRNG seeded from a value jqwik
  generated (`Seeds.secureRandom(long)`), so it draws the same values on every run.
- `src/test/resources/junit-platform.properties` switches jqwik's test run database off
  (with it, a failed run changes what the next one tries first) and sets 50 tries per
  property.
- The overloads without a `SecureRandom` draw from `DefaultSecureRandom` or
  `Math.random()`, which no seed reaches. Their properties are of the second kind, and each
  names the probability that a correct generator fails it; it stays below 10^-18 (for
  example `2 * (2/3)^200` for "a value on each side of the middle in 200 draws"). The
  mutants they catch, a constant 0 or a bound off by one, fail them on every run.

## Measured

On 2026-10-17, PIT 1.30.0, `DEFAULTS`. Strict score: killed / all mutants; timed out and
no coverage count against it. PIT's own score counts a timeout as detected.

| | Mutants | Killed | Survived | No coverage | Timed out | Strict | PIT |
| --- | ---: | ---: | ---: | ---: | ---: | ---: | ---: |
| Before (`develop` at c3d3036) | 362 | 189 | 150 | 20 | 3 | 52.2 % | 53.0 % |
| After, run 1 | 367 | 345 | 17 | 2 | 3 | 94.0 % | 94.8 % |
| After, run 2 | 367 | 345 | 17 | 2 | 3 | 94.0 % | 94.8 % |
| After, run 3 | 367 | 345 | 17 | 2 | 3 | 94.0 % | 94.8 % |

The three runs agree mutant by mutant. Before, the same commit measured 189 to 193 killed
between runs. The number of mutants changed with the bugs the properties found and the
fixes that came with them.

By class, mutants not killed (survived, no coverage, timed out) before and after:

| Class | Before | After |
| --- | ---: | ---: |
| RandomObjectFactory | 44 of 90 | 1 of 90 |
| RandomIntFactory | 18 of 37 | 0 of 39 |
| RandomWebObjectFactory | 16 of 25 | 0 of 23 |
| RandomFloatFactory | 13 of 19 | 0 of 19 |
| RandomLongFactory | 13 of 18 | 2 of 24 |
| RandomDateFactory | 10 of 35 | 4 of 35 |
| RandomDoubleFactory | 10 of 18 | 0 of 18 |
| SecureRandomBean | 9 of 31 | 6 of 31 |
| RandomByteFactory | 5 of 8 | 1 of 8 |
| RandomCharFactory | 5 of 6 | 0 of 7 |
| RandomShortFactory | 5 of 5 | 1 of 5 |
| SecureRandomBuilder | 5 of 15 | 0 of 15 |
| RandomStringFactory | 4 of 15 | 3 of 15 |
| SecureRandomBean.SecureRandomBeanBuilder | 4 of 5 | 0 of 5 |
| RandomBooleanFactory | 4 of 4 | 0 of 4 |
| RandomBigIntegerFactory | 2 of 6 | 0 of 4 |
| PropertiesLoader | 2 of 5 | 2 of 5 |
| RandomAddressExtensions | 2 of 4 | 0 of 4 |
| RandomNumberExtensions | 1 of 6 | 1 of 6 |
| RandomBigDecimalFactory | 1 of 4 | 1 of 4 |

## The mutants left

None of them is hidden by an exclude; they stay in the score. Line numbers as of the
commit that added this page.

| Class | Method, line | Mutant | Status | Why it stays |
| --- | --- | --- | --- | --- |
| SecureRandomBean | `hashCode`, 111, 113, 115 | `*` to `/` and `+` to `-`, six mutants | survived | Equal beans keep equal hash codes, all the contract asks; the formula itself (Lombok's) is not specified, and pinning it would only freeze it. The test that the upper 32 bits of the seed count kills the seventh. |
| RandomDateFactory | `randomDate(Date, SecureRandom)`, 211, 212 | negation removed, `-` to `+` | survived | Its documentation says "a random date" and "the date from where to begin"; what the formula `(-d1 * t / 99999) * ((t - d2) / 99999)` yields (a date long before 1970) is described nowhere, so no property can say what it should be. |
| RandomDateFactory | `randomLocalDate`, 357 | coin flip removed | survived | Equivalent: both branches move today by a number of days drawn from a range symmetric around 0, adding or subtracting it, so always taking one branch gives the same distribution. |
| RandomDateFactory | `randomLocalTime`, 389 | coin flip removed | survived | Both branches move the current time by up to 22:58:58, forwards or backwards around the clock; either alone reaches nearly every time of day, and which distribution the method should have is not documented. |
| RandomLongFactory | `randomLongBetween(long, long, SecureRandom)`, 180 | `<` to `<=`, twice | survived | Equivalent: `start < end && width < 0` and `start <= end && width <= 0` differ only for `start == end`, where the width is 0 and both take the same path. |
| RandomByteFactory | `randomByteArray(int, SecureRandom)`, 98 | coin flip removed | survived | Equivalent in what can be observed: both branches draw a uniform byte (one through `randomByte`, one through `nextBytes`), and which one drew a byte cannot be told from the byte. |
| RandomShortFactory | `randomShort(SecureRandom)`, 58 | `- 32768` to `+ 32768` | survived | Equivalent: the cast to short wraps both into the same range with the same distribution. |
| RandomStringFactory | `randomHexString`, 158 | `<` to `<=` | survived | Equivalent: one more chunk is appended and cut off again by `substring(0, numberOfCharacters)`. |
| RandomObjectFactory | `setRandomValues`, 296 | `toIgnoreFields.contains(...)` replaced with false | survived | Equivalent: `ReflectionExtensions.getAllDeclaredFields(cls, ignoreFieldNames)` already leaves the ignored fields out. |
| RandomNumberExtensions | `getRandomNumericString()`, 70 | `<` to `<=` | survived | Its length is random and unspecified; one more number appended keeps it a numeric string. |
| PropertiesLoader | `loadProperties`, 65, 68 | fallback branch | no coverage | Unreachable in practice: the fallback asks the same class loader for the same resource (`ClassExtensions.getResourceAsStream`, the name still with its leading slash, which class loaders do not resolve) after `getResource` found nothing under it. |
| RandomBigDecimalFactory | `randomBigDecimal(int, int)`, 85 | loop condition removed | timed out | The mutant turns the retry loop into an endless one; a timeout is the only way it shows. |
| RandomStringFactory | `newRandomLongString(String, long)`, 125; `newRandomString(String, int)`, 109 | `i++` to `i--`; `""` returned | timed out | The first loops forever; the second makes `randomBigDecimal(int, int)` retry forever, as its loop waits for more than ".". |

## Behaviour no property pins

Where the documentation says nothing, the properties say nothing either:

- invalid arguments: a range of 0 or below, a start after the end, a negative length;
- `randomBigDecimal(0, 0)` never returns (it retries while the number string is ".");
- `newRandomString(int)` cuts the length at 1024;
- `randomDate(Date)` and `randomDate(Date, SecureRandom)` compute a date nobody described
  (see the table above).
