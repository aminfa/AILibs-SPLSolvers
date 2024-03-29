package hasco.core;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import hasco.model.Component;
import hasco.model.Parameter;
import hasco.model.ParameterRefinementConfiguration;
import jaicore.logic.fol.structure.ConstantParam;
import jaicore.logic.fol.structure.Monom;
import jaicore.logic.fol.theories.EvaluablePredicate;

public class isNotRefinable implements EvaluablePredicate {
	
	private final isValidParameterRangeRefinementPredicate p;
	
	public isNotRefinable(Collection<Component> components, Map<Component, Map<Parameter, ParameterRefinementConfiguration>> refinementConfiguration) {
		super();
		this.p = new isValidParameterRangeRefinementPredicate(components, refinementConfiguration);
	}
	
	@Override
	public Collection<List<ConstantParam>> getParamsForPositiveEvaluation(Monom state, ConstantParam... partialGrounding) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isOracable() {
		return false;
	}

	@Override
	public Collection<List<ConstantParam>> getParamsForNegativeEvaluation(Monom state, ConstantParam... partialGrounding) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean test(Monom state, ConstantParam... params) {
		return true; //p.getParamsForPositiveEvaluation(state, params[0], params[1], params[2], params[3], params[4], null).isEmpty();
	}

}
