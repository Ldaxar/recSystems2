package alg.ub.predictor;

import java.util.Map;
import java.util.Set;

import alg.ub.neighbourhood.Neighbourhood;
import profile.Profile;
import similarity.SimilarityMap;

public class WeightedAveragePredictor implements Predictor {

	@Override
	public Double getPrediction(Integer userId, Integer itemId, Map<Integer, Profile> userProfileMap,
			Map<Integer, Profile> itemProfileMap, Neighbourhood neighbourhood, SimilarityMap simMap) {
		
		Set<Integer> neighbours = neighbourhood.getNeighbours(userId);
		Double s1 = 0d, s2 = 0d;
		// return null if the user has no neighbours
		if (neighbours == null)
			return null;
		
		for(Integer neighbour: neighbours) // iterate over each neighbour
		{
			Double rating = userProfileMap.get(neighbour).getValue(itemId); // get the neighbour's rating for the target item
			Profile profile = simMap.getSimilarities(userId);
			if (profile != null) {
				double sim = profile.getValue(neighbour);
				s1+=(sim*rating);
				s2+=sim;
			}
		}
		return s2 != 0d ? s1/s2 : 0d;
	}

}
