package org.jboss.ddoyle.drools.test;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.drools.compiler.compiler.DRLFactory;
import org.drools.compiler.lang.DRLLexer;
import org.drools.compiler.lang.DRLParser;
import org.drools.compiler.lang.DrlDumper;
import org.drools.compiler.lang.descr.PackageDescr;
import org.kie.api.KieServices;
import org.kie.api.io.Resource;
import org.kie.internal.builder.conf.LanguageLevelOption;

public class Main {

	public static void main(String[] args) throws Exception {
		System.out.println("Parsing and dumping DRL.");

		KieServices kieServices = KieServices.Factory.get();
		Resource drlResource = kieServices.getResources().newClassPathResource("rules.drl");
		
		DRLParser parser = createParser(new ANTLRInputStream(drlResource.getInputStream()));
		PackageDescr packageDescription = parser.compilationUnit();
		
		DrlDumper dumper = new DrlDumper();
		System.out.println(dumper.dump(packageDescription));
	}

	private static DRLParser createParser(CharStream charStream) {
		DRLLexer lexer = DRLFactory.getDRLLexer(charStream, LanguageLevelOption.DRL6);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		DRLParser parser = DRLFactory.getDRLParser(tokens, LanguageLevelOption.DRL6);
		return parser;
	}

}
