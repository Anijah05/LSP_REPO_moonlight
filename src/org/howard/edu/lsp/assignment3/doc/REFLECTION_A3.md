# CSCI 363 – Assignment 3 Reflection (OO Redesign)

## Overview
In Assignment 2, I implemented the ETL pipeline in a single Java class. The code worked correctly but combined multiple responsibilities (file reading, validation, transformation rules, file writing, and summary reporting) inside one file. In Assignment 3, I redesigned the solution into multiple classes with clearer responsibilities while keeping the exact same behavior (same input path, output path, transformations, row skipping rules, error handling, and summary output).

## What is different about the design?
**Assignment 2** was mostly procedural: one class did everything end-to-end.
**Assignment 3** separates responsibilities into multiple classes:
- `ETLPipelineApp` runs the pipeline and wires the components together.
- `CsvProductReader` handles reading the CSV, applying skipping rules, and calling the transformer.
- `ProductTransformer` contains only the transformation logic in the exact required order.
- `CsvProductWriter` writes the output CSV in the required format and header.
- `ProductRecord` encapsulates the data for a single row and handles parsing and output formatting.
- `PipelineReport` tracks and prints the run summary.
- `TransformResult` is a small wrapper around transformed output to keep the design extensible.

This separation made the code easier to read, test, and maintain because each class has a focused purpose.

## How is Assignment 3 more object-oriented?
Assignment 3 is more object-oriented because:
- It models the domain data as an object (`ProductRecord`) instead of passing around raw strings.
- It uses encapsulation: parsing and CSV output formatting are inside `ProductRecord`, and transformation rules are inside `ProductTransformer`.
- It decomposes the system into collaborating objects (reader, transformer, writer, report) rather than one long method.

## Which OO ideas did you use?
- **Object/Class:** The design is built around objects such as `ProductRecord`, `CsvProductReader`, and `ProductTransformer`.
- **Encapsulation:** Each class hides its internal details. For example, `ProductTransformer` hides the transformation steps behind `applyAll()`, and `PipelineReport` hides counter tracking behind methods.
- **Polymorphism / Inheritance:** This redesign primarily focuses on decomposition and encapsulation. Although inheritance is not heavily used, the design could support polymorphism in the future (e.g., swapping in a different reader or transformer implementation) because responsibilities are separated into distinct components.

## How did you test to confirm Assignment 3 works the same as Assignment 2?
I tested Assignment 3 using the same test cases from Assignment 2:
1. **Robust sample input:** I used the provided `data/products.csv` that contains bad rows and blank lines. I verified:
   - Output file matches the expected transformed CSV exactly.
   - Run summary counts match expected values (rows read, transformed, skipped).
2. **Empty input file (header only):** I verified that the program still creates `data/transformed_products.csv` containing only the header.
3. **Missing input file:** I verified that the program prints a clear error message and exits cleanly without a stack trace.

These tests confirmed Assignment 3 produces the same output/behavior as Assignment 2, while the internal design is more object-oriented.