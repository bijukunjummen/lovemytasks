package org.bk.webtest2;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.lang.annotation.Annotation;
import org.junit.Test;

public class AnAnnotationTest {
	
	
	@Test
	public void testAnAnnotation() throws Exception {
		AnAnnotatedClass anAnnotatedClass = new AnAnnotatedClass();
		Annotation[] annotationsOnClass = anAnnotatedClass.getClass().getAnnotations();
		assertThat(annotationsOnClass.length, is(1));
	}

}

@AnAnnotaton
class AnAnnotatedClass{
	
}