package alg.ub.predictor;

import java.util.Map;
import java.util.Set;

import alg.ub.neighbourhood.Neighbourhood;
import profile.Profile;
import similarity.SimilarityMap;

public class DeviationFromUserMeanPredictor implements Predictor {
	
	@Override
	public Double getPrediction(Integer userId, Integer itemId, Map<Integer, Profile> userProfileMap,
			Map<Integer, Profile> itemProfileMap, Neighbourhood neighbourhood, SimilarityMap simMap) {
		
		Set<Integer> neighbours = neighbourhood.getNeighbours(userId);
		if (neighbours == null)
			return null;
		
		Pair p = neighbours.stream().filter(id -> userProfileMap.get(id).getValue(itemId) != null)
				.map(id ->  {
					Double bottom = simMap.getSimilarity(id, userId);
					Double top = userProfileMap.get(id).getValue(itemId)
							-userProfileMap.get(id).getMeanValue();
					return new Pair (bottom*top, bottom);
				})
				.reduce(new Pair(0d, 0d), (acc, pair) -> {
					acc.s1 += pair.s1;
					acc.s2 += pair.s2;
					return acc;
				});
		return p.s2 > 0d ? userProfileMap.get(userId).getMeanValue() + p.s1 / p.s2 : null;
	}

}
