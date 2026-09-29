package com.github.jangalinski.kobweb.tabler._foundation

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier

data object Tabler {

  interface TablerType

  /**
   * Controls runtime behavior, dynamic animations, and interactive states, for example
   * pulsing animations (`badge-blink`, `icon-pulse`), loading states (`btn-loading`),
   * or interactive rows and headers (`table-hover`, `table-sort`).
   */
  interface TablerBehavior

  /**
   * Applies palette colors, theme tints, gradients, or semantic color accents,
   * for example contextual variants (`alert-{color}`, `btn-{color}`),
   * background tints (`bg-{color}`, `steps-{color}`), or trend indicators
   * (`text-green`, `text-red`).
   */
  interface Color : Modifier, Supplier<String> {

    /**
     * ClassName value used to apply the color to an element.
     */
    val value: String

    /**
     * Short initials used to represent the color, for example in a legend or key.
     */
    val initials: Initials

    /**
     * Display name of the color, for example "Azure" or "Red".
     */
    val displayName: String

    override fun get() = value
  }

  /**
   * A functional interface for a composable component that can be invoked with or without a [Modifier].
   * Marks a tabler-component.
   *
   * Defines the base root container or foundational element of a UI component, for example
   * standalone elements (`accordion`, `card`, `btn`, `modal`, `table`).
   */
  fun interface Component : ComposableType, TablerType {

    @Composable
    operator fun invoke(modifier: Modifier)

    @Composable
    operator fun invoke() = invoke(Modifier)
  }

  fun interface FoundationComponent : Component

  /**
   * Specifies alignment, placement edges, opening direction, or layout orientation,
   * for example edge placement (ribbon-top, offcanvas-start), text or menu
   * alignment (hr-text-start, dropdown-menu-end),
   * or vertical stacking (steps-vertical, nav-segmented-vertical).
   */
  interface Direction

  /**
   * Modifies layout flow, geometry, positioning, or responsive layout behavior,
   * for example geometric shapes (badge-pill, btn-square),
   * stacking and tilting (avatar-list-stacked, card-stacked),
   * or responsive layouts (table-responsive, modal-fullscreen).
   */
  interface TablerModifier

  /**
   * Designates internal structural elements, child sub-components, or content slots within a composite component hierarchy,
   * for example sub-elements (card-header, accordion-body, modal-dialog, dropdown-item).
   */
  interface Part {
  }


  /**
   * Scales component dimensions, padding, thickness, or aspect ratios, for example sizing scale variants (btn-sm, badge-lg, modal-xl),
   * track thickness (progress-lg), or aspect ratios (ratio-{ratio}).
   */
  interface Size : Modifier


  /**
   * Configures visual styling variants, border treatments, fills, backgrounds, or decorative presentations,
   * for example outlines and ghost buttons (btn-outline, badge-outline, btn-ghost),
   * alternate fills and borders (table-striped, card-dashed, alert-important),
   * or backdrop effects (modal-blur).
   */
  interface Style : Modifier

  /**
   * A functional interface that represents a supplier of results.
   *
   * This interface is used to provide a way to generate or supply values on demand.
   *
   * @param T the type of results supplied by this supplier
   */
  fun interface Supplier<T> {
    fun get(): T
  }
}

/**
 * Marks Tabler DSL receivers so nested layout blocks stay scoped to Tabler-specific builders.
 */
@DslMarker
annotation class TabwebDsl
