package com.datastructurediagrammer;

public interface ProblemGenerator<DS, Diagrammer extends DataStructureDiagrammer<DS>> {
    public String generateWorksheet();
}
