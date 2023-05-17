# Java RNG reader

A project to build tests for the [`libgdx-xs128`](https://crates.io/crates/libgdx-xs128).

## Assumptions

You have `gradle` and the `Java Development Kit` installed.

## Usage

Run this application with a seed followed by up to `16` arguments.
Arguments can be

- a positive integer (which will yield a `CappedU64` result),
- a non-positive number (which will yield a `U64` result),
- or a string starting with `'a'` followed by a number (which will yield an `Advance` result).

The remaining values in the 16-value array will be `None`.

For example,

```bash
gradle run --args="12345 0 a10 134 -1"
```

creates a random number generator with seed `12345`, produces one `u64`, advances `10` times, generates a `u64` modulo `134`, then another `u64`, then nothing.

It prints

```rust
RngValues {
    seed: 12345i64 as u64,
    values: [Some(RngValue::U64(1382432690769144372i64 as u64)), Some(RngValue::Advance(10)), Some(RngValue::CappedU64 { modulus: 134, residue: 83 }), Some(RngValue::U64(-5355119237153046436i64 as u64)), None, None, None, None, None, None, None, None, None, None, None, None, ],
}
```

to the terminal.
