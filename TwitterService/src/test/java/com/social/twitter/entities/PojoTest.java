package com.social.twitter.entities;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.filters.FilterPackageInfo;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.PojoValidator;
import com.openpojo.validation.affirm.Affirm;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.NoFieldShadowingRule;
import com.openpojo.validation.rule.impl.NoNestedClassRule;
import com.openpojo.validation.rule.impl.NoPublicFieldsRule;
import com.openpojo.validation.rule.impl.NoStaticExceptFinalRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

public class PojoTest {
	// +1 including the currrent class
	private static final int EXPECTED_CLASS_COUNT = 3;
	private static final String POJO_PACKAGE = "com.social.twitter.entities";
	private List<PojoClass> pojoClasses;
	private PojoValidator pojoValidator;

	@Before
	public void setup() {
		pojoClasses = PojoClassFactory.getPojoClasses(POJO_PACKAGE,
				new FilterPackageInfo());
		pojoValidator = new PojoValidator();
		// This rule ensures that no fields declared with public visibility.
		pojoValidator.addRule(new NoPublicFieldsRule());
		// This rule ensures that there are no static fields unless they are
		// final.
		pojoValidator.addRule(new NoStaticExceptFinalRule());
		// This rule ensures that no subclass shadows fields defined in a parent
		// class
		pojoValidator.addRule(new NoFieldShadowingRule());
		// This rule ensures that all Fields have a getter associated with them.
		pojoValidator.addRule(new GetterMustExistRule());
		// This rule ensures that all Fields have a setter associated with them.
		pojoValidator.addRule(new SetterMustExistRule());
		// This Rule checks for classes being "nested".
		pojoValidator.addRule(new NoNestedClassRule());
		// Test the setter and ensure it sets the field being tested if and only
		// if a Setter method was defined.
		pojoValidator.addTester(new SetterTester());
		// Test the getter and ensure it retrieves from the field being tested
		// if and only if it has a getter defined.
		pojoValidator.addTester(new GetterTester());
	}

	/**
	 * Test case to test the count of entites
	 */
	@Test
	public void ensureExpectedPojoCount() {
		List<PojoClass> pojoClasses = PojoClassFactory.getPojoClasses(
				POJO_PACKAGE, new FilterPackageInfo());
		Affirm.affirmEquals("Classes added / removed?", EXPECTED_CLASS_COUNT,
				pojoClasses.size());
	}

	/**
	 * Run Validation on Pojos
	 */
	@Test
	public void testPojoStructureAndBehavior() {
		for (PojoClass pojoClass : pojoClasses) {
			if (!pojoClass.getName().equalsIgnoreCase(
					"com.social.twitter.entities.PojoTest")) {
				System.out.println("Validating " + pojoClass.getName());
				pojoValidator.runValidation(pojoClass);
			}
		}

	}
}
