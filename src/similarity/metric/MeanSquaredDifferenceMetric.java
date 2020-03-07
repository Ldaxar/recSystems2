package similarity.metric;

import java.util.Set;

import profile.Profile;

public class MeanSquaredDifferenceMetric implements SimilarityMetric {

	private Integer minRating = 0;
	private Integer maxRating = 0;
	
	

	public MeanSquaredDifferenceMetric(Integer minRating, Integer maxRating) {
		super();
		this.minRating = minRating;
		this.maxRating = maxRating;
	}

	@Override
	public double getSimilarity(Profile p1, Profile p2) {
		Set<Integer> common = p1.getCommonIds(p2);
		Double msd = common.stream().mapToDouble(id -> Math.pow(p1.getValue(id) - p2.getValue(id), 2))
				.sum()/common.size();
		return 1 - (msd/Math.pow(maxRating-minRating, 2));
	}
}
