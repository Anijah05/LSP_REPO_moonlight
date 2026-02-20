package org.howard.edu.lsp.assignment3;

import java.util.Collections;
import java.util.List;

/**
 * Holds the result of transforming input rows into output records.
 * <p>
 * Separating this makes it easier to extend later (e.g., collecting warnings).
 * </p>
 */
public class TransformResult {

    private final List<ProductRecord> transformedRecords;

    /**
     * Constructs a TransformResult.
     *
     * @param transformedRecords list of transformed records
     */
    public TransformResult(List<ProductRecord> transformedRecords) {
        this.transformedRecords = transformedRecords;
    }

    /**
     * Returns the transformed records.
     *
     * @return unmodifiable list of transformed records
     */
    public List<ProductRecord> getTransformedRecords() {
        return Collections.unmodifiableList(transformedRecords);
    }
}