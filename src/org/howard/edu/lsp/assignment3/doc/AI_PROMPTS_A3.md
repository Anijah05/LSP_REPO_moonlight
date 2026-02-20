# AI Prompts & Excerpts (Assignment 3)

Below are prompts I used with a generative AI assistant (ChatGPT) to redesign Assignment 2 into a more object-oriented solution. I reviewed all suggestions and adapted them to match the exact Assignment 2 specification (transform order, skipping rules, summary counts, and relative paths).

---

## Prompt 1
**Prompt:** “Help me redesign my Assignment 2 ETL pipeline into an object-oriented design with multiple classes while keeping the exact same behavior.”

**Excerpt of AI response:** Suggested splitting responsibilities into Reader, Transformer, Writer, a data model class for rows, and a reporting class for summary counts.

**How I used it:** I implemented `CsvProductReader`, `ProductTransformer`, `CsvProductWriter`, `ProductRecord`, and `PipelineReport` to separate concerns.

---

## Prompt 2
**Prompt:** “What classes would you create for a CSV ETL pipeline in Java (no third-party libraries), and what responsibilities should each class have?”

**Excerpt of AI response:** Proposed a `Product`/record class to encapsulate parsing/formatting and separate I/O from transformations.

**How I used it:** I created `ProductRecord` with `tryParse()` for validation and `toOutputCsvRow()` for output formatting.

---

## Prompt 3
**Prompt:** “My run summary counts must match the spec. How should I count blank lines?”

**Excerpt of AI response:** Explained that blank lines can be ignored completely to match expected counts if the instructor’s expected results don’t count blanks as read or skipped.

**How I used it:** In `CsvProductReader`, blank lines are ignored (not counted as read or skipped) so the summary matches the expected output.

---

## Prompt 4
**Prompt:** “Generate Javadocs for these classes and public methods, but keep them accurate and not overly long.”

**Excerpt of AI response:** Provided Javadoc templates and reminded me to review/edit for correctness.

**How I used it:** I added Javadocs to each class and public method and edited wording to match the actual behavior.

---