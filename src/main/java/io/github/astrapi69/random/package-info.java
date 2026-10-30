/**
 * This package contains helper classes for random objects like numbers or String and a builder for
 * SecureRandom object.
 * <p>
 * <b>randomizer generates random test data. It is not meant for secrets.</b> For passwords, keys,
 * salts, nonces, tokens or anything else that has to stay unpredictable, use
 * {@link java.security.SecureRandom} directly. The {@code SecureRandom} in the names of
 * {@link io.github.astrapi69.random.DefaultSecureRandom},
 * {@link io.github.astrapi69.random.SecureRandomFactory} and
 * {@link io.github.astrapi69.random.SecureRandomBuilder} names the generator class, not a promise
 * that what is drawn through them may be used for cryptography: before 10.5 their default generator
 * was seeded with the current time in milliseconds (GHSA-395w-fmf5-xfj3).
 */
package io.github.astrapi69.random;