package alg.ub.neighbourhood;

import java.util.HashSet;
import java.util.Set;

import profile.Profile;
import similarity.SimilarityMap;
import util.ScoredThingDsc;

public class ThresholdNeighbourhood extends Neighbourhood{

	private double threshold ;
	
	
	
	public ThresholdNeighbourhood(double threshold) {
		super();
		this.threshold = threshold;
	}



	@Override
	public void computeNeighbourhoods(SimilarityMap simMap) {
		// TODO Auto-generated method stub
		for (Integer userId: simMap.getIds()) {
			Set<ScoredThingDsc> thresholdEntries = new HashSet<ScoredThingDsc>(); 
			
			Profile profile = simMap.getSimilarities(userId); // get the user similarity profile
			if (profile != null) {
				for (Integer id: profile.getIds()) { // iterate over each user in the profile
					double sim = profile.getValue(id);
					if (sim>threshold) {
						this.add(userId, id);
					//	thresholdEntries.add(new ScoredThingDsc(sim, id));
					}
				}
			}
		}
	}

}
