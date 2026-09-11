# Decision: Tabler Preview Reference Scope

## Status

Accepted.

## Decision

The primary goal of the library is to provide composable Tabler components and
elements that can recreate most of the captured `preview.tabler.io` reference
pages.

The reference markup and styling combinations define the supported scope. When
the reference uses a component with a particular structural combination, the
library should reproduce that combination. For example, when the reference
uses a footer together with `container-xl`, the library should provide and use
that same composition.

The library does not need to support every theoretically possible combination
of Tabler classes or styles. Combinations that are not demonstrated by the
reference are out of scope unless they are later added explicitly to the
reference scope.

This reference scope is part of the Definition of Done for the composable
components and elements: an implementation is complete when the supported
reference composition can be recreated with the library's composable API and
matches the reference's relevant structure and styling behavior.

## Rationale

Tabler exposes a very large class surface, and unrestricted combinations would
make the library difficult to implement, test, and maintain. Using the
captured preview as the compatibility boundary keeps the API focused on real
Tabler compositions while still covering the layouts and components users
actually need to reproduce.
