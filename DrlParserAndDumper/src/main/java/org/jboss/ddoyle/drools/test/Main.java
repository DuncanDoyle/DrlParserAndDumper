package org.jboss.ddoyle.drools.test;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.drools.compiler.lang.DRL6Lexer;
import org.drools.compiler.lang.DRL6Parser;
import org.drools.compiler.lang.DRLLexer;
import org.drools.compiler.lang.DrlDumper;
import org.drools.compiler.lang.descr.PackageDescr;
import org.kie.api.KieServices;
import org.kie.api.io.Resource;

public class Main {

	public static void main(String[] args) throws Exception {
		System.out.println("Parsing and dumping DRL.");

		KieServices kieServices = KieServices.Factory.get();
		Resource drlResource = kieServices.getResources().newClassPathResource("rules.drl");
		
		DRL6Parser parser = createParser(new ANTLRInputStream(drlResource.getInputStream()));
		PackageDescr packageDescription = parser.compilationUnit();
		
		DrlDumper dumper = new DrlDumper();
		System.out.println(dumper.dump(packageDescription));
	}

	private static DRL6Parser createParser(CharStream charStream) {
		// DRLFactory.
		DRLLexer lexer = new DRL6Lexer(charStream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		DRL6Parser parser = new DRL6Parser(tokens);
		return parser;

	}

	private static String dump2DRL(PackageDescr descr) {
		DrlDumper dumper = new DrlDumper();
		return dumper.dump(descr);
	}

}
