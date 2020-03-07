package similarity.metric;

import java.util.Set;

import profile.Profile;

public class PearsonSigWeightingMetric implements SimilarityMetric {

	private Integer threshold;
	
	public PearsonSigWeightingMetric(Integer threshold) {
		super();
		this.threshold = threshold;
	}

	@Override
	public double getSimilarity(Profile p1, Profile p2) {
				PearsonMetric pm = new PearsonMetric();
		Set<Integer> common = p1.getCommonIds(p2);

		return (common.size() < threshold) ? 
				pm.getSimilarity(p1, p2)*((double)common.size()/threshold) : pm.getSimilarity(p1, p2);
	}

}
