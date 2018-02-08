package de.upb.crc901.mlplan.classifiers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import de.upb.crc901.mlplan.core.MLPipeline;
import de.upb.crc901.mlplan.core.MLUtil;
import de.upb.crc901.mlplan.search.algorithms.GraphBasedPipelineSearcher;
import jaicore.planning.graphgenerators.task.tfd.TFDNode;
import jaicore.planning.model.task.ceocstn.CEOCSTNUtil;
import jaicore.search.algorithms.parallel.parallelexploration.distributed.interfaces.SerializableGraphGenerator;
import jaicore.search.algorithms.standard.core.ORGraphSearch;
import weka.classifiers.Classifier;
import weka.core.Instances;

/**
 * This class generates k random pipelines and picks the one that is best against a validation set
 * 
 * @author Felix
 *
 */
public class RandomPipelinePicker extends GraphBasedPipelineSearcher<TFDNode, String, Integer>{
	private final SerializableGraphGenerator<TFDNode, String> graphGenerator;
	public RandomPipelinePicker(File testsetFile, boolean showGraph, int numberOfSolutions, int selectionDepth, int timeout, Random random) throws IOException {
		super(random, timeout, showGraph);
		this.graphGenerator = MLUtil.getGraphGenerator(testsetFile, null, null, null);
	}

	@Override
	protected ORGraphSearch<TFDNode, String, Integer> getSearch(Instances data) {		return null;//new RandomizedDepthFirstSearch<>(graphGenerator, getRandom());
	}

	@Override
	protected Classifier convertPathToPipeline(List<TFDNode> path) {
		try {
			return MLUtil.extractGeneratedClassifierFromPlan(CEOCSTNUtil.extractPlanFromSolutionPath(path));
		}
		catch (Throwable e) {
			throw new IllegalArgumentException("Converting path to pipeline caused " + e.getClass().getName() + " with message " + e.getMessage() + " for path " + path);
		}
	}

	@Override
	protected Classifier selectModel() {
		return solutions.peek();
	}
}