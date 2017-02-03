# Design Spec

 This document specifies details about the original game that must be replicated, and serves as reference.
---

## Sections:
- The Grid
- The Layers
- Tranistors

---
## The Grid-base

Tiles are visually represented as 13x13 pixels. The game takes place on 27 x 44 grid, though only center 27 x 36 is editable.

Two 4-tile wide columns on each side contain 6 solder-pads and act as external connection. each pad 3x3 tiles with a 1 tile gap between pads.

---
## Layers

Circuits consist of 2 layers build on the grid, metal and silicon, as well as interconnects/vias that connect them electrically.

The **Metal Layer** consists of a single material type, each grid cell is either occupied or not. Neighboring occupied cells are not nessisarily connected, this also needs to be stored.

The **Silicon Layer** is similar but consists of two material types, N and P, represented by color. Neighboring cells that are not of the same material cannot be connected normally.

"Vias" create electrical connections between tiles on the metal and silicon layers, and are only allowed on non-transistor silicon

---
## Transistors

A transistor can be formed on the silicon layer by connecting a silicon tile to a neighboring perpendicular length of straight silicon made of opposite type. The connection does not conduct power across silicon types.

Connection from unlike silicon types can result in a transistor.

### Yellow on red
A length of red silicon interjected by yellow.

- The red path defaults to disconnected.
- If the yellow silicon is powered, the path will open in the following tick.
- If the power to yellow is removed, path will close

### Red on Yellow
A length of yellow silicon interjected by red.

- The yellow path defaults to disconnected.
- If the red silicon is powered, the path will open in the following tick.
- If the power to red is removed, path will close

---
## The "Tick" Event

Every tick, electricity propogates from voltage sources. [...]