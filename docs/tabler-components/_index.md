# Tabler Components

- [Accordion](accordion.md)
- [Alert](alert.md)
- [Avatar](avatar.md)
- [Badge](badge.md)
- [Breadcrumb](breadcrumb.md)
- [Button](button.md)
- [Card gradient](card-gradient.md)
- [Card](card.md)
- [Carousel](carousel.md)
- [Chat](chat.md)
- [Data grid](datagrid.md)
- [Divider](divider.md)
- [Dropdown](dropdown.md)
- [Empty state](empty.md)
- [Icon](icon.md)
- [List group](list-group.md)
- [Map](map.md)
- [Mention](mention.md)
- [Modal](modal.md)
- [Offcanvas](offcanvas.md)
- [Pagination](pagination.md)
- [Placeholder](placeholder.md)
- [Popover](popover.md)
- [Progress steps](progress-step.md)
- [Progress bar](progress.md)
- [Ribbon](ribbon.md)
- [Segmented control](segmented-control.md)
- [Spinner](spinner.md)
- [Star rating](star-rating.md)
- [Status](status.md)
- [Step](step.md)
- [Switch icon](switch-icon.md)
- [Tab](tab.md)
- [Table](table.md)
- [Tag](tag.md)
- [Timeline](timeline.md)
- [Toast](toast.md)
- [Tooltip](tooltip.md)
- [Tracking](tracking.md)
- [Trending](trending.md)

## Component types

Complete list of all extracted type names:

| Name | Usages | Description |
| --- | --- | --- |
| [`behavior`](#behavior) | 27 | Controls runtime behavior, dynamic animations, and interactive states, for example pulsing animations (`badge-blink`, `icon-pulse`), loading states (`btn-loading`), or interactive rows and headers (`table-hover`, `table-sort`). |
| [`color`](#color) | 19 | Applies palette colors, theme tints, gradients, or semantic color accents, for example contextual variants (`alert-{color}`, `btn-{color}`), background tints (`bg-{color}`, `steps-{color}`), or trend indicators (`text-green`, `text-red`). |
| [`component`](#component) | 41 | Defines the base root container or foundational element of a UI component, for example standalone elements (`accordion`, `card`, `btn`, `modal`, `table`). |
| [`direction`](#direction) | 15 | Specifies alignment, placement edges, opening direction, or layout orientation, for example edge placement (`ribbon-top`, `offcanvas-start`), text or menu alignment (`hr-text-start`, `dropdown-menu-end`), or vertical stacking (`steps-vertical`, `nav-segmented-vertical`). |
| [`modifier`](#modifier) | 28 | Modifies layout flow, geometry, positioning, or responsive layout behavior, for example geometric shapes (`badge-pill`, `btn-square`), stacking and tilting (`avatar-list-stacked`, `card-stacked`), or responsive layouts (`table-responsive`, `modal-fullscreen`). |
| [`part`](#part) | 126 | Designates internal structural elements, child sub-components, or content slots within a composite component hierarchy, for example sub-elements (`card-header`, `accordion-body`, `modal-dialog`, `dropdown-item`). |
| [`size`](#size) | 30 | Scales component dimensions, padding, thickness, or aspect ratios, for example sizing scale variants (`btn-sm`, `badge-lg`, `modal-xl`), track thickness (`progress-lg`), or aspect ratios (`ratio-{ratio}`). |
| [`style`](#style) | 49 | Configures visual styling variants, border treatments, fills, backgrounds, or decorative presentations, for example outlines and ghost buttons (`btn-outline`, `badge-outline`, `btn-ghost`), alternate fills and borders (`table-striped`, `card-dashed`, `alert-important`), or backdrop effects (`modal-blur`). |

## behavior

| Name | Description |
| --- | --- |
| `badge-blink` | Pulsing animation, for a live counter |
| `btn-loading` | Hides the label and draws a spinner; pair it with disabled and aria-busy |
| `btn-animate-icon` | Animates the icon on hover, with -pulse, -rotate, -shake, -tada or -move-start |
| `card-gradient-animated` | Slowly shifts the gradient |
| `card-active` | Marks the card as selected |
| `card-inactive` | Dims the card |
| `card-link` | Whole card is a link; -pop and -rotate add a hover effect |
| `carousel-fade` | Crossfades between slides instead of sliding |
| `dropdown-menu-scrollable` | Scrolls a long menu instead of growing it |
| `icon-pulse` | Pulsing animation |
| `icon-tada` | Shake animation |
| `icon-rotate` | Continuous rotation |
| `list-group-hoverable` | Highlights the entry under the cursor |
| `list-group-item-action` | Marks an entry as a link or a button |
| `placeholder-glow` | Fades the placeholders inside it, applied to the wrapper |
| `placeholder-wave` | Sweeps a highlight across them, applied to the wrapper |
| `progress-bar-animated` | Animates the stripes |
| `progress-bar-indeterminate` | Looping animation for an unknown amount of progress |
| `status-dot-animated` | Pulsing dot, for a live state |
| `status-indicator-animated` | Pulsing indicator |
| `switch-icon-fade` | Crossfades between the icons |
| `switch-icon-flip` | Flips the icon over |
| `switch-icon-scale` | Scales one icon out and the other in |
| `switch-icon-slide-{direction}` | Slides the icons; up, down, left, right, start or end |
| `table-hover` | Highlights the row under the cursor |
| `table-selectable` | Rows with a checkbox, highlighted when checked |
| `table-sort` | Sortable header cell |

## color

| Name | Description |
| --- | --- |
| `alert-{color}` | Any base color, for example alert-success, alert-blue or alert-muted |
| `bg-{color}` | Any base color for an avatar with initials, for example bg-azure-lt |
| `bg-{color}` | Any base color, for example bg-green or bg-red |
| `btn-{color}` | Any base or social color, for example btn-primary or btn-facebook |
| `card-gradient-{color}` | Any base color as the gradient start |
| `card-gradient-start` | Sets the first color stop with a CSS custom property |
| `card-gradient-end` | Sets the second color stop |
| `text-{color}` | Any base color for a labelled divider |
| `list-group-item-{color}` | Tints one entry, for example list-group-item-success |
| `bg-{color}` | Any base color on the bar, for example bg-success |
| `bg-{color}` | Any base color, for example bg-red or bg-green |
| `text-{color}` | Any base color, for example text-primary |
| `status-{color}` | Any base color, for example status-green or status-danger |
| `steps-{color}` | Any base color for the completed steps; -lt variants use the soft tint |
| `table-{color}` | Tints a row or a cell, for example table-success |
| `toast-{color}` | Any base color, for example toast-success or toast-azure |
| `text-green` | Rising value, paired with an arrow-up icon |
| `text-red` | Falling value, paired with an arrow-down icon |
| `text-muted` | No change, paired with a minus icon |

## component

| Name | Description |
| --- | --- |
| `accordion` | Container element |
| `alert` | Container element |
| `avatar` | Container element |
| `badge` | Container element |
| `breadcrumb` | Container element, an ordered list |
| `btn` | Container element, on a button or a link |
| `card-gradient` | Applied with card, fills the card with a gradient |
| `card` | Container element |
| `carousel` | Container element |
| `chat` | Container element |
| `datagrid` | Container element, a responsive grid of label and value pairs |
| `hr` | Applied to the hr element |
| `dropdown` | Wrapper around the trigger and the menu |
| `empty` | Container element |
| `icon` | Applied to the svg element |
| `list-group` | Container element |
| `ratio` | Wrapper that keeps the embed at a fixed aspect ratio |
| `mention` | The chip itself, on a span or on an a |
| `modal` | Container element, hidden until Bootstrap opens it |
| `offcanvas` | Container element, hidden until Bootstrap opens it |
| `pagination` | Container element, an unordered list |
| `placeholder` | Grey block standing in for content that is still loading |
| `popover` | Container element, positioned by Bootstrap |
| `progress-steps` | Container element |
| `progress` | Track element |
| `ribbon` | Container element, positioned over its parent |
| `nav-segmented` | Applied together with nav, turns it into a segmented control |
| `spinner-border` | Rotating ring |
| `spinner-grow` | Pulsing dot, a quieter alternative |
| `stars` | Container element, holding one icon per star |
| `status` | Container element, a label with a marker |
| `steps` | Container element |
| `switch-icon` | Button that swaps between two icons |
| `nav-tabs` | Applied with nav, styles the triggers as tabs |
| `table` | Container element |
| `tag` | Container element |
| `timeline` | Container element |
| `toast` | Container element |
| `tooltip` | Container element, positioned by Bootstrap |
| `tracking` | Container element |
| `d-inline-flex align-items-center lh-1` | Utilities that lay out the value and its arrow on one line |

## direction

| Name | Description |
| --- | --- |
| `card-gradient-bottom` | Starts the gradient at the bottom edge |
| `hr-text-start` | Moves the label to the leading edge |
| `hr-text-end` | Moves the label to the trailing edge |
| `dropdown-menu-end` | Aligns the menu to the trailing edge; -{breakpoint}- variants apply above a width |
| `dropend` | Opens a nested menu to the side, for a submenu |
| `list-group-horizontal` | Lays the entries out in a row; -{breakpoint} only above that width |
| `offcanvas-start` | Slides in from the leading edge |
| `offcanvas-end` | Slides in from the trailing edge |
| `offcanvas-top` | Slides in from the top |
| `offcanvas-bottom` | Slides in from the bottom |
| `ribbon-top` | Places the ribbon along the top edge |
| `ribbon-bottom` | Places the ribbon along the bottom edge |
| `ribbon-start` | Places the ribbon on the leading edge |
| `nav-segmented-vertical` | Stacks the segments in a column |
| `steps-vertical` | Stacks the steps in a column |

## modifier

| Name | Description |
| --- | --- |
| `accordion-flush` | Removes the outer border and rounding, to sit flush in a parent |
| `accordion-inverted` | Moves the toggle indicator to the start of the header |
| `accordion-tabs` | Styles the headers as a row of tabs |
| `avatar-list-stacked` | Overlaps the avatars in the list |
| `badge-pill` | Fully rounded ends |
| `btn-pill` | Fully rounded ends |
| `btn-square` | Sharp corners |
| `btn-icon` | Square button holding only an icon; it still needs an aria-label |
| `btn-floating` | Pins the button to the bottom corner of the viewport |
| `card-stacked` | Draws a second card behind this one |
| `card-rotate-start` | Tilts the card; also -end, -left and -right |
| `card-body-scrollable` | Scrolls the body instead of growing the card |
| `hr-text-spaceless` | Removes the vertical margin |
| `modal-dialog-centered` | Centers the dialog vertically |
| `modal-dialog-scrollable` | Scrolls the body instead of the whole page |
| `modal-full-width` | Lets the dialog use the full viewport width |
| `modal-fullscreen` | Fills the viewport; modal-fullscreen-{breakpoint}-down only below that width |
| `offcanvas-narrow` | Narrower panel, 20rem instead of the default width |
| `progress-stacked` | Wrapper for several tracks shown as one bar |
| `progress-separated` | Adds a gap between stacked segments |
| `steps-counter` | Numbers the steps |
| `nav-fill` | Spreads the tabs across the full width |
| `table-responsive` | Scrolls sideways; table-responsive-{breakpoint} only below that width |
| `table-mobile-{breakpoint}` | Stacks rows into blocks below the breakpoint, using data-label on each cell |
| `table-vcenter` | Centers cell content vertically |
| `table-center` | Centers cell content horizontally |
| `table-nowrap` | Stops cell text from wrapping |
| `tracking-squares` | Draws the blocks as equal squares instead of tall bars |

## part

| Name | Description |
| --- | --- |
| `accordion-item` | One collapsible section |
| `accordion-header` | Heading that holds the toggle button |
| `accordion-button` | The toggle itself |
| `accordion-body` | Panel revealed when the section opens |
| `alert-heading` | Title line above the message |
| `alert-description` | Secondary text under the title |
| `alert-icon` | Icon slot, sized and colored with the alert |
| `alert-link` | Link inside the message, matched to the alert color |
| `alert-action` | Underlined link on its own line, for the next step |
| `alert-list` | List inside the alert, with the default list margin removed |
| `avatar-brand` | Small brand mark in the bottom corner |
| `avatar-cover` | Pulls the avatar over the element above it, with a ring |
| `avatar-upload-text` | Label inside an empty upload slot |
| `avatar-list` | Wrapper that spaces several avatars in a row |
| `badge-dot` | Small dot with no text, for a status marker |
| `badge-icononly` | Square badge holding only an icon |
| `badge-notification` | Positions the badge over the corner of its parent |
| `badge-list` | Wrapper that spaces several badges in a row |
| `breadcrumb-item` | One step in the trail |
| `btn-action` | Quiet icon button for a card or a table row |
| `btn-actions` | Wrapper that groups action buttons |
| `btn-list` | Spaces several buttons in a row; add btn-list-center to center them |
| `btn-group` | Joins buttons into one control; btn-group-vertical stacks them |
| `btn-toolbar` | Groups several button groups |
| `btn-check` | Hidden input that turns a label into a toggle button |
| `card-header` | Title bar at the top |
| `card-title` | Heading, inside the header or the body |
| `card-subtitle` | Small label above the title |
| `card-body` | Content area |
| `card-footer` | Row at the bottom |
| `card-meta` | Muted secondary line, for a role or a date |
| `card-actions` | Slot for controls on the trailing edge of the header |
| `card-btn` | Full width action along the bottom edge |
| `card-img-top` | Image above the body; card-img-end rounds the trailing corners instead |
| `card-img-overlay` | Content laid over an image; add card-img-overlay-dark for a scrim |
| `card-cover` | Header with a background photo and an overlay; card-cover-blurred blurs it |
| `card-stamp` | Large decorative mark in the corner |
| `card-status-top` | Colored bar on an edge; also -bottom and -start |
| `card-progress` | Progress bar attached to the card edge |
| `card-tabs` | Wrapper for a card whose header is a row of tabs |
| `card-header-tabs` | Tabs inside the header; card-header-pills for the pill style |
| `card-table` | Table that sits flush inside the card |
| `card-code` | Replaces the body for a code block, removing the padding |
| `card-list-group` | List group that sits flush inside the card |
| `carousel-inner` | Wrapper around the slides |
| `carousel-item` | One slide |
| `carousel-caption` | Text block over a slide |
| `carousel-indicators` | Row of buttons that jump to a slide |
| `carousel-control-prev` | Previous control; use a button so it can take focus |
| `carousel-control-next` | Next control |
| `chat-bubbles` | Wrapper around a group of messages from one author |
| `chat-bubble` | A single message |
| `chat-bubble-title` | Header line of a message, holding the author and the time |
| `chat-bubble-author` | Author name |
| `chat-bubble-date` | Timestamp |
| `chat-bubble-body` | Message text |
| `datagrid-title` | Label above a value |
| `hr-text` | Divider with a label; the text sits in a child element |
| `dropdown-toggle` | The trigger; Bootstrap keeps aria-expanded in sync |
| `dropdown-menu` | The menu itself |
| `dropdown-item` | One entry |
| `dropdown-item-icon` | Icon at the start of an entry |
| `dropdown-item-indicator` | Marker slot, for a checkbox or a radio |
| `dropdown-item-text` | Plain text that is not an action |
| `dropdown-header` | Section label between entries |
| `dropdown-divider` | Separator line |
| `dropdown-menu-columns` | Splits a long menu into columns, holding dropdown-menu-column elements |
| `dropdown-menu-card` | Lets the menu hold card content instead of a list |
| `empty-header` | Large muted text above the title, usually an error code |
| `empty-img` | Illustration slot |
| `empty-icon` | Icon slot, an alternative to the illustration |
| `empty-title` | Heading that says what is missing |
| `empty-subtitle` | Explanation under the title |
| `empty-action` | Row holding the call to action |
| `list-group-item` | One entry |
| `list-group-header` | Section label between entries |
| `list-group-item-actions` | Slot for controls on the trailing edge of an entry |
| `mention-avatar` | Round picture of a person at the start of the chip |
| `mention-app` | Square logo of an app or a brand |
| `mention-color` | Color swatch, tinted with any bg-{color} utility |
| `mention-count` | Muted number at the end, for example how many posts carry a tag |
| `modal-dialog` | Positions and sizes the dialog inside the container |
| `modal-content` | The visible panel |
| `modal-header` | Title bar, usually with the close button |
| `modal-title` | Heading inside the header |
| `modal-body` | Content area |
| `modal-footer` | Row of actions at the bottom |
| `modal-status` | Colored bar across the top edge, tinted with a bg-{color} utility |
| `offcanvas-header` | Title bar with a bottom border |
| `offcanvas-title` | Heading inside the header |
| `offcanvas-body` | Content area |
| `offcanvas-footer` | Row of actions at the bottom |
| `page-item` | One entry in the pager |
| `page-link` | The clickable element inside an entry |
| `page-item-title` | Page name, for a pager that names pages instead of numbering them |
| `page-item-subtitle` | Small uppercase label above the title |
| `popover-header` | Title bar |
| `popover-body` | Content area |
| `popover-arrow` | Pointer towards the trigger, positioned by Bootstrap |
| `progress-steps-item` | One step; mark the reached ones as active |
| `progress-bar` | The filled part; set its width and the ARIA value on it |
| `status-dot` | Small dot marker |
| `status-indicator` | Larger marker, usually on its own |
| `step-item` | One step; mark the current one with aria-current="step" |
| `switch-icon-a` | Icon shown in the off state |
| `switch-icon-b` | Icon shown in the on state |
| `nav-link` | One tab trigger; needs role="tab" and aria-controls |
| `tab-content` | Wrapper around the panels |
| `tab-pane` | One panel; needs role="tabpanel" |
| `td-truncate` | Cell whose content is cut with an ellipsis instead of stretching the table |
| `tag-avatar` | Avatar at the start of the tag |
| `tag-flag` | Country flag at the start of the tag |
| `tag-payment` | Payment provider logo at the start of the tag |
| `tag-icon` | Icon slot inside the tag |
| `tag-check` | Checkbox inside the tag, for a removable filter |
| `tag-badge` | Badge appended to the tag |
| `tag-list` | Wrapper that spaces several tags in a row |
| `timeline-event` | One entry on the timeline |
| `timeline-event-icon` | Marker on the line, next to the entry |
| `timeline-event-card` | Card holding the content of the entry |
| `toast-container` | Positions the toasts on the page |
| `toast-header` | Title bar with the close button |
| `toast-body` | Message area |
| `tooltip-inner` | The bubble holding the text |
| `tooltip-arrow` | Pointer towards the trigger, positioned by Bootstrap |
| `tracking-block` | One block in the strip, colored with a bg-{color} utility |

## size

| Name | Description |
| --- | --- |
| `avatar-{size}` | From avatar-xxs to avatar-2xl; avatar-list-{size} sizes a whole list |
| `badge-sm` | Small size |
| `badge-lg` | Large size |
| `btn-sm` | Small size |
| `btn-lg` | Large size |
| `btn-xl` | Extra large size |
| `card-sm` | Tighter padding |
| `card-md` | Default padding |
| `card-lg` | Roomier padding |
| `icon-sm` | 1rem |
| `icon-md` | 1.5rem, with a thinner stroke |
| `icon-lg` | 2.5rem |
| `ratio-{ratio}` | The aspect to hold, for example ratio-16x9 or ratio-21x9 |
| `modal-sm` | Small dialog |
| `modal-lg` | Large dialog |
| `modal-xl` | Extra large dialog |
| `offcanvas-{breakpoint}` | Behaves as an offcanvas below the breakpoint and as normal content above it |
| `pagination-sm` | Small size |
| `pagination-lg` | Large size |
| `placeholder-xs` | Extra small, for a caption line |
| `placeholder-sm` | Small |
| `placeholder-lg` | Large, for a heading line |
| `progress-sm` | Thin track |
| `progress-lg` | Thick track |
| `progress-xl` | Extra thick track |
| `nav-sm` | Small size |
| `nav-lg` | Large size |
| `spinner-border-sm` | Small ring |
| `spinner-grow-sm` | Small dot |
| `table-sm` | Tighter cell padding |

## style

| Name | Description |
| --- | --- |
| `accordion-button-toggle` | Chevron indicator on the toggle |
| `accordion-button-toggle-plus` | Plus and minus indicator instead of a chevron |
| `alert-important` | Solid fill in the alert color, with white text |
| `alert-minor` | Drops the tinted background, keeps a plain border |
| `alert-dismissible` | Reserves room on the trailing edge for the close button |
| `avatar-square` | Rounded corners instead of a circle |
| `avatar-rounded` | Fully round, the default shape |
| `avatar-upload` | Dashed empty slot for a picture that is not set yet |
| `badge-outline` | Transparent fill with a colored border |
| `breadcrumb-arrows` | Chevron between the items |
| `breadcrumb-bullets` | Bullet between the items |
| `breadcrumb-dots` | Dot between the items |
| `breadcrumb-muted` | Quieter text color for the whole trail |
| `btn-outline` | Transparent fill with a colored border |
| `btn-ghost` | No fill and no border until hovered |
| `btn-link` | Renders as a plain link |
| `card-gradient-{preset}` | Ready-made blends - disco, gold, love, mellow, ocean, psychedelic, rainbow, snow, sun |
| `card-borderless` | Removes the border |
| `card-transparent` | Removes the background and the border |
| `card-dashed` | Dashed border, for an empty or a placeholder card |
| `carousel-caption-background` | Adds a scrim behind the caption |
| `carousel-indicators-dot` | Round dot indicators |
| `carousel-indicators-thumb` | Thumbnail indicators |
| `carousel-indicators-vertical` | Stacks the indicators down the side |
| `chat-bubble-me` | Marks the message as sent by the current user |
| `dropdown-menu-arrow` | Adds a pointer towards the trigger |
| `dropdown-menu-dark` | Dark menu |
| `dropdown-toggle-split` | Separates the caret into its own button |
| `empty-bordered` | Adds a dashed border around the whole block |
| `icon-filled` | Fills the shape with the current text color |
| `icon-inline` | Aligns the icon with the surrounding line of text |
| `list-group-flush` | Removes the outer border, to sit flush in a card |
| `list-group-transparent` | Removes the background and the borders |
| `list-group-numbered` | Numbers the entries |
| `list-separated` | Spaces the entries apart instead of joining them with borders |
| `modal-blur` | Blurs the page behind the dialog instead of only dimming it |
| `pagination-outline` | Outlined entries instead of filled ones |
| `pagination-circle` | Round entries |
| `progress-bar-striped` | Diagonal stripes over the fill |
| `ribbon-bookmark` | Notched bookmark shape instead of a plain band |
| `status-lite` | Transparent background, keeps only the marker and the text |
| `status-indicator-circle` | Solid circle inside the indicator |
| `nav-underline` | Underline marker instead of a filled tab |
| `nav-bordered` | Bordered tab style |
| `table-striped` | Shades every other row |
| `table-bordered` | Border on every cell |
| `table-borderless` | Removes all borders |
| `table-transparent` | Removes the background |
| `timeline-simple` | Drops the cards and the connecting line for a plain list |
