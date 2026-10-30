# Security Policy

`randomizer` generates random test data: numbers, strings, dates, objects. It is
**not meant for secrets**. For passwords, keys, salts, nonces or tokens, use
`java.security.SecureRandom` directly; a library built for convenience in tests
is the wrong place to get them from, and the advisory below is what that costs
when it goes wrong.

## Supported Versions

Only the current minor line receives security fixes. This is a single-maintainer
project, and supporting several lines at once is a promise it could not keep.

The table names **lines, not releases**: the top row is the supported one, and a
release inside it changes nothing here.

| Version | Supported          | Required JDK |
|---------|--------------------|--------------|
| 10.x    | :white_check_mark: | 17 and above |
| < 10    | :x:                |              |

If you are on an older line, the fix for a reported vulnerability will be an
upgrade to the current one rather than a backport.

## Reporting a Vulnerability

**Do not open a public issue.** Use GitHub's private vulnerability reporting,
which is enabled on this repository:

[Report a vulnerability](https://github.com/astrapi69/randomizer/security/advisories/new)

Please include the version, the method you called, and a way to reproduce it; a
failing test is ideal, since this project fixes bugs test-first and yours would
become the regression guard.

This is a spare-time project, so no response time is promised. What is promised
instead: a reply saying whether it is reproducible, and if it is, a fixed release
and an advisory crediting you unless you prefer otherwise.

## Published advisories

- [GHSA-395w-fmf5-xfj3](https://github.com/astrapi69/randomizer/security/advisories/GHSA-395w-fmf5-xfj3):
  before 10.5, a `SecureRandom` built without an explicit seed was seeded with the
  current time in milliseconds, so its whole output followed from the moment it
  was built. That covered `DefaultSecureRandom`, behind every method without a
  `SecureRandom` parameter. Fixed in 10.5.
